package workflow.consumer.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "process_activity")
public class ProcessActivity {

    @Id
    @Column(name = "event_id")
    private String eventId;

    @Column(name = "username")
    private String username;

    @Column(name = "process_id")
    private String processId;

    @Column(name = "process_name")
    private String processName;

}