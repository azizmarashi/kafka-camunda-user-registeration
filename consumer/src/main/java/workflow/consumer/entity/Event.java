package workflow.consumer.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "event")
@AllArgsConstructor
@Builder
public class Event implements Serializable {

    @Serial
    private static final long serialVersionUID = 1100249189715805323L;

    @Id
    @Indexed(useGeneratedName = true)
    private String id;

    private String serviceName;

    private String publisher;


    @Column(unique = true, nullable = false, length = 50, columnDefinition = "VARCHAR(50)")
    private String username;

    @Column(nullable = false, length = 50, columnDefinition = "VARCHAR(50)")
    private String firstName;

    @Column(nullable = false, length = 50, columnDefinition = "VARCHAR(50)")
    private String lastName;

    @Column(nullable = false, length = 500, columnDefinition = "VARCHAR(500)")
    private String password;

    @Column(unique = true, nullable = false)
    private long phone;


    private LocalDateTime timestamp;


}
