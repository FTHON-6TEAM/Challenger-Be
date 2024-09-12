package com.challenger.challengerbe.common.exception;

/**
 * packageName    : com.challenger.challengerbe.common.exception
 * fileName       : AlreadyExistException
 * author         : rhkdg
 * date           : 2024-09-12
 * description    : 이미 존재하는 데이터에 대한 Exception
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-12        rhkdg       최초 생성
 */
public class AlreadyExistException extends  Exception{
    public AlreadyExistException(String message ){
        super(message);
    }
}
