package org.example.sivillage.admin.infrastructure;


import org.example.sivillage.admin.domain.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColorRepository extends JpaRepository<Color, Long> {
    Optional<Color> findByColorCode(String colorCode);
}
