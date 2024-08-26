package org.example.sivillage.memberinfo.infrastructure;

import org.example.sivillage.memberinfo.domain.SizeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeInfoRepository extends JpaRepository<SizeInfo, Long>{
    // JpaRepository extends CrudRepository<T,>{
}
