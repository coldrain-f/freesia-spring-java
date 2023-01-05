package edu.coldrain.word.service;

import edu.coldrain.word.dto.WordCreateRequest;
import edu.coldrain.word.dto.WordUpdateRequest;
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

    public void update(final Long wordId, final WordUpdateRequest request) {
        final WordEntity wordEntity = this.findById(wordId);
        wordEntity.changeName(request.getName());
    }

    public void delete(final Long wordId) {
        final WordEntity wordEntity = this.findById(wordId);
        final Boolean isDeleted = wordEntity.getIsDeleted();
        if (isDeleted) {
            throw new IllegalArgumentException("Already deleted word.");
        }
        wordEntity.changeIsDeleted(true);
    }

    public void findAll(final Pageable pageable) {

    }

    public WordEntity findById(final Long wordId) {
        return wordRepository.findById(wordId)
                .orElseThrow(() -> new IllegalArgumentException("Word not found."));
    }
}
