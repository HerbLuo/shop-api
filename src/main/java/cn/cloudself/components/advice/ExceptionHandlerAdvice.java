package cn.cloudself.components.advice;

import cn.cloudself.exception.http.*;
import cn.cloudself.util.rest.RestErrorBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HerbLuo
 * @version 1.0.0.d
 */
@ControllerAdvice
@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
public class ExceptionHandlerAdvice {

    @Autowired
    private Logger logger;

    private void exHandler(Exception ex) {
        logger.error("Exception handled in ExceptionHandlerAdvice", ex);
    }

    /*
     * 4xx
     */
    @ExceptionHandler(RequestBadException.class)
    @ResponseBody
    public ResponseEntity<RestErrorBean> requestBad(Exception exception) {
        exHandler(exception);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new RestErrorBean(status.value(), exception.getMessage()), status);
    }

    @ExceptionHandler(RequestTooLargeException.class)
    @ResponseBody
    public ResponseEntity<RestErrorBean> requestTooLarge(Exception exception) {
        exHandler(exception);
        HttpStatus status = HttpStatus.PAYLOAD_TOO_LARGE;
        return new ResponseEntity<>(new RestErrorBean(status.value(), exception.getMessage()), status);
    }

    @ExceptionHandler(RequestTooManyException.class)
    @ResponseBody
    public ResponseEntity<RestErrorBean> requestTooMany(Exception exception) {
        exHandler(exception);
        HttpStatus status = HttpStatus.TOO_MANY_REQUESTS;
        return new ResponseEntity<>(new RestErrorBean(status.value(), exception.getMessage()), status);
    }

    @ExceptionHandler(IAmATeapot.class)
    @ResponseBody
    public ResponseEntity<RestErrorBean> iAmATeapot(Exception exception) {
        exHandler(exception);
        HttpStatus status = HttpStatus.I_AM_A_TEAPOT;
        return new ResponseEntity<>(new RestErrorBean(status.value(), exception.getMessage()), status);
    }

    @ExceptionHandler(RequestUnauthorizedException.class)
    @ResponseBody
    public ResponseEntity<RestErrorBean> unauthorized(Exception exception) {
        exHandler(exception);
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        return new ResponseEntity<>(new RestErrorBean(status.value(), exception.getMessage()), status);
    }

    @ExceptionHandler(RequestNotFindException.class)
    @ResponseBody
    public ResponseEntity<RestErrorBean> requestNotFind(Exception exception) {
        exHandler(exception);
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(new RestErrorBean(status.value(), exception.getMessage()), status);
    }


    /*
     * 5xx
     */
    @ExceptionHandler(ServerException.class)
    @ResponseBody
    public ResponseEntity<RestErrorBean> internalServerError(Exception exception) {
        exHandler(exception);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(new RestErrorBean(status.value(), exception.getMessage()), status);
    }

    /**
     * 未实现
     */
    @ExceptionHandler(ServerNotImplementedException.class)
    @ResponseBody
    public ResponseEntity<RestErrorBean> notImplement(Exception exception) {
        exHandler(exception);
        HttpStatus status = HttpStatus.NOT_IMPLEMENTED;
        return new ResponseEntity<>(new RestErrorBean(status.value(), exception.getMessage()), status);
    }

    @ExceptionHandler(ServerUnavailableException.class)
    @ResponseBody
    public ResponseEntity<RestErrorBean> serverUnavailable(Exception exception) {
        exHandler(exception);
        HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
        return new ResponseEntity<>(new RestErrorBean(status.value(), exception.getMessage()), status);
    }



    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<RestErrorBean> exceptionAll(Exception exception) {
        exHandler(exception);

        HttpStatus status = map.get(exception.getClass());
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(new RestErrorBean(status.value(), exception.getMessage()), status);
    }

    private Map<Class<? extends Exception>, HttpStatus> map = new HashMap<>();
    {
        map.put(HttpMediaTypeNotAcceptableException.class, HttpStatus.NOT_ACCEPTABLE);
        map.put(HttpMediaTypeNotSupportedException.class, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        map.put(HttpMessageNotReadableException.class, HttpStatus.BAD_REQUEST);
        map.put(HttpMessageNotWritableException.class, HttpStatus.INTERNAL_SERVER_ERROR);
        map.put(HttpRequestMethodNotSupportedException.class, HttpStatus.METHOD_NOT_ALLOWED);
    }


}
