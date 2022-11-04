package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = AfspraakNotFoundException.class)
    public ResponseEntity<Object> exception(AfspraakNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AutoNotFoundException.class)
    public ResponseEntity<Object> exception(AutoNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = GebruikerNotFoundException.class)
    public ResponseEntity<Object> exception(GebruikerNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = KlantNotFoundException.class)
    public ResponseEntity<Object> exception(KlantNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = OnderdeelNotFoundException.class)
    public ResponseEntity<Object> exception(OnderdeelNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ReparatieNotFoundException.class)
    public ResponseEntity<Object> exception(ReparatieNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
