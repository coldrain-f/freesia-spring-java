package edu.coldrain.word.service;

import edu.coldrain.word.dto.WordCreateRequest;
import edu.coldrain.word.entity.WordEntity;
import edu.coldrain.word.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
@RequiredArgsConstructor
public class WordService {

    private final WordRepository wordRepository;

    public Long create(final WordCreateRequest request) {
        final WordEntity wordEntity = request.toEntity();
        return wordRepository.save(wordEntity).getId();
    }

    public void update(final Long wordId) {

    }

    public void delete(final Long wordId) {

    }

    public void findAll(final Pageable pageable) {

    }
}
