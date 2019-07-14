package com.whkobe.advice;

import com.whkobe.enums.ExceptionEnum;
import com.whkobe.exception.PmException;
import com.whkobe.vo.ExceptionResult;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandle {
    @ExceptionHandler(PmException.class)
    public ResponseEntity<ExceptionResult> handleException(PmException e){
        val em=e.getExceptionEnum();
        return ResponseEntity.status(em.getCode()).body(new ExceptionResult(em));
    }

}
