package workflow.producer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workflow.producer.domain.Person;

@Repository
public interface UserRepository extends JpaRepository<Person, Long> {

    Person findByUsername(String username);
    long countByUsername(String username);
}
