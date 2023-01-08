package edu.coldrain.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    // 에러 발생 시간
    private LocalDateTime timestamp;
    private String message;
    private Integer status;

    public ErrorResponse(final String message, final Integer status) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
