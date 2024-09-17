package uz.blessed.oson.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ResponseWrapper<T> {



    private Integer code = 2000;
    private T data;
    private List<UUID> errorRef;
    private List<String> errorMessage;
    private Long timestamp;

    public ResponseWrapper() {
    }

    public ResponseWrapper(T data) {
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public ResponseWrapper(T data, List<UUID> errorRef, List<String> errorMessage, Integer code) {
        this.data = data;
        this.errorRef = errorRef;
        this.errorMessage = errorMessage;
        this.timestamp = System.currentTimeMillis();
        this.code = code;
    }

    public ResponseWrapper(List<UUID> errorRef, List<String> errorMessage, Integer code) {
        this.errorRef = errorRef;
        this.errorMessage = errorMessage;
        this.timestamp = System.currentTimeMillis();
        this.code = code;
    }

    public <E> ResponseWrapper(List<String> errorMessage, Integer code) {
        this.errorMessage = errorMessage;
        this.code = code;
        this.timestamp = System.currentTimeMillis();
    }

    public ResponseWrapper(T data, List<String> errorMessage) {
        this.data = data;
        this.errorMessage = errorMessage;
        this.timestamp = System.currentTimeMillis();
    }

    public T getData() {
        return data;
    }

    public ResponseWrapper<T> setData(T data) {
        this.data = data;
        return this;
    }

    public List<UUID> getErrorRef() {
        return errorRef;
    }

    public ResponseWrapper<T> setErrorRef(List<UUID> errorRef) {
        this.errorRef = errorRef;
        return this;
    }

    public List<String> getErrorMessage() {
        return errorMessage;
    }

    public ResponseWrapper<T> setErrorMessage(List<String> errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public ResponseWrapper<T> setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    public static <T> ResponseEntity<ResponseWrapper<T>> notAuthorized(){
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setCode(HttpStatus.UNAUTHORIZED.value());
        responseWrapper.setTimestamp(System.currentTimeMillis());
        responseWrapper.setErrorMessage(List.of("user.not.authorized"));
        return new ResponseEntity<>(responseWrapper, HttpStatus.UNAUTHORIZED);
    }

    public static <T>ResponseEntity<ResponseWrapper<T>> accessDenied(){
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setCode(HttpStatus.UNAUTHORIZED.value());
        responseWrapper.setTimestamp(System.currentTimeMillis());
        responseWrapper.setErrorMessage(List.of("user.access.forbidden"));
        return new ResponseEntity<>(responseWrapper, HttpStatus.FORBIDDEN);
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "data=" + data +
                ", errorRef=" + errorRef +
                ", errorMessage=" + errorMessage +
                ", timestamp=" + timestamp +
                '}';
    }
}