package org.example.sivillage.brand.infrastructure;

import org.example.sivillage.brand.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    boolean existsByBrandEngNameOrBrandKorName(String brandEngName, String brandKorName);

    // 전체 브랜드를 brandEngName 기준으로 오름차순 정렬하여 반환
    @Query("SELECT b.id FROM Brand b ORDER BY b.brandEngName ASC")
    List<Long> findAllBrandIdsByOrderByEngNameAsc();

    Optional<Brand> findById(Long id);

    boolean existsByBrandEngName(String brandEngName);

    boolean existsByBrandKorName(String brandKorName);

    List<Brand> findAllByOrderByBrandEngNameAsc();
}
