package org.example.sivillage.beautyInfo.infrastructure;

import org.example.sivillage.beautyInfo.domain.BeautyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BeautyInfoRepository extends JpaRepository<BeautyInfo, Long> {
    Optional<BeautyInfo> findByMemberUuid(String memberUuid);
}
