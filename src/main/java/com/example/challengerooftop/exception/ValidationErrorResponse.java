package com.example.challengerooftop.exception;

public class ValidationErrorResponse extends RequestErrorResponse<ValidationErrorResponse.FieldValidationError>{
    public static class FieldValidationError {
        private String field;
        private String message;

        public FieldValidationError(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
