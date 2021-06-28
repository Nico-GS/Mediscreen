package com.mediscreen.notes.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    private String error;
    private String path;
    private int status;
    private Date timestamp;

    public ExceptionResponse (Date ptimestamp, int pstatus, String perror, String ppath) {
        timestamp = ptimestamp;
        status = pstatus;
        error = perror;
        path = ppath;
    }
}
