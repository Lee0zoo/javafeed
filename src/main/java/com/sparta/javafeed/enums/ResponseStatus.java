package com.sparta.javafeed.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseStatus {
    // 상수 지정 패키지 - constant,

    SIGN_UP_SUCCESS(HttpStatus.OK, "회원가입에 성공 했습니다."),
    DEACTIVATE_USER_SUCCESS(HttpStatus.OK, "회원탈퇴 완료."),
    LOGIN_SUCCESS(HttpStatus.OK, "로그인에 성공 했습니다."),
    LOGOUT_SUCCESS(HttpStatus.OK, "로그아웃에 성공 했습니다."),
    PROFILE_UPDATE_SUCCESS(HttpStatus.OK, "프로필이 수정 되었습니다."),
    PASSWORD_UPDATE_SUCCESS(HttpStatus.OK, "비밀번호가 수정 되었습니다."),
    POST_CREATE_SUCCESS(HttpStatus.OK, "게시글이 작성 되었습니다."),
    POST_UPDATE_SUCCESS(HttpStatus.OK, "게시글이 수정 되었습니다."),
    POST_DELETE_SUCCESS(HttpStatus.OK, "게시글이 삭제 되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}