package edu.coldrain.category.service;

import edu.coldrain.category.dto.CategoryCreateRequest;
import edu.coldrain.category.dto.CategoryUpdateRequest;
import edu.coldrain.category.entity.CategoryEntity;
import edu.coldrain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Long create(final CategoryCreateRequest request) {
        final CategoryEntity categoryEntity = request.toEntity();
        return categoryRepository.save(categoryEntity).getId();
    }

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
