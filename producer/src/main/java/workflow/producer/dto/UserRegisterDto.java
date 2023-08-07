package workflow.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -5081863825368962553L;
    @NotEmpty(message = "Firstname is required")
    private String firstName;
    @NotEmpty(message = "Lastname is required")
    private String lastName;
    @NotEmpty(message = "Username is required")
    private String username;
    @NotEmpty(message = "Password is required")
    private String password;
    @NotEmpty(message = "Phone number is required")
    private long phone;
}
