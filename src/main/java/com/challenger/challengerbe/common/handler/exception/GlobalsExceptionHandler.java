package com.challenger.challengerbe.common.handler.exception;

import com.challenger.challengerbe.common.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * packageName    : com.challenger.challengerbe.common.handler.exception
 * fileName       : GlobalsExceptionHandler
 * author         : rhkdg
 * date           : 2024-09-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-11        rhkdg       최초 생성
 */
@ControllerAdvice
public class GlobalsExceptionHandler extends ResponseEntityExceptionHandler {

    /***
     * Validation Exception
     * @param e
     * @return
     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public final ResponseEntity<?> handlerMethodValidationException (MethodArgumentNotValidException e) {
//        CommonResponse<?> response = new CommonResponse<>(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
}
