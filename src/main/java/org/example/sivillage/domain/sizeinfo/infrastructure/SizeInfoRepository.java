package org.example.sivillage.domain.sizeinfo.infrastructure;

import org.example.sivillage.domain.sizeinfo.domain.SizeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeInfoRepository extends JpaRepository<SizeInfo, Long>{
    // JpaRepository extends CrudRepository<T,>{
}
