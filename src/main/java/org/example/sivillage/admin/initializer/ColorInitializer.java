package org.example.sivillage.admin.initializer;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.admin.application.ColorService;
import org.example.sivillage.admin.dto.in.AddColorRequestDto;
import org.example.sivillage.admin.infrastructure.ColorRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ColorInitializer implements ApplicationRunner {

    private final ColorService colorService;
    private final ColorRepository colorRepository;

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 색상 코드별 색상 이름 배열
        Object[][] colorGroups = {
                {"yellow", new String[]{"yellow", "lemon", "gold",
                        "ocher", "golden yellow",
                        "beige", "Khaki",
                        "pale lemon", "mustard"}},
                {"blue", new String[]{"blue", "sky blue", "navy"}},
                {"red", new String[]{"red", "wine", "burgundy"}},
                {"green", new String[]{"green", "mint", "olive"}},
                {"black", new String[]{"black", "charcoal", "midnight"}}
        };

        // 각 색상 그룹에 대해 DTO 생성 후 저장
        for (Object[] group : colorGroups) {
            String colorCode = (String) group[0];  // 색상 코드
            String[] colorNames = (String[]) group[1];  // 색상 이름 배열

            for (String colorName : colorNames) {
                if (!colorRepository.existsByColorName(colorName)) {  // 중복 확인
                    AddColorRequestDto colorDto = AddColorRequestDto.builder()
                            .colorCode(colorCode)
                            .colorName(colorName)
                            .build();
                    colorService.addColor(colorDto);
                }
            }
        }
    }
}
