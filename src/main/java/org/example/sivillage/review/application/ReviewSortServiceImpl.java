package org.example.sivillage.review.application;

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
public class ReviewSortServiceImpl implements ReviewSortService {

    private final JPAQueryFactory jpaQueryFactory;

    public List<IdListResponseDto<Long>> getSortReviews(Long cursor, int pageSize, String sort) {

        QReview review = QReview.review;
        QReviewLike reviewLike = QReviewLike.reviewLike1;

        Map<String, JPAQuery<Long>> queryMap = Map.of(
                "created", jpaQueryFactory
                        .select(review.id)
                        .from(review)
                        .orderBy(review.createdDate.desc()),

                "likes", jpaQueryFactory
                        .select(reviewLike.reviewId)
                        .from(reviewLike)
                        .groupBy(reviewLike.reviewId)
                        .orderBy(reviewLike.count().desc()),

                "best", jpaQueryFactory
                        .select(review.id)
                        .from(review)
                        .orderBy(review.score.desc(), review.createdDate.desc()));

        JPAQuery<Long> query = queryMap.getOrDefault(sort, queryMap.get(sort));


        if (cursor != null) {
            query.where(review.id.gt(cursor));
        }

        return query.limit(pageSize)
                .fetch()
                .stream()
                .map(IdListResponseDto::from)
                .toList();
    }
}