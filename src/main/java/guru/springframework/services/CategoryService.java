package guru.springframework.services;

import guru.springframework.api.v1.model.CategoryDTO;

import java.util.List;

/**
 * Created by Kunal Indoliya.
 */
public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryByName(String name);
}
