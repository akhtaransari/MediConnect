package com.xcelore.mediconnect.exception;

/**
 * Custom runtime exception class for handling exceptions specific to the MediConnect application.
 */
public class MediConnectException extends RuntimeException {

    /**
     * Constructs a new MediConnectException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public MediConnectException(String message) {
        super(message);
    }
}
