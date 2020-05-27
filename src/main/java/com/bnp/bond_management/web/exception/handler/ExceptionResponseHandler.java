package com.bnp.bond_management.web.exception.handler;

import com.bnp.bond_management.web.exception.ApiException;
import com.bnp.bond_management.web.exception.BondNotFoundException;
import com.bnp.bond_management.web.exception.ExceptionsEnum;
import com.bnp.bond_management.web.exception.error.ApiError;
import com.bnp.bond_management.web.exception.error.ApiObjectError;
import com.bnp.bond_management.web.exception.error.ApiSubError;
import com.bnp.bond_management.web.exception.error.ApiValidationError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Martin Šimek (nitramkemis44@gmail.com)
 * This class handle the ResponseEntityExceptions and other Custom Exceptions, which can be thrown in application
 */
@ControllerAdvice
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError(ExceptionsEnum.REQUEST_PARAMETERS_VALUES_NOT_VALID.getExceptionKey(), "Validation failed");

        List<ApiSubError> subErrors = ex.getBindingResult().getFieldErrors().stream().map(this::mapValidationError).collect(Collectors.toList());
        subErrors.addAll(ex.getBindingResult().getGlobalErrors().stream().map(this::mapGlobalError).collect(Collectors.toList()));

        apiError.setApiSubErrors(subErrors);
        apiError.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(getHeaders(headers)).body(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError(ExceptionsEnum.REQUEST_PARAMETERS_FORMAT_NOT_VALID.getExceptionKey(), ex.getMessage());
        apiError.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(getHeaders(headers)).body(apiError);
    }

    @ExceptionHandler({BondNotFoundException.class})
    public ResponseEntity<Object> handleBondNotFoundException(ApiException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(newApiError(ex, HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleConverterErrors(MethodArgumentTypeMismatchException exception) {
        ApiError apiError = new ApiError(ExceptionsEnum.REQUEST_PARAMETERS_VALUES_NOT_VALID.getExceptionKey(), exception.getMessage());
        apiError.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(getHeaders()).body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex) {
        ApiError apiError = new ApiError(ExceptionsEnum.INTERVIEW_API_ERROR.getExceptionKey(), ex.getMessage());
        apiError.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(getHeaders()).body(apiError);
    }

    private ApiError newApiError(ApiException ex, int statusCode) {
        return new ApiError(statusCode ,ex.getMessageKey(), ex.getMessage());
    }

    private HttpHeaders getHeaders(HttpHeaders headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(headers.getAccept());
        return httpHeaders;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

    private ApiObjectError mapGlobalError(ObjectError objectError) {
        return ApiObjectError.builder()
                .object(objectError.getObjectName())
                .message(objectError.getDefaultMessage())
                .build();
    }

    private ApiSubError  mapValidationError(FieldError fieldError) {
        ApiValidationError apiValidationError = new ApiValidationError();
        apiValidationError.setField(fieldError.getField());
        apiValidationError.setMessage(fieldError.getDefaultMessage());
        apiValidationError.setRejectedValue(fieldError.getRejectedValue());
        apiValidationError.setObject(fieldError.getObjectName());
        return apiValidationError;
    }
}
