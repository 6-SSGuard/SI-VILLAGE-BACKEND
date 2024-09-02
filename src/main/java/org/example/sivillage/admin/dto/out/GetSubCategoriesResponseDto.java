package org.example.sivillage.admin.dto.out;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetSubCategoriesResponseDto {
    private List<CategoryDto> categories;
}
