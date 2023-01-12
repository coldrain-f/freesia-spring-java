package edu.coldrain.service;

import edu.coldrain.dto.WordResponse;
import edu.coldrain.entity.Category;
import edu.coldrain.repository.CategoryRepository;
import edu.coldrain.dto.WordCreateRequest;
import edu.coldrain.dto.WordUpdateRequest;
import edu.coldrain.entity.Word;
import edu.coldrain.repository.WordRepository;
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
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다. CATEGORY_ID = " + categoryId));

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
            throw new IllegalArgumentException("이미 삭제된 단어입니다.");
        }
        word.changeIsDeleted(true);
    }

    public Page<Word> findAll(final Pageable pageable) {
        return wordRepository.findAll(pageable);
    }

    public Word findById(final Long wordId) {
        return wordRepository.findById(wordId)
                .orElseThrow(() -> new IllegalArgumentException("단어를 찾을 수 없습니다. WORD_ID = " + wordId));
    }

    public Page<WordResponse> findAllByQuerydsl(final Pageable pageable) {
        return wordRepository.findAllByQuerydsl(pageable);
    }

    public Page<WordResponse> findAllByCategoryId(final Long categoryId, final Pageable pageable) {
        return wordRepository.findAllByCategoryId(categoryId, pageable);
    }
}
