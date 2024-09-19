package com.challenger.challengerbe.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * packageName    : com.challenger.challengerbe.common.annotation
 * fileName       : FileUploadAction
 * author         : rhkdg
 * date           : 2024-09-15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-15        rhkdg       최초 생성
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileUploadAction {
}
