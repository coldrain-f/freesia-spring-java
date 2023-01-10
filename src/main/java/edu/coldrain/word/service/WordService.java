package edu.coldrain.word.service;

import edu.coldrain.category.entity.Category;
import edu.coldrain.category.repository.CategoryRepository;
import edu.coldrain.word.dto.WordCreateRequest;
import edu.coldrain.word.dto.WordUpdateRequest;
import edu.coldrain.word.entity.Word;
import edu.coldrain.word.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WordService {

    private final WordRepository wordRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Long create(final WordCreateRequest request, final Long categoryId) {
        final Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));

        final Word word = request.toEntity();
        word.changeCategoryEntity(category);

        return wordRepository.save(word).getId();
    }

    @Transactional
    public void update(final Long wordId, final WordUpdateRequest request) {
        final Word word = this.findById(wordId);
        word.changeName(request.getName());
    }

    @Transactional
    public void delete(final Long wordId) {
        final Word word = this.findById(wordId);
        final Boolean isDeleted = word.getIsDeleted();
        if (isDeleted) {
            throw new IllegalArgumentException("Already deleted word.");
        }
        word.changeIsDeleted(true);
    }

    public Page<Word> findAll(final Pageable pageable) {
        return wordRepository.findAll(pageable);
    }

    public Word findById(final Long wordId) {
        return wordRepository.findById(wordId)
                .orElseThrow(() -> new IllegalArgumentException("Word not found."));
    }
}
