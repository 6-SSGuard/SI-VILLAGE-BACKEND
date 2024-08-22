package org.example.sivillage.domain.beautyinfo.application;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.sivillage.domain.beautyinfo.domain.BeautyInfo;
import org.example.sivillage.domain.beautyinfo.dto.BeautyInfoRequest;
import org.example.sivillage.domain.beautyinfo.infrastructure.BeautyInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BeautyInfoService {

    @Autowired
    BeautyInfoRepository beautyInfoRepository;

    }

