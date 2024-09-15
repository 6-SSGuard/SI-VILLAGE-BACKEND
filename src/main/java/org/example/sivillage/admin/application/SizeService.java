package org.example.sivillage.admin.application;

import org.example.sivillage.admin.dto.in.AddSizeRequestDto;
import org.example.sivillage.admin.dto.in.ChangeSizeRequestDto;
import org.example.sivillage.admin.dto.out.GetSizeResponseDto;

public interface SizeService {

    void addSize(AddSizeRequestDto addSizeRequestDto);

    GetSizeResponseDto getSize(Long id);

    void changeSize(ChangeSizeRequestDto changeSizeRequestDto);

    void removeSize(Long id);
}
