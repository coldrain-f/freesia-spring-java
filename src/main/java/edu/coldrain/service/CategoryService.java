package edu.coldrain.service;

import edu.coldrain.entity.Book;
import edu.coldrain.repository.BookRepository;
import edu.coldrain.dto.CategoryCreateRequest;
import edu.coldrain.dto.CategoryUpdateRequest;
import edu.coldrain.entity.Category;
import edu.coldrain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    @Transactional
    public Long create(final Long bookId, final CategoryCreateRequest request) {
        final Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found."));

        final Category category = request.toEntity();
        category.changeBookEntity(book);

        final Category savedCategory = categoryRepository.save(category);
        return savedCategory.getId();
    }

    @Transactional
    public void update(final CategoryUpdateRequest request, final Long categoryId) {
        final Category category = this.findById(categoryId);
        category.changeName(request.getName());
    }

    public Category findById(final Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found."));
    }

    @Transactional
    public void delete(final Long categoryId) {
        final Category category = this.findById((categoryId));
        final Boolean isDeleted = category.getIsDeleted();
        if (isDeleted) {
            throw new IllegalArgumentException("Already deleted category.");
        }
        category.changeIsDeleted(true);
    }

    public Page<Category> findAll(final Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }
}