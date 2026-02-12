package com.movieflix.movieApi.exceptions;

import java.io.File;

public class FileExistsException extends RuntimeException{
    public FileExistsException(String message){
        super(message);
    }
}
