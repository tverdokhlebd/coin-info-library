package com.tverdokhlebd.coin.info.requestor;

import com.tverdokhlebd.mining.commons.http.ErrorCode;
import com.tverdokhlebd.mining.commons.http.RequestException;

/**
 * Exception for working with coin info requestor.
 *
 * @author Dmitry Tverdokhleb
 *
 */
public class CoinInfoRequestorException extends RequestException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 4078780829401257645L;

    /**
     * Creates instance.
     *
     * @param e request exception
     */
    public CoinInfoRequestorException(RequestException e) {
        super(e.getErrorCode(), e.getMessage());
    }

    /**
     * Creates the instance.
     *
     * @param errorCode error code
     * @param message the detail message
     */
    public CoinInfoRequestorException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    /**
     * Creates the instance.
     *
     * @param errorCode error code
     * @param cause the cause
     */
    public CoinInfoRequestorException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

}
