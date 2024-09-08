package com.challenger.challengerbe.cms.upload.service;

import com.challenger.challengerbe.cms.upload.domain.FileUpload;
import com.challenger.challengerbe.cms.upload.dto.FileUploadDefaultDto;
import com.challenger.challengerbe.cms.upload.dto.FileUploadDto;
import com.challenger.challengerbe.cms.upload.repository.FileUploadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : com.routdoo.dailyroutine.cms.upload.service
 * fileName       : FileUploadService
 * author         : rhkdg
 * date           : 2024-04-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-22        rhkdg       최초 생성
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileUploadService {

    private final FileUploadRepository fileUploadRepository;

    /**
     * 상세
     * @param dto
     * @return
     * @throws Exception
     */
    public FileUploadDto selectFileUpload(FileUploadDto dto) throws Exception {
        FileUpload fileUpload = dto.toEntity();
        if(dto.getUseYn() != null && !dto.getUseYn().isEmpty()){
            fileUpload = fileUploadRepository.findByCodeAndUseYn(fileUpload.getCode(), fileUpload.getUseYn());
        }else{
            fileUpload =fileUploadRepository.findByCode(fileUpload.getCode());
        }

        return fileUpload == null ? null : new FileUploadDto(fileUpload);
    }

    /**
     * 등록/ 수정
     * @param dto
     * @throws Exception
     */
    @Transactional
    public void saveFileUpload(FileUploadDto dto) throws Exception {
        fileUploadRepository.save(dto.toEntity());
    }

    /**
     * 삭제
     * @param dto
     * @throws Exception
     */
    @Transactional
    public void deleteFileUpload(FileUploadDto dto) throws Exception {
        fileUploadRepository.delete(dto.toEntity());
    }

}
