package warehouse.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
public class AppErrorDto {
    private Timestamp timestamp;
    private String friendlyMessage;
    private String developerMessage;

    public AppErrorDto(String friendlyMessage, String developerMessage) {
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
        this.friendlyMessage = friendlyMessage;
        this.developerMessage = developerMessage;
    }

    public AppErrorDto(String friendlyMessage) {
        this(friendlyMessage, friendlyMessage);
    }
}
