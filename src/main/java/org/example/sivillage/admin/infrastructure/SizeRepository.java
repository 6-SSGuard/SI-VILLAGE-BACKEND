package org.example.sivillage.admin.infrastructure;

import org.example.sivillage.admin.domain.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SizeRepository extends JpaRepository<Size, Long> {
    boolean existsBySizeNameAndSizeType(String sizeName, String sizeType);

    List<Size> findBySizeType(String sizeType);
}
