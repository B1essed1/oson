package uz.blessed.oson.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.blessed.oson.exception.ExceptionWithStatusCode;


import java.util.List;

@RestControllerAdvice
@Slf4j
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ExceptionWithStatusCode.class})
    ResponseEntity<ResponseWrapper<?>> handeCustomErrors(ExceptionWithStatusCode e, WebRequest request){

        var response = new ResponseWrapper<>();
        response.setErrorMessage(List.of(e.getMessageKey()));
        log.error(response.toString());

        return ResponseEntity.status(e.getHttpStatusCode()).body(response);
    }

    @ExceptionHandler({Throwable.class})
    public ResponseEntity internalError(Throwable e, ServletWebRequest webRequest) {

        log.error(e.getMessage(), e);


        return ResponseEntity
                .status(500)
                .body(new ResponseWrapper(List.of("internal.server.error"),500));
    }
}
