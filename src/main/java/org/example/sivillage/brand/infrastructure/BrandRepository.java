package org.example.sivillage.brand.infrastructure;

import org.example.sivillage.brand.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findByBrandEngName(String brandEngName);

    Optional<Brand> findByBrandKorName(String brandKorName);

    boolean existsByBrandEngNameOrBrandKorName(String brandEngName, String brandKorName);

    // 영문자에 대해 brandEngName으로 시작하는 브랜드를 오름차순 정렬하여 반환
    @Query("SELECT b FROM Brand b WHERE LOWER(b.brandEngName) LIKE LOWER(CONCAT(:prefix, '%')) ORDER BY b.brandEngName")
    List<Brand> findByEngNameStartingWith(@Param("prefix") String prefix);

    // 한글에 대해 brandKorName으로 시작하는 브랜드를 오름차순 정렬하여 반환
    @Query("SELECT b FROM Brand b WHERE b.brandKorName LIKE CONCAT(:prefix, '%') ORDER BY b.brandKorName")
    List<Brand> findByKorNameStartingWith(@Param("prefix") String prefix);

    // 전체 브랜드를 brandEngName 기준으로 오름차순 정렬하여 반환
    @Query("SELECT b.brandId FROM Brand b ORDER BY b.brandEngName ASC")
    List<Long> findAllBrandIdsByOrderByEngNameAsc();

    Optional<Brand> findByBrandId(Long brandId);
}
