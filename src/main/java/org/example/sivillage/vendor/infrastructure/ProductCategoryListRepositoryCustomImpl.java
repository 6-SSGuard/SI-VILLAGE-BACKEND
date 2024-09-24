package org.example.sivillage.vendor.infrastructure;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.product.domain.QProduct;
import org.example.sivillage.vendor.domain.QProductByVendor;
import org.example.sivillage.vendor.domain.QProductCategoryList;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductCategoryListRepositoryCustomImpl implements ProductCategoryListRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public Slice<String> findProductsByCategoriesWithCursorPaging(
            String topCategoryCode, String middleCategoryCode, String bottomCategoryCode, String subCategoryCode,
            String lastProductCode, Pageable pageable) {

        QProductCategoryList productCategoryList = QProductCategoryList.productCategoryList;
        QProductByVendor productByVendor = QProductByVendor.productByVendor;
        QProduct product = QProduct.product;

        // 정렬 옵션을 결정
        List<OrderSpecifier<?>> orderSpecifiers = getOrderSpecifiers(pageable.getSort(), productByVendor, product);

        List<String> productCodes = queryFactory.select(productCategoryList.productCode)
                .from(productCategoryList)
                .join(product).on(productCategoryList.productCode.eq(product.productCode))  // Product와 조인
                .join(productByVendor).on(productCategoryList.productCode.eq(productByVendor.productCode))  // ProductByVendor와 조인
                .where(
                        productCategoryList.topCategoryCode.eq(topCategoryCode),
                        productCategoryList.middleCategoryCode.eq(middleCategoryCode),
                        productCategoryList.bottomCategoryCode.eq(bottomCategoryCode),
                        subCategoryCode != null ? productCategoryList.subCategoryCode.eq(subCategoryCode) : null
                )
                .orderBy(orderSpecifiers.toArray(new OrderSpecifier[0]))  // 선택된 정렬 기준 적용
                .limit(pageable.getPageSize() + 1)  // 다음 페이지 확인을 위해 +1
                .fetch();

        boolean hasNext = productCodes.size() > pageable.getPageSize();

        if (hasNext) {
            productCodes.remove(productCodes.size() - 1);  // 다음 페이지 확인용으로 조회된 마지막 요소 제거
        }

        return new SliceImpl<>(productCodes, pageable, hasNext);
    }

    // Pageable의 Sort 객체를 기반으로 OrderSpecifier 목록을 생성
    private List<OrderSpecifier<?>> getOrderSpecifiers(Sort sort, QProductByVendor productByVendor, QProduct product) {
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();

        for (Sort.Order order : sort) {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            switch (order.getProperty()) {
                case "price":
                    // 할인된 가격 정렬
                    NumberExpression<Integer> discountedPrice = product.price.multiply(
                            Expressions.numberTemplate(Double.class, "1 - ({0} / 100.0)", productByVendor.discountRate)
                    );
                    orderSpecifiers.add(new OrderSpecifier<>(direction, discountedPrice));
                    break;
                case "discountRate":
                    // 할인율 정렬
                    orderSpecifiers.add(new OrderSpecifier<>(direction, productByVendor.discountRate));
                    break;
                case "createdDate":
                default:
                    // 신상품순 정렬
                    orderSpecifiers.add(new OrderSpecifier<>(direction, productByVendor.createdDate));
                    break;
            }
        }

        return orderSpecifiers;
    }

    // 할인된 가격으로 정렬
    private OrderSpecifier<?> getDiscountedPriceOrder(Order order, QProduct product, QProductByVendor productByVendor) {
        // 할인된 가격 계산: 가격 * (1 - 할인율 / 100)
        NumberExpression<Integer> discountedPrice = product.price.multiply(
                Expressions.numberTemplate(Double.class, "1 - ({0} / 100.0)", productByVendor.discountRate)
        );

        return new OrderSpecifier<>(order, discountedPrice);
    }
}
