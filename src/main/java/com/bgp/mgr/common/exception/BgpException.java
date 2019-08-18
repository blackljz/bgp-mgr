package com.bgp.mgr.common.exception;

public class BgpException extends RuntimeException {

    /**
     * Instantiates a new service exception.
     */
    public BgpException() {
        super();
    }

    /**
     * Instantiates a new service exception.
     *
     * @param message the message
     */
    public BgpException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new service exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public BgpException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
