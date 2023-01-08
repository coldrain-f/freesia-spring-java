package edu.coldrain.word.service;

import edu.coldrain.category.entity.CategoryEntity;
import edu.coldrain.category.repository.CategoryRepository;
import edu.coldrain.word.dto.WordCreateRequest;
import edu.coldrain.word.dto.WordUpdateRequest;
import edu.coldrain.word.entity.WordEntity;
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

    public Long create(final WordCreateRequest request, final Long categoryId) {
        final CategoryEntity categoryEntity = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));

        final WordEntity wordEntity = request.toEntity();
        wordEntity.changeCategoryEntity(categoryEntity);

        return wordRepository.save(wordEntity).getId();
    }

    @Transactional
    public void update(final Long wordId, final WordUpdateRequest request) {
        final WordEntity wordEntity = this.findById(wordId);
        wordEntity.changeName(request.getName());
    }

    @Transactional
    public void delete(final Long wordId) {
        final WordEntity wordEntity = this.findById(wordId);
        final Boolean isDeleted = wordEntity.getIsDeleted();
        if (isDeleted) {
            throw new IllegalArgumentException("Already deleted word.");
        }
        wordEntity.changeIsDeleted(true);
    }

    public Page<WordEntity> findAll(final Pageable pageable) {
        return wordRepository.findAll(pageable);
    }

    public WordEntity findById(final Long wordId) {
        return wordRepository.findById(wordId)
                .orElseThrow(() -> new IllegalArgumentException("Word not found."));
    }
}
