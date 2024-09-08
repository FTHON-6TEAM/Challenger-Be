package com.challenger.challengerbe.cms.file;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * packageName    : com.challenger.challengerbe.cms.file
 * fileName       : CmsFileThreadLocalHolder
 * author         : rhkdg
 * date           : 2024-09-08
 * description    : CMS FILE 파일 Request thread 관리 저장소
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-08        rhkdg       최초 생성
 */
public class CmsFileThreadLocalHolder {
    private static final ThreadLocal<MultipartHttpServletRequest> MULTIPART_THREAD_LOCAL = new ThreadLocal<>();

    public static void setRequest(HttpServletRequest request) {
        try{
            if(request instanceof  MultipartHttpServletRequest) {
                MULTIPART_THREAD_LOCAL.set((MultipartHttpServletRequest) request);
            }
        }catch (Exception e) {
            MULTIPART_THREAD_LOCAL.remove();;
        }
    }

    public static MultipartHttpServletRequest getRequest(){
        return MULTIPART_THREAD_LOCAL.get();
    }

    public static void clear(){
        MULTIPART_THREAD_LOCAL.remove();;
    }

}
