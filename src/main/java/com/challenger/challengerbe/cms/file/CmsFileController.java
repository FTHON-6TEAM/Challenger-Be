package com.challenger.challengerbe.cms.file;

import com.challenger.challengerbe.cms.file.domain.CmsFile;
import com.challenger.challengerbe.cms.file.dto.CmsFileDto;
import com.challenger.challengerbe.cms.file.service.CmsFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.beans.Encoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * packageName    : com.challenger.challengerbe.cms.file
 * fileName       : CmsFileController
 * author         : rhkdg
 * date           : 2024-09-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-12        rhkdg       최초 생성
 */
@Tag(name = "CMS 파일 처리 컨트롤러")
@RestController
@RequiredArgsConstructor
public class CmsFileController {

    private final CmsFileService cmsFileService;

    @Value("${globals.upload.path}")
    private String uploadPath;

    @Operation(summary = "이미지 링크 파일 생성 API")
    @Parameter(name = "idx", description = "파일 일련번호에 해당")
    @GetMapping(value = "/cms/file/image/link")
    public ResponseEntity<?> convertImage(@RequestParam("idx") Long idx) throws Exception {

        CmsFileDto cmsFileVO = new CmsFileDto();
        cmsFileVO.setIdx(idx);
        cmsFileVO = cmsFileService.selectCmsFile(cmsFileVO);

        if(cmsFileVO == null) {
            return ResponseEntity.notFound().build();
        }
        System.out.println(uploadPath+cmsFileVO.getSaveFilePath());
        Path filePath = Paths.get(uploadPath+cmsFileVO.getSaveFilePath()).resolve(cmsFileVO.getSaveFileName()).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        // 파일이 존재하고, 읽을 수 있는지 확인합니다.
        if (resource.exists() && resource.isReadable()) {
            // 이미지의 Content-Type을 설정합니다.
            String contentType = "image/jpeg"; // 필요시 다른 타입 설정

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + URLEncoder.encode(Objects.requireNonNull(resource.getFilename()),StandardCharsets.UTF_8) + "\"")
                    .body(resource);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
