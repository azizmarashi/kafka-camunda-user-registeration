package workflow.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PersonDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 698409590844751299L;
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private long phone;

}
