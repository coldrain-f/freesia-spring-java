package edu.coldrain.repository;

import edu.coldrain.dto.WordResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WordRepositoryQuerydsl {

    Page<WordResponse> findAllByQuerydsl(Pageable pageable);

    Page<WordResponse> findAllByCategoryId(Long categoryId, Pageable pageable);
}
