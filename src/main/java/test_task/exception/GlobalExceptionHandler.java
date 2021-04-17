package test_task.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;
import test_task.api.response.ErrorResponse;
import test_task.service.ErrorService;

/**
 * Класс обработки исключений приложения
 */
@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {

    private final ErrorService errorService;

    @ExceptionHandler({
            IllegalStateException.class,
            IllegalArgumentException.class,
            UnsupportedOperationException.class})
    public final ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (ex instanceof IllegalStateException) {
            status = HttpStatus.NOT_FOUND;
            IllegalStateException ise = (IllegalStateException) ex;

            return handleIllegalStateException(ise, headers, status, request);
        }

        if (ex instanceof IllegalArgumentException) {
            status = HttpStatus.BAD_REQUEST;
            IllegalArgumentException iae = (IllegalArgumentException) ex;

            return handleIllegalArgumentException(iae, headers, status, request);
        }

        if (ex instanceof UnsupportedOperationException) {
            status = HttpStatus.FORBIDDEN;
            UnsupportedOperationException uoe = (UnsupportedOperationException) ex;

            return handleUnsupportedOperationException(uoe, headers, status, request);
        }

        return handleExceptionInternal(ex, null, headers, status, request);
    }

    /**
     * Метод настройки ответа для IllegalStateException
     */
    private ResponseEntity<ErrorResponse> handleIllegalStateException(IllegalStateException ise, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ise, new ErrorResponse(ise.getMessage()), headers, status, request);
    }

    /**
     * Метод настройки ответа для IllegalArgumentException
     */
    private ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException iae, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(iae, new ErrorResponse(iae.getMessage()), headers, status, request);
    }

    /**
     * Метод настройки ответа для UnsupportedOperationException
     */
    private ResponseEntity<ErrorResponse> handleUnsupportedOperationException(UnsupportedOperationException uoe, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(uoe, new ErrorResponse(uoe.getMessage()), headers, status, request);
    }

    /**
     * Метод настройки тела ответа для всех типов исключений
     */
    private ResponseEntity<ErrorResponse> handleExceptionInternal(Exception ex, ErrorResponse body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        ex.printStackTrace();

        return new ResponseEntity<>(errorService.addError(ex.getMessage()), headers, status);
    }
}
