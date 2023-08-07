package workflow.consumer.repository;

import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import workflow.consumer.entity.ProcessActivity;

import java.util.List;

public interface ProcessRepository extends JpaRepository<ProcessActivity,Long> {

    @Query(value = "SELECT * FROM act_ru_event_subscr",nativeQuery = true)
    List<Object> getProcessList();

    @Query(value = "SELECT * FROM act_hi_actinst WHERE proc_def_key_= :process ",nativeQuery = true)
    List<Object> getAllProcessHistory(@Param("process") String processName);

    List<ProcessActivity> findAllByUsername(String username);

    @Query(value = "SELECT COUNT(*) FROM act_ru_event_subscr",nativeQuery = true)
    int processCount();

    @Query(value = "SELECT COUNT(*) FROM process_activity WHERE username= :username",nativeQuery = true)
    int activityCountByUsername(@Param("username") String username);

}
