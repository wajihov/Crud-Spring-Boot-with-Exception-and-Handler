package com.example.Advice;

import com.example.Exception.ConsultationExecptionNotFound;
import com.example.Exception.MedecinNotFoundException;
import com.example.Exception.PatientNotFoundException;
import com.example.Exception.RendezVousNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorsMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorsMap.put(error.getDefaultMessage(), error.getDefaultMessage());
        });
        return errorsMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(PatientNotFoundException.class)
    public Map<String, String> handlerInvalidIdPatient(PatientNotFoundException ex) {
        Map<String, String> errorsMap = new HashMap<>();
        errorsMap.put("Erreur Message", ex.getMessage());
        return errorsMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MedecinNotFoundException.class)
    public Map<String, String> handlerInvalidIdMedecin(MedecinNotFoundException ex) {
        Map<String, String> errorsMap = new HashMap<>();
        errorsMap.put("Erreur Message", ex.getMessage());
        return errorsMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RendezVousNotFound.class)
    public Map<String, String> handlerInvalidRDV(RendezVousNotFound ex) {
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("RDV Erreur ", ex.getMessage());
        return stringMap;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolation(ConstraintViolationException ex) {
        String message = ((ex).getConstraintViolations()).iterator().next().getMessage();
        Map<String, String> errorsMap = new HashMap<>();
        errorsMap.put(" Erreur message ", message);
        return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ConsultationExecptionNotFound.class)
    public ResponseEntity<Map<String, String>> handlerConsultationIntrouvable(ConsultationExecptionNotFound ex) {
        Map<String, String> errorMsg = new HashMap<>();
        errorMsg.put("Message Not Found Consultation  ", ex.getMessage());
        return ResponseEntity.ok(errorMsg);
    }

}
