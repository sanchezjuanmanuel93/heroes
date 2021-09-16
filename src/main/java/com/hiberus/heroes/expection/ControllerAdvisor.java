package com.hiberus.heroes.expection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus httpStatus,
            WebRequest request) {
        return buildBadRequestResponse(ex, request, httpStatus);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        return buildBadRequestResponse(ex, request, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        return buildBadRequestResponse(ex, request, status);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            @Nullable Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        Map<String, Object> bodyResponse =
                buildResponse(
                        ExceptionStatus.INTERNAL_ERROR,
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ex.getMessage(),
                        request,
                        ex);
        return new ResponseEntity<>(bodyResponse, status);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> globalExceptionHandler(Exception ex, WebRequest request) {
        Map<String, Object> bodyResponse =
                buildResponse(
                        ExceptionStatus.INTERNAL_ERROR,
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ex.getMessage(),
                        request,
                        ex);
        return new ResponseEntity<>(bodyResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HeroAccessException.class)
    private ResponseEntity<Object> HeroAccessExceptionHandler(HeroAccessException ex) {
        Map<String, Object> body =
                buildResponse(
                        ExceptionStatus.USER_HAS_NO_ACCESS, HttpStatus.FORBIDDEN, null, null, ex);
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InconsistentDataException.class)
    public ResponseEntity<Object> handleInconsistentDataException(InconsistentDataException ex) {
        final Map<String, Object> bodyResponse =
                buildResponse(
                        ExceptionStatus.INCONSISTENT_DATA, HttpStatus.BAD_REQUEST, ex.getMessage(), null, ex);
        return new ResponseEntity<>(bodyResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        final Map<String, Object> bodyResponse =
                buildResponse(
                        ExceptionStatus.DOCUMENT_NOT_FOUND, HttpStatus.NOT_FOUND, ex.getMessage(), null, ex);
        return new ResponseEntity<>(bodyResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<Object> handleGenericException(GenericException ex) {
        final Map<String, Object> bodyResponse =
                buildResponse(
                        ExceptionStatus.ERROR, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null, ex);
        return new ResponseEntity<>(bodyResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        final Map<String, Object> bodyResponse =
                buildResponse(
                        ExceptionStatus.INTERNAL_ERROR,
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ex.getMessage(),
                        null,
                        ex);
        return new ResponseEntity<>(bodyResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> buildBadRequestResponse(
            Exception ex, WebRequest request, HttpStatus httpStatus) {
        Map<String, Object> body =
                buildResponse(
                        ExceptionStatus.BAD_INPUT, httpStatus, "The request is not valid", request, ex);

        if (ex instanceof MethodArgumentNotValidException) {
            List<String> errors =
                    ((MethodArgumentNotValidException) ex)
                            .getBindingResult().getFieldErrors().stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .collect(Collectors.toList());
            body.put("errors", errors);
        }
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    private Map<String, Object> buildResponse(
            ExceptionStatus exStatus,
            HttpStatus httpStatus,
            String message,
            WebRequest request,
            Exception originalException) {
        log.error("Exception caught: ", originalException);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", ZonedDateTime.now());
        body.put("httpStatus", httpStatus.value());
        body.put("status", exStatus);
        body.put("message", message);
        if (request != null) {
            body.put("path", request.getDescription(false));
        }
        return body;
    }

}