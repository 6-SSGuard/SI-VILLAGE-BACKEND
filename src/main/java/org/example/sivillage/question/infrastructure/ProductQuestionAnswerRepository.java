package org.example.sivillage.question.infrastructure;

import org.example.sivillage.question.domain.ProductQuestion;
import org.example.sivillage.question.domain.ProductQuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductQuestionAnswerRepository extends JpaRepository<ProductQuestionAnswer, Long> {

    List<ProductQuestionAnswer> findByMemberUuid(String memberUuid);
    Optional<ProductQuestionAnswer> findByProductQuestion(ProductQuestion productQuestion);
}
