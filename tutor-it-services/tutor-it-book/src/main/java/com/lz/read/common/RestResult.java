package com.lz.read.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author :  lz
 * @date :   2020/4/2 12:56
 **/
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Accessors(chain = true)
public class RestResult<T> {
    private Integer statusCode;
    private String message;
    private T data;

    public RestResult(CommonStatus commonStatus, T data) {
        this.statusCode = commonStatus.getStatusCode();
        this.message = commonStatus.getMessage();
        this.data = data;
    }

    public RestResult(String message) {
        this.message = message;
        this.statusCode = CommonStatus.FAILURE.getStatusCode();
    }

    public RestResult(CommonStatus commonStatus) {
        this.statusCode = commonStatus.getStatusCode();
        this.message = commonStatus.getMessage();
    }

    public static <T> RestResult<T> success(T data) {
        return new RestResult<T>(CommonStatus.SUCCESS, data);
    }

    public static RestResult success() {
        return new RestResult<>(CommonStatus.SUCCESS);
    }

    public static RestResult failureOfAuthority() {
        return new RestResult<>(CommonStatus.FAILED_AUTHORITY);
    }

    public static RestResult failureOfNotLogin() {
        return new RestResult<>(CommonStatus.FAILED_NOT_LOGIN);
    }

    public static RestResult failureOfQuery() {
        return new RestResult<>(CommonStatus.FAILURE);
    }

    public static RestResult failure() {
        return new RestResult<>(CommonStatus.FAILURE);
    }
    public static RestResult failureExpert() {
        return new RestResult<>(CommonStatus.EXPERTFAIL);
    }
    public static RestResult failureExpert1() {
        return new RestResult<>(CommonStatus.EXPERTFAIL1);
    }

    public static RestResult failure(String message) {
        return new RestResult<>(message);
    }

    public static RestResult failure(CommonStatus commonStatus) {
        return new RestResult<>(commonStatus);
    }

    public static RestResult failureOfParam() {
        return new RestResult<>(CommonStatus.FAILED_BAD_PARAM);
    }

    public static RestResult failureOfRepeatName() {
        return new RestResult<>(CommonStatus.REPEAT_NAME);
    }

    public static RestResult failureOfCaptcha() {
        return new RestResult<>(CommonStatus.FAILED_CAPTCHA);
    }

    public static RestResult failureOfPassword() {
        return new RestResult<>(CommonStatus.FAILED_PASSWORD);
    }

    public static RestResult failureOfUsername() {
        return new RestResult<>(CommonStatus.FAILED_FOUND_NAME);
    }

}
