package edu.coldrain.repository;

import edu.coldrain.entity.Learn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearnRepository extends JpaRepository<Learn, Long> {

    Learn findByUserIdAndBookId(Long userId, Long bookId);
}
