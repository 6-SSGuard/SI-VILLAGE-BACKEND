package org.example.sivillage.admin.initializer;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.admin.application.SizeService;
import org.example.sivillage.admin.dto.in.AddSizeRequestDto;
import org.example.sivillage.admin.infrastructure.SizeRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SizeInitializer implements ApplicationRunner {

    private final SizeService sizeService;
    private final SizeRepository sizeRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // Shoes 사이즈 추가 (중복 방지)
        for (int size = 180; size <= 300; size += 10) {
            if (!sizeRepository.existsBySizeNameAndSizeType(String.valueOf(size), "shoes")) {
                AddSizeRequestDto sizeDto = AddSizeRequestDto.builder()
                        .sizeName(String.valueOf(size))
                        .sizeType("shoes")
                        .build();
                sizeService.addSize(sizeDto);
            }
        }

        // Clothes 사이즈 추가 (중복 방지)
        String[] clothesSizes = {"XXS", "XS", "S", "M", "L", "XL", "XXL", "XXXL"};
        for (String size : clothesSizes) {
            if (!sizeRepository.existsBySizeNameAndSizeType(size, "clothes")) {
                AddSizeRequestDto sizeDto = AddSizeRequestDto.builder()
                        .sizeName(size)
                        .sizeType("clothes")
                        .build();
                sizeService.addSize(sizeDto);
            }
        }

    }
}
