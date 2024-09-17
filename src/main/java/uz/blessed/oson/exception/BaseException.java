package uz.blessed.oson.exception;

public abstract class BaseException extends RuntimeException{

    BaseException(){}

    BaseException(Throwable cause){
        super(cause);
    }
}
