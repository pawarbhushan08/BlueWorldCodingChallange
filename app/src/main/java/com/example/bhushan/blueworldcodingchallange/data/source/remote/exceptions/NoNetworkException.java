package com.example.bhushan.blueworldcodingchallange.data.source.remote.exceptions;

public class NoNetworkException extends RuntimeException {

    public NoNetworkException() {
        super();
    }

    public NoNetworkException(String message) {
        super(message);
    }

    public NoNetworkException(String message, Throwable cause) {
        super(message, cause);
    }

}