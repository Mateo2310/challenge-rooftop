package com.example.challengerooftop.exception.handler;

import com.example.challengerooftop.exception.NotFoundTextException;
import com.example.challengerooftop.exception.RequestErrorResponse;
import com.example.challengerooftop.exception.ValidationErrorResponse;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class RequestException {

    @ExceptionHandler(value = {NoSuchElementException.class})
    protected ResponseEntity<?> handleNoSuchElementException(NoSuchElementException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    protected ResponseEntity<?> handleNullPointerException(NullPointerException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {NotFoundTextException.class})
    protected ResponseEntity<?> handleAdvertiseNotFoundException(NotFoundTextException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<?> handleConstraintViolationException(Exception ex) {
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {BindException.class})
    protected ResponseEntity<?> handleConstraintViolationException(BindException ex) {
        if (ex.getFieldErrors().isEmpty() && Objects.nonNull(ex.getGlobalError()))
            return buildResponse(ex.getGlobalError().getDefaultMessage(), HttpStatus.BAD_REQUEST);

        List<ValidationErrorResponse.FieldValidationError> errorDetails = ex.getFieldErrors().stream()
                .map(fieldError -> new ValidationErrorResponse.FieldValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        return buildDetailedErrorResponse(errorDetails);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex) {
        List<ValidationErrorResponse.FieldValidationError> errorDetails = new ArrayList<>();
        for (ConstraintViolation<?> constraint : ex.getConstraintViolations()) {
            String fieldName = ((PathImpl) constraint.getPropertyPath()).getLeafNode().getName();
            errorDetails.add(new ValidationErrorResponse.FieldValidationError(fieldName, constraint.getMessage()));
        }
        return buildDetailedErrorResponse(errorDetails);
    }

    private <T> ResponseEntity<?> buildDetailedErrorResponse(List<T> errorDetails) {
        RequestErrorResponse<T> errorResponse = new RequestErrorResponse<>();
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setDetails(errorDetails);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    private ResponseEntity<?> buildResponse(String errorMessage, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(new RequestErrorResponse<>(errorMessage, httpStatus.value()));
    }
}
