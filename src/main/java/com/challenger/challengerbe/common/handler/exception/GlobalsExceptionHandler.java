package com.challenger.challengerbe.common.handler.exception;

import com.challenger.challengerbe.common.CommonResponse;
import com.challenger.challengerbe.common.exception.AlreadyExistException;
import com.challenger.challengerbe.common.exception.AlreadyUseException;
import org.springframework.core.annotation.Order;
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

    /**
     * 이미 존재 여부
     * @param e
     * @return
     */
    @ExceptionHandler(AlreadyExistException.class)
    public final ResponseEntity<?> handlerAlreadyException (AlreadyExistException e) {
        CommonResponse<?> response = new CommonResponse<>(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
    }

    /**
     * 이미 사용 여부
     * @param e
     * @return
     */
    @ExceptionHandler(AlreadyUseException.class)
    public final ResponseEntity<?> handlerAlreadyException (AlreadyUseException e) {
        CommonResponse<?> response = new CommonResponse<>(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handlerAlreadyException (Exception e) {
        CommonResponse<?> response = new CommonResponse<>(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
