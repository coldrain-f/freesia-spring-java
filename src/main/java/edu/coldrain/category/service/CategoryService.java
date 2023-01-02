package edu.coldrain.category.service;

import edu.coldrain.category.dto.CategoryCreateRequest;
import edu.coldrain.category.dto.CategoryUpdateRequest;
import edu.coldrain.category.entity.CategoryEntity;
import edu.coldrain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
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
}
