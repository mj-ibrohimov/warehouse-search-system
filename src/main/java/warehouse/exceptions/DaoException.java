package warehouse.exceptions;

import lombok.Getter;
import warehouse.dtos.AppErrorDto;

@Getter
public class DaoException extends Exception {

    private AppErrorDto appErrorDto;

    public DaoException(String message) {
        super(message);
    }
}
