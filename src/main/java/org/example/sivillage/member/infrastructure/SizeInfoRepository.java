package org.example.sivillage.member.infrastructure;
import org.example.sivillage.member.domain.SizeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SizeInfoRepository extends JpaRepository<SizeInfo, Long>{
    Optional<SizeInfo> findByMemberUuid(String memberUuid);
}
