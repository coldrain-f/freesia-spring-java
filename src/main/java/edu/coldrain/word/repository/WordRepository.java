package edu.coldrain.word.repository;

import edu.coldrain.word.entity.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Long, WordEntity> {
}
