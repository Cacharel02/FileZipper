package com.perso.fileZipperTool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoUploadedFileException extends RuntimeException {
    public NoUploadedFileException() {
        super("vous devez sélectionner au moins un fichier");
    }
}

