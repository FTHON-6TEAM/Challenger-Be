package com.challenger.challengerbe.cms.file;

import com.challenger.challengerbe.common.annotation.FileUploadAction;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

/**
 * packageName    : com.routdoo.dailyroutine.cms.file
 * fileName       : CmsFileThreadLocalIntercepter
 * author         : GAMJA
 * date           : 2024/09/08
 * description    : cms file multipartrequest interceptor
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/09/08        GAMJA       최초 생성
 */
@Component
public class CmsFileThreadLocalInterceptor implements HandlerInterceptor {

    // preHandle -> Controller -> postHandle -> 화면처리 -> afterCompletion

    // 컨트롤러 가기 전 실행
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 어노테이션 FileUploadAction 어노테이션일 경오에만 인터셉터가 타도록 수정
        if(handler instanceof HandlerMethod handlerMethod) {
            Method method = handlerMethod.getMethod();
            if(method.isAnnotationPresent(FileUploadAction.class)){
                System.out.println("------------------------ cms file support intercepter ----------------------------");
                CmsFileThreadLocalHolder.setRequest(request);
            }
        }
        return true;
    }

    // 화면 처리가 끝난 뒤 실행
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        CmsFileThreadLocalHolder.clear();
    }

}
