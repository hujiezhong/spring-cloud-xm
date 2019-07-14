package com.whkobe.exception;

import com.whkobe.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PmException extends  RuntimeException {
    private ExceptionEnum exceptionEnum;
}
