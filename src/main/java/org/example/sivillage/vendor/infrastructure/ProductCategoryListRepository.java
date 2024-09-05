package org.example.sivillage.vendor.infrastructure;

import org.example.sivillage.vendor.domain.ProductCategoryList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductCategoryListRepository extends JpaRepository<ProductCategoryList, Long>  {
    @Query("SELECT p FROM ProductCategoryList p WHERE p.topCategoryCode = :topCategoryCode " +
            "AND p.middleCategoryCode = :middleCategoryCode " +
            "AND p.bottomCategoryCode = :bottomCategoryCode " +
            "AND p.subCategoryCode = :subCategoryCode")
    List<ProductCategoryList> findByCategories(
            @Param("topCategoryCode") String topCategoryCode,
            @Param("middleCategoryCode") String middleCategoryCode,
            @Param("bottomCategoryCode") String bottomCategoryCode,
            @Param("subCategoryCode") String subCategoryCode);
}
