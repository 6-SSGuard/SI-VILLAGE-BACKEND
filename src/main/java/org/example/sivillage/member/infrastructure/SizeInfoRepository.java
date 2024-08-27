package org.example.sivillage.member.infrastructure.infrastructure;

import org.example.sivillage.member.domain.SizeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeInfoRepository extends JpaRepository<SizeInfo, Long>{
    // JpaRepository extends CrudRepository<T,>{
}
