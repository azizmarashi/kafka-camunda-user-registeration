package workflow.producer.domain;

import lombok.*;
import workflow.producer.dto.UserRegisterDto;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

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


}
