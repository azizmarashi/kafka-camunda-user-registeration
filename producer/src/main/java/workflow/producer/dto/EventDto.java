package workflow.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -3681300138873410389L;

    private String serviceName;
    private String publisher;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private long phone;
    private LocalDateTime timestamp;


}
