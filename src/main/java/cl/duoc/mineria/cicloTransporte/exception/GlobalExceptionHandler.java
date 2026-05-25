package cl.duoc.mineria.cicloTransporte.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetalle> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetalle error = ErrorDetalle.builder()
                .timestamp(LocalDateTime.now())
                .mensaje(ex.getMessage())
                .detalles(request.getDescription(false))
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetalle> handleValidationErrors(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorDetalle error = ErrorDetalle.builder()
                .timestamp(LocalDateTime.now())
                .mensaje("Error de validación en los datos enviados")
                .detalles(errors.toString())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDetalle> handleJsonParseError(HttpMessageNotReadableException ex, WebRequest request) {
        ErrorDetalle error = ErrorDetalle.builder()
                .timestamp(LocalDateTime.now())
                .mensaje("Error al procesar el cuerpo JSON enviado")
                .detalles(ex.getMostSpecificCause().getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetalle> handleGeneralException(Exception ex, WebRequest request) {
        ErrorDetalle error = ErrorDetalle.builder()
                .timestamp(LocalDateTime.now())
                .mensaje("Error interno en el módulo de ciclos de transporte")
                .detalles(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}