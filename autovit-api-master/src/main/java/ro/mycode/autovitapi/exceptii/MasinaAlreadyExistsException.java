package ro.mycode.autovitapi.exceptii;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MasinaAlreadyExistsException extends RuntimeException{
    public MasinaAlreadyExistsException(String mesaj) {
        super(mesaj);
    }
}
