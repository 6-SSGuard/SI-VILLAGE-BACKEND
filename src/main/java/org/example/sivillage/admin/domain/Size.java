package org.example.sivillage.admin.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.sivillage.global.common.BaseEntity;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "size", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"sizeName"})
})
public class Size extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String sizeName;

    @Column(nullable = false, length = 50)
    private String sizeTypeValue;

    @Builder
    public Size(String sizeName, String sizeTypeValue) {
        this.sizeName = sizeName;
        this.sizeTypeValue = sizeTypeValue;
    }
}
