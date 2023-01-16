package edu.coldrain.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime timestamp;
    private Integer status;

    private List<CustomFieldError> errors = new ArrayList<>();


    // 일반 에러 생성자
    public ErrorResponse(final String message, final HttpStatus status) {
        this.message = message;
        this.status = status.value();
        this.timestamp = LocalDateTime.now();
    }

    // Bean Validation 에러 생성자
    public ErrorResponse(final HttpStatus status, final BindingResult bindingResult) {
        this.message = "";
        this.status = status.value();
        this.timestamp = LocalDateTime.now();
        this.errors = this.toCustomFieldErrors(bindingResult.getFieldErrors());
    }

    private List<CustomFieldError> toCustomFieldErrors(final List<FieldError> fieldErrors) {
        return fieldErrors
                .stream()
                .map((fieldError) -> CustomFieldError.builder()
                        .field(fieldError.getField())
                        .rejectedValue((String) fieldError.getRejectedValue())
                        .reason(fieldError.getDefaultMessage())
                        .build())
                .toList();
    }

}