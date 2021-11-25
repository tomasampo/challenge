package cl.tenpo.challenge.web.controller;

import cl.tenpo.challenge.dto.response.ExceptionResponse;
import cl.tenpo.challenge.exception.UsernameAlreadyUsedException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler(UsernameAlreadyUsedException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public @ResponseBody ExceptionResponse handleUsernameAlready(final UsernameAlreadyUsedException ex, final HttpServletRequest request) {
        return new ExceptionResponse(ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public @ResponseBody ExceptionResponse handleResourceInvalidPassword(final BadCredentialsException ex, final HttpServletRequest request) {
        return new ExceptionResponse(ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public @ResponseBody ExceptionResponse handleResourceAccessDenied(final AccessDeniedException ex, final HttpServletRequest request) {
        return new ExceptionResponse(ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionResponse handleResourceMethodArgumentNotValid(final Exception ex, final HttpServletRequest request) {
        return new ExceptionResponse(ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionResponse handleResourceMethodArgumentTypeMismatch(final Exception ex, final HttpServletRequest request) {
        return new ExceptionResponse(ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionResponse handleConstraintViolation(final Exception ex, final HttpServletRequest request) {
        return new ExceptionResponse(ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ExceptionResponse handleException(final Exception ex, final HttpServletRequest request) {
        return new ExceptionResponse(ex.getMessage(), request.getRequestURI());
    }
}
