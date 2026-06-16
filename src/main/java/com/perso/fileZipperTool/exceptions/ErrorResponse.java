package com.perso.fileZipperTool.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private int status;
}

