package org.example.sivillage.global.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // 매칭 전략 설정 (STRICT는 모든 필드명이 정확히 일치해야 함)
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        // Null 값이 아닌 필드만 매핑
        // modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        // 깊은 복사 활성화
        modelMapper.getConfiguration().setDeepCopyEnabled(true);
        // 필드 접근 허용 (getters/setters 대신 직접 필드에 접근)
        modelMapper.getConfiguration().setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        return modelMapper;
    }
}
