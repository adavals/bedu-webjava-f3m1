package org.bedu.java.backend.postwork.controller.handles;

import org.bedu.java.backend.postwork.model.RespuestaError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ManejadorGlobalErrores {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleStatusException(MethodArgumentNotValidException ex, WebRequest request) {
        return RespuestaError.builder()
            .exception(ex)
            .mensaje("Ocurrio un error al validar la peticion.")
            .ruta(request.getDescription(false).substring(4))
            .entidad();
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> manejaException(Exception ex, WebRequest request) {
        return RespuestaError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .mensaje("Ocurrio un error al validar la peticion.")
                .ruta(request.getDescription(false).substring(4))
                .entidad();
    }
}
