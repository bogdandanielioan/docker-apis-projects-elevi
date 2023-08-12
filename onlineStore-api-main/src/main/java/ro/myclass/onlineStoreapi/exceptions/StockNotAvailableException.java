package ro.myclass.onlineStoreapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StockNotAvailableException extends RuntimeException {

    public StockNotAvailableException(String productName) {
        super(productName + " stock is not available !");
    }
}
