package com.portfolio.my_portfolio_backend.exception.handler;

import com.portfolio.my_portfolio_backend.exception.ValidationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public String exceptionHandler(ValidationException ex, Model model) {
        model.addAttribute("errors", ex.getBindingResult().getAllErrors());
        model.addAttribute("message", "Validation failed. Please correct the errors below and try again.");

        return "error/validation";
    }
}
