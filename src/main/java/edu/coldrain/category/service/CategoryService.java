package edu.coldrain.category.service;

import edu.coldrain.book.entity.BookEntity;
import edu.coldrain.book.repository.BookRepository;
import edu.coldrain.category.dto.CategoryCreateRequest;
import edu.coldrain.category.dto.CategoryUpdateRequest;
import edu.coldrain.category.entity.CategoryEntity;
import edu.coldrain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    public Long create(final Long bookId, final CategoryCreateRequest request) {
        final BookEntity bookEntity = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found."));

        final CategoryEntity categoryEntity = request.toEntity();
        categoryEntity.changeBookEntity(bookEntity);

        final CategoryEntity savedCategory = categoryRepository.save(categoryEntity);
        return savedCategory.getId();
    }

    @Transactional
    public void update(final CategoryUpdateRequest request, final Long categoryId) {
        final CategoryEntity categoryEntity = this.findById(categoryId);
        categoryEntity.changeName(request.getName());
    }

    public CategoryEntity findById(final Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found."));
    }

    public void delete(final Long categoryId) {
        final CategoryEntity categoryEntity = this.findById((categoryId));
        final Boolean isDeleted = categoryEntity.getIsDeleted();
        if (isDeleted) {
            throw new IllegalArgumentException("Already deleted category.");
        }
        categoryEntity.changeIsDeleted(true);
    }

    public Page<CategoryEntity> findAll(final Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }
}
