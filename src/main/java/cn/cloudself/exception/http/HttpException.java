package cn.cloudself.exception.http;

import java.security.PrivilegedActionException;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class HttpException extends Exception {

    private int code;

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public HttpException() {
        super();
    }

    public HttpException(int code) {
        super();
        this.code = code;
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public HttpException(String message) {
        super(message);
    }

    public HttpException(String message, int code) {
        super(message);
        this.code = code;
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     * @since 1.4
     */
    public HttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public HttpException(Throwable cause) {
        super(cause);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
