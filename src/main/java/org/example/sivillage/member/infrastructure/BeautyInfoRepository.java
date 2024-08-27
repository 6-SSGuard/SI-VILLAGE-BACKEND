package org.example.sivillage.member.infrastructure.infrastructure;

import org.example.sivillage.member.domain.BeautyInfo;
import org.example.sivillage.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BeautyInfoRepository extends JpaRepository<BeautyInfo, Long> {
    Optional<BeautyInfo> findByMemberUuid(String memberUuid);
}
