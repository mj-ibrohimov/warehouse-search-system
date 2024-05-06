package warehouse.dtos;

import lombok.Getter;

@Getter
public class DataDto<T> {
    private T body;
    private boolean success;
    private AppErrorDto errorDTO;

    public DataDto(AppErrorDto errorDTO) {
        this.errorDTO = errorDTO;
        this.success = false;
    }

    public DataDto(T body) {
        this.body = body;
        this.success = true;
    }
}
