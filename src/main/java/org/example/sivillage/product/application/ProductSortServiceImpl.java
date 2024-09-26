package org.example.sivillage.product.application;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.common.response.dto.IdListResponseDto;
import org.example.sivillage.product.domain.QProductLike;
import org.example.sivillage.product.domain.QProductLikeCount;
import org.example.sivillage.product.domain.QProductViewCount;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductSortServiceImpl implements ProductSortService{

    private final JPAQueryFactory jpaQueryFactory;

    public List<IdListResponseDto<String>> getSortBestProduct(String sort) {
        QProductViewCount productViewCount = QProductViewCount.productViewCount;
        QProductLikeCount productLikeCount = QProductLikeCount.productLikeCount;

        Map<String, JPAQuery<String>> queryMap = Map.of(
                "viewCount", jpaQueryFactory
                        .select(productViewCount.productCode)
                        .from(productViewCount)
                        .orderBy(productViewCount.viewCount.desc())
                        .limit(100), // 상위 100개만 조회

                "likes", jpaQueryFactory
                        .select(productLikeCount.productCode)
                        .from(productLikeCount)
                        .groupBy(productLikeCount.productCode) //
                        .orderBy(productLikeCount.count().desc()) // 좋아요 수에 따라 정렬
                        .limit(100)// 상위 100개만 조회
        );

        // 선택된 정렬 방식에 따라 쿼리 실행
        List<String> productIds = queryMap.getOrDefault(sort, queryMap.get("viewCount")).fetch();

        return productIds.stream()
                .map(IdListResponseDto::from)
                .collect(Collectors.toList());
    }
}
