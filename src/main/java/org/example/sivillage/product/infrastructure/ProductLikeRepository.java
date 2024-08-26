package org.example.sivillage.product.infrastructure;

import org.example.sivillage.member.domain.Member;
import org.example.sivillage.product.domain.Product;
import org.example.sivillage.member.domain.ProductLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLikeRepository extends JpaRepository<ProductLike, Long> {
    boolean existsByProductAndMember(Product product, Member member);
    void deleteByProductAndMember(Product product, Member member);
    long countByProduct(Product product);
}