package edu.coldrain.repository;

import edu.coldrain.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long>, WordRepositoryQuerydsl {
}
