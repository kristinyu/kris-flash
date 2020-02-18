package com.kris.edu.framwork.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class EduException extends RuntimeException {
  
    private int code = 0;

    public EduException(int code) {
        this.code = code;
    }

    public EduException(String message) {
        super(message);
    }

    public EduException(int code, String message) {
        super(message);
        this.code = code;
    }

    public EduException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public EduException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }
}
