package personal.studio.manager.backend.restful;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import personal.studio.manager.backend.app.exception.PositiveBalanceException;
import personal.studio.manager.backend.restful.error.ErrorMsg;
import personal.studio.manager.backend.restful.error.PositiveBalanceMessage;


@RestControllerAdvice
public class RestfulAPIExceptionHandler {


    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMsg handleThrowable(Throwable throwable) {
        return new ErrorMsg(throwable.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(PositiveBalanceException.class)
    public PositiveBalanceMessage handlePositiveBalanceException(PositiveBalanceException ex) {
        return new PositiveBalanceMessage(ex);
    }

}