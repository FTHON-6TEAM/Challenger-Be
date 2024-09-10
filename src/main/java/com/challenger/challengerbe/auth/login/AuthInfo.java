package com.challenger.challengerbe.auth.login;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * packageName    : com.challenger.challengerbe.auth.login
 * fileName       : AuthRedirect
 * author         : rhkdg
 * date           : 2024-09-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-11        rhkdg       최초 생성
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthInfo {
}
