package org.example.sivillage.vendor.infrastructure;

import org.example.sivillage.vendor.domain.ProductCategoryList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductCategoryListRepository extends JpaRepository<ProductCategoryList, Long>  {
    @Query("SELECT p.productCode FROM ProductCategoryList p WHERE " +
            "(:topCategoryCode IS NULL OR p.topCategoryCode = :topCategoryCode) AND " +
            "(:middleCategoryCode IS NULL OR p.middleCategoryCode = :middleCategoryCode) AND " +
            "(:bottomCategoryCode IS NULL OR p.bottomCategoryCode = :bottomCategoryCode) AND " +
            "(:subCategoryCode IS NULL OR p.subCategoryCode = :subCategoryCode)")
    List<String> findAllProductCodeByCategories(@Param("topCategoryCode") String topCategoryCode,
                                                @Param("middleCategoryCode") String middleCategoryCode,
                                                @Param("bottomCategoryCode") String bottomCategoryCode,
                                                @Param("subCategoryCode") String subCategoryCode);
}
