package com.clinicaportaldoautista.exception;

import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.clinicaportaldoautista.dto.GenericResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GenericResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        logger.warn("Recurso não encontrado: {}", ex.getMessage());
        GenericResponseDTO errorResponse = new GenericResponseDTO(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SubscriptionException.class)
    public ResponseEntity<GenericResponseDTO> handleSubscriptionException(SubscriptionException ex, WebRequest request) {
        logger.warn("Erro de inscrição: {}", ex.getMessage());
        GenericResponseDTO errorResponse = new GenericResponseDTO(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField,
                        fieldError -> fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : "Erro de validação"
                ));
        logger.warn("Erro de validação nos argumentos do método: {}", errors);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponseDTO> handleGlobalException(Exception ex, WebRequest request) {       
        logger.error("Erro inesperado no servidor: {}", ex.getMessage(), ex);
        GenericResponseDTO errorResponse = new GenericResponseDTO("Ocorreu um erro inesperado no servidor. Tente novamente mais tarde.");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
