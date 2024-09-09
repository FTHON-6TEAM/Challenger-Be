package com.challenger.challengerbe.cms.publiccode.controller;


import com.challenger.challengerbe.cms.publiccode.dto.PublicCodeDefaultDto;
import com.challenger.challengerbe.cms.publiccode.dto.PublicCodeDto;
import com.challenger.challengerbe.cms.publiccode.service.PublicCodeService;
import com.challenger.challengerbe.common.BaseController;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.routdoo.dailyroutine.cms.publiccode.web
 * fileName       : PublicCodeController
 * author         : GAMJA
 * date           : 2024/09/08
 * description    : 공통 코드 관리 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/09/08        GAMJA       최초 생성
 */
@Tag(name = "공통 코드 관리 컨트롤러")
@RestController
@RequiredArgsConstructor
public class PublicCodeController extends BaseController {

    private final PublicCodeService publicCodeService;

    /**
     * 공통 코드 목록 (페이징)
     * @param searchDto
     * @return
     * @throws Exception
     */
    @GetMapping(MGN_URL+"/public/code/list")
    public List<PublicCodeDto> selectPublicCodePageList(@Parameter(hidden = true) PublicCodeDefaultDto searchDto) throws Exception {
        return publicCodeService.selectPublicCodeList(searchDto);
    }

    /**
     * 공통 코드 상세 조회
     * @param publicCodeDto
     * @return
     * @throws Exception
     */
    @GetMapping(MGN_URL+"/public/code/view")
    public PublicCodeDto selectPublicCodeView(@Parameter(hidden = true) PublicCodeDto publicCodeDto) throws Exception {
        return publicCodeService.selectPublicCodeView(publicCodeDto);
    }

    /**
     * 등록
     * @param publicCodeDto
     * @return
     * @throws Exception
     */
    @PostMapping(MGN_URL+"/public/code/ins")
    public ResponseEntity<String> insertPublicCode(PublicCodeDto publicCodeDto) throws Exception {

        try{
            boolean result = publicCodeService.insertPublicCode(publicCodeDto);
            if(!result){
                return new ResponseEntity<>("등록에 실패하였습니다.", HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }catch (Exception e ){
            logger.error("## insert public code error : {}",e.getMessage());
            return new ResponseEntity<>("등록시 오류가 발생하였습니다.",HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>("등록 되었습니다.",HttpStatus.OK);
    }

    /**
     * 수정
     * @param publicCodeDto
     * @return
     * @throws Exception
     */
    @PostMapping(MGN_URL+"/public/code/upd")
    public ResponseEntity<String> updatePublicCode(PublicCodeDto publicCodeDto) throws Exception {

        try{
            boolean result = publicCodeService.updatePublicCode(publicCodeDto);
            if(!result){
                return new ResponseEntity<>("수정에 실패하였습니다.", HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }catch (Exception e ){
            logger.error("## update public code error : {}",e.getMessage());
            return new ResponseEntity<>("수정시 오류가 발생하였습니다.",HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>("수정 되었습니다.",HttpStatus.OK);
    }

    /**
     * 삭제
     * @param publicCodeDto
     * @return
     * @throws Exception
     */
    @PostMapping(MGN_URL+"/public/code/del")
    public ResponseEntity<String> deletePublicCode(PublicCodeDto publicCodeDto) throws Exception {

        try{
            boolean result = publicCodeService.deletePublicCode(publicCodeDto);
            if(!result){
                return new ResponseEntity<>("삭제에 실패하였습니다.", HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }catch (Exception e ){
            logger.error("## delete public code error : {}",e.getMessage());
            return new ResponseEntity<>("삭제시 오류가 발생하였습니다.",HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>("삭제 되었습니다.",HttpStatus.OK);
    }

    @PostMapping(MGN_URL+"/public/code/ord")
    public ResponseEntity<String> updatePublicCodeOrd(@RequestParam("pubCds[]") List<String> pubCdList) throws Exception {

        try{
            publicCodeService.updatePublicCodeOrdBatch(pubCdList);
        }catch (Exception e){
            logger.error("## update public code ord batch error : {}",e.getMessage());
            return new ResponseEntity<>("정렬 업데이트시 오류가 발생하였습니다.",HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>("수정되었습니다.",HttpStatus.OK);
    }
}
