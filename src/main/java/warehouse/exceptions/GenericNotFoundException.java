package warehouse.exceptions;

import lombok.Getter;

@Getter
public class GenericNotFoundException extends Exception{
    public GenericNotFoundException(String message) {
        super(message);
    }

}
