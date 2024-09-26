package org.example.sivillage.vendor.infrastructure;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.common.response.dto.ProductDto;
import org.example.sivillage.product.domain.QProduct;
import org.example.sivillage.vendor.domain.QProductByVendor;
import org.example.sivillage.vendor.domain.QProductCategoryList;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProductCategoryListRepositoryCustomImpl implements ProductCategoryListRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private JPAQuery<ProductDto> getDiscountedPriceQuery(QProduct product, QProductByVendor productByVendor, boolean asc) {
        NumberExpression<Integer> discountedPrice = product.price.multiply(
                Expressions.numberTemplate(Double.class, "1 - ({0} / 100.0)", productByVendor.discountRate)
        );

        Order order = asc ? Order.ASC : Order.DESC;
        return queryFactory.select(
                        Projections.constructor(ProductDto.class, product.productCode, discountedPrice)
                )
                .from(product)
                .join(productByVendor).on(product.productCode.eq(productByVendor.productCode))
                .orderBy(new OrderSpecifier<>(order, discountedPrice));
    }

    @Override
    public Slice<ProductDto> findProductsByCategoriesWithCursorPaging(
            String topCategoryCode, String middleCategoryCode, String bottomCategoryCode, String subCategoryCode,
            String lastProductCode, int pageSize, String sort) {

        QProductCategoryList productCategoryList = QProductCategoryList.productCategoryList;
        QProductByVendor productByVendor = QProductByVendor.productByVendor;
        QProduct product = QProduct.product;

        // 정렬 쿼리 매핑
        Map<String, JPAQuery<ProductDto>> queryMap = Map.of(
                "priceAsc", getDiscountedPriceQuery(product, productByVendor, true),
                "priceDesc", getDiscountedPriceQuery(product, productByVendor, false),
                "newest", queryFactory.select(
                                Projections.constructor(ProductDto.class, product.productCode, productByVendor.createdDate))
                        .from(productCategoryList)
                        .join(product).on(productCategoryList.productCode.eq(product.productCode))
                        .join(productByVendor).on(productCategoryList.productCode.eq(productByVendor.productCode))
                        .orderBy(productByVendor.createdDate.desc())
        );

        JPAQuery<ProductDto> query = queryMap.getOrDefault(sort, queryMap.get("newest"));

        // 마지막 값 설정에 대한 조건 처리
        if (lastProductCode != null && sort.equals("newest")) {
            LocalDateTime lastProductDateTime = LocalDateTime.parse(lastProductCode);
            query.where(productByVendor.createdDate.lt(lastProductDateTime));
        } else if (lastProductCode != null && sort.equals("priceAsc")) {
            query.where(product.price.gt(Integer.parseInt(lastProductCode)));
        } else if (lastProductCode != null && sort.equals("priceDesc")) {
            query.where(product.price.lt(Integer.parseInt(lastProductCode)));
        }

        // 카테고리 조건 처리
        query.where(
                topCategoryCode != null ? productCategoryList.topCategoryCode.eq(topCategoryCode) : null,
                middleCategoryCode != null ? productCategoryList.middleCategoryCode.eq(middleCategoryCode) : null,
                bottomCategoryCode != null ? productCategoryList.bottomCategoryCode.eq(bottomCategoryCode) : null,
                subCategoryCode != null ? productCategoryList.subCategoryCode.eq(subCategoryCode) : null
        );

        // 페이지 사이즈 + 1로 다음 페이지 여부 확인
        List<ProductDto> results = query.limit(pageSize + 1).fetch();

        // 다음 페이지가 있는지 확인
        boolean hasNext = results.size() > pageSize;
        if (hasNext) {
            results.remove(results.size() - 1);  // 다음 페이지 확인용으로 조회된 마지막 요소 제거
        }

        // Slice로 반환
        return new SliceImpl<>(results, Pageable.unpaged(), hasNext);
    }
}
