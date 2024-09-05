package org.example.sivillage.product.infrastructure;

import org.example.sivillage.product.domain.ProductQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductQuestionRepository extends JpaRepository<ProductQuestion, Long> {
}
