package org.example.sivillage.domain.beautyinfo.infrastructure;

import org.example.sivillage.domain.beautyinfo.domain.BeautyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeautyInfoRepository extends JpaRepository<BeautyInfo, Long> {

}
