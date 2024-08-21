package org.example.sivillage.domain.product.infrastructure;

import org.example.sivillage.domain.member.domain.Member;
import org.example.sivillage.domain.product.domain.Product;
import org.example.sivillage.domain.product.domain.ProductLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLikeRepository extends JpaRepository<ProductLike, Long> {
    boolean existsByProductAndMember(Product product, Member member);
    void deleteByProductAndMember(Product product, Member member);
    long countByProduct(Product product);
}