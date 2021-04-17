package test_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test_task.model.Profile;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    boolean existsByEmailIgnoreCase(String email);

    Optional<Profile> findFirstByOrderByCreatedDesc();

    Optional<Profile> findByEmailIgnoreCase(String email);
}
