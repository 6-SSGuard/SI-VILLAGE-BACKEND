package org.example.sivillage.event.infrastructure;

import org.example.sivillage.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e.id FROM Event e WHERE e.categoryCode = :categoryCode")
    List<Long> findByCategoryCode(@Param("categoryCode") String categoryCode);

    @Query("SELECT e.id FROM Event e WHERE e.memberUuid = :memberUuid")
    List<Long> findIdByMemberUuid(@Param("memberUuid") String memberUuid);
}
