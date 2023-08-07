package workflow.consumer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private long phone;
    private String serviceName;
    private String publisher;
    private LocalDateTime timestamp;
}