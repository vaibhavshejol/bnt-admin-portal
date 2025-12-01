package com.bnt.common;
/**************************
 * @author nilofar.shaikh *
 **************************/

import org.springframework.http.HttpStatus;

import com.renovite.ripps.ap.enums.Errorcodes;

public class RippsAdminException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final ErrorCodes codes;

    private final String errorMessage;

    private final String message;

    private final HttpStatus httpStatus;

    public RippsAdminException(String message) {
        super(message);
        this.codes = ErrorCodes.SYSTEM_ERROR;
        this.errorMessage = codes.getMessage();
        this.message = message;
        this.httpStatus = HttpStatus.ACCEPTED;
    }

    /**
     * Instantiates a new ripps admin exception.
     *
     * @param codes the codes
     * @param th    the th
     */
    public RippsAdminException(ErrorCodes codes, Throwable th) {
        this.codes = codes;
        this.errorMessage = codes.getMessage();
        this.message = "sds";
        this.httpStatus = HttpStatus.ACCEPTED;
    }

    public RippsAdminException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.codes = ErrorCodes.SYSTEM_ERROR;
        this.errorMessage = codes.getMessage();
    }

    public ErrorCodes getCodes() {
        return codes;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
