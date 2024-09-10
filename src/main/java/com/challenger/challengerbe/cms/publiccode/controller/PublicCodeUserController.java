package com.challenger.challengerbe.cms.publiccode.controller;


import com.challenger.challengerbe.cms.publiccode.dto.PublicCodeDefaultDto;
import com.challenger.challengerbe.cms.publiccode.dto.PublicCodeDto;
import com.challenger.challengerbe.cms.publiccode.service.PublicCodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.routdoo.dailyroutine.cms.publiccode.web
 * fileName       : PublicUserController
 * author         : rhkdg
 * date           : 2023-12-20
 * description    : 공통코드 사용자 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-20        rhkdg       최초 생성
 */
@Tag(name = "공통코드 사용자 컨트롤러")
@RestController
@RequiredArgsConstructor
public class PublicCodeUserController {

    private final PublicCodeService publicCodeService;

    /**
     * 대분류 카테고리 목록 조회
     * @return
     * @throws Exception
     */
    @Operation(summary = "전체 대분류 카테고리 목록 조회(따로 파라미터가 필요없음)")
    @GetMapping("/public/code/tops")
    public List<PublicCodeDto> selectPublicCodeList() throws Exception {
        PublicCodeDefaultDto searchDto = new PublicCodeDefaultDto();
        searchDto.setUseYn("Y");
        searchDto.setParentCd("_TOP");
        return publicCodeService.selectPublicCodeCacheList(searchDto);
    }

    @Operation(summary = "카테고리(키워드) 목록 조회 (사용 가능 목록만 조회)")
    @Parameters({
            @Parameter(name = "parentCd", description = "대분류 코드", example = "KEYWORD(키워드)"),
    })
    @GetMapping("/public/code/list")
    public List<PublicCodeDto> selectPublicCodeList(@Parameter(hidden = true) PublicCodeDefaultDto searchDto) throws Exception {
        searchDto.setUseYn("Y");
        return publicCodeService.selectPublicCodeCacheList(searchDto);
    }



}
