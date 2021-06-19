package com.iti.jets.reportingsystem.exceptions;

import com.iti.jets.openapi.model.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ErrorMessage> handleApiException(ApiException ex, WebRequest request) {
        System.out.println("inside handleMethodArgumentNotValidException========================== ");
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorMessage(ex.getMessage());
        errorMessage.setErrorCode(ex.getHttpStatus().value());
        errorMessage.setErrorDescription(ex.getDescription());
        return new ResponseEntity<>(errorMessage, ex.getHttpStatus());
    }
//    @ExceptionHandler({MethodArgumentNotValidException.class})
//    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
//        System.out.println("inside handleMethodArgumentNotValidException ");
//        ErrorMessage errorMessage = new ErrorMessage();
//        errorMessage.setErrorMessage(ex.getMessage());
//        errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.value());
//        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
//    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder builder = new StringBuilder();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError ->
                        builder.append(String.format("|'%s'|%s", fieldError.getField(),
                                fieldError.getDefaultMessage())));

        String error = builder.toString();
        ErrorDetails errorDetails = buildErrorDetails(error, request.getDescription(false), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    private ErrorDetails buildErrorDetails(String message, String uri, HttpStatus status, String error) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setError(error);
        errorDetails.setDate(LocalDateTime.now().toString());
        errorDetails.setMessage(message);
        errorDetails.setUri(uri);
        errorDetails.setStatus("" + status.value());
        errorDetails.setMessage(message);
        return errorDetails;
    }
}
