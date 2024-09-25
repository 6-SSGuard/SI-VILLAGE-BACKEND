package org.example.sivillage.review.application;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.global.common.response.dto.IdListResponseDto;
import org.example.sivillage.review.domain.QReview;
import org.example.sivillage.review.domain.QReviewLike;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewFilterServiceImpl implements ReviewFilterService {

    private final JPAQueryFactory jpaQueryFactory;

    public List<IdListResponseDto<Long>> getSortReviews(Long cursor, int pageSize, String sort) {

        QReview review = QReview.review;
        QReviewLike reviewLike = QReviewLike.reviewLike1;

        JPAQuery<Long> query = jpaQueryFactory
                .select(review.id)
                .from(review)
                .leftJoin(reviewLike).on(review.id.eq(reviewLike.reviewId))
                .groupBy(review.id);

        // 정렬 조건 설정
        Map<String, OrderSpecifier<?>> sortMap = Map.of(
                "likes", reviewLike.count().desc(),
                "created", review.createdDate.desc(),
                "best", review.score.desc()
        );

        // 정렬 조건을 추가
        query.orderBy(sortMap.getOrDefault(sort, review.createdDate.desc())); // 기본값으로 최신 등록 순

        // 커서 기준으로 필터링
        if (cursor != null) {
            query.where(review.id.gt(cursor)); // 커서 기준으로 필터링
        }

        // 페이지 사이즈 제한 및 결과 반환
        return query.limit(pageSize)
                .fetch()
                .stream()
                .map(IdListResponseDto::from)
                .toList();
    }
}