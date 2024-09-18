package org.example.sivillage.brand.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.sivillage.brand.domain.Brand;
import org.example.sivillage.brand.dto.in.AddBrandRequestDto;
import org.example.sivillage.brand.dto.out.GetBrandNameResponseDto;
import org.example.sivillage.brand.infrastructure.BrandLikeRepository;
import org.example.sivillage.brand.infrastructure.BrandRepository;
import org.example.sivillage.global.common.response.BaseResponseStatus;
import org.example.sivillage.global.common.response.dto.IdListResponseDto;
import org.example.sivillage.global.error.BaseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandLikeRepository brandLikeRepository;

    /**
     * BrandServiceImpl
     * 1. 브랜드 추가
     * 2. 브랜드 id 목록 조회
     * 3. 브랜드 이름 조회 (브랜드 영어 명, 한국어 명)
     * 4. 브랜드 좋아요 여부 조회
     * 5. 브랜드 좋아요 토글
     */

    /**
     * 1. 브랜드 추가
     * @param addBrandRequestDto
     * return void
     */
    @Override
    public void addBrand(AddBrandRequestDto addBrandRequestDto) {
        validateDuplicatedBrand(addBrandRequestDto);

        Brand brand = addBrandRequestDto.toEntity();
        brandRepository.save(brand);
    }

    private void validateDuplicatedBrand(AddBrandRequestDto addBrandRequestDto) {
        boolean exist = brandRepository.existsByBrandEngNameOrBrandKorName(addBrandRequestDto.getBrandEngName(), addBrandRequestDto.getBrandKorName());
        if (exist) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_BRAND_NAME);
        }
    }

    /**
     * 2. 브랜드 id 목록 조회
     * @param memberUuid 회원 UUID
     * return GetBrandIdListResponseDto
     */
    @Transactional(readOnly = true)
    @Override
    public List<IdListResponseDto<Long>> getBrandIdList(String memberUuid) {

        return brandRepository.findAllBrandIdsByOrderByEngNameAsc()
                .stream()
                .map(IdListResponseDto::from)
                .toList();
    }

    /**
     * 3. 브랜드 이름 조회(브랜드 영어 명, 한국어 명)
     * @param brandId 브랜드 ID
     * return GetBrandNameResponseDto
     */
    @Transactional(readOnly = true)
    @Override
    public GetBrandNameResponseDto getBrandName(Long brandId) {

        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.BRAND_NOT_FOUND));

        return GetBrandNameResponseDto.builder()
                .brandEngName(brand.getBrandEngName())
                .brandKorName(brand.getBrandKorName())
                .build();
    }

    @Override
    public void addBrandFromCsv(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            List<CSVRecord> records = csvParser.getRecords();
            for (CSVRecord record : records) {
                String brandEngName = record.get("brand_eng_name"); // CSV 컬럼 이름 사용
                String brandKorName = record.get("brand_kor_name");

                // 중복 체크
                if (brandRepository.existsByBrandEngName(brandEngName) || brandRepository.existsByBrandKorName(brandKorName)) {
                    continue; // 이미 존재하는 브랜드일 경우 건너뜀
                }

                // 브랜드 엔티티 생성 및 저장
                Brand brand = Brand.builder()
                        .brandEngName(brandEngName)
                        .brandKorName(brandKorName)
                        .build();
                brandRepository.save(brand);
            }
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
