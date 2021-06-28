package com.mediscreen.rapport.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new ErrorDecoder.Default();
    private final String ResourceNotFoundException = "Rapport not found";

    @Override
    public Exception decode (String invoker, Response response) {
        if(response.status() == 404) {
            return new ResourceNotFoundException(ResourceNotFoundException);
        }
        return defaultErrorDecoder.decode(invoker, response);
    }

}
