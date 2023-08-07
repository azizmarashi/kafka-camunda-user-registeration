package workflow.consumer.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import workflow.consumer.entity.Event;


@Repository
public interface EventRepository extends MongoRepository<Event, String> {

}
