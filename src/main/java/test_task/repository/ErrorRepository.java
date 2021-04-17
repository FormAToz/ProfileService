package test_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test_task.model.Error;

import java.util.Optional;

@Repository
public interface ErrorRepository extends JpaRepository<Error, Long> {
    Optional<Error> findFirstByOrderByCreatedDesc();
}
