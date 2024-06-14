package br.com.alyson.algafood.domain.service;

import br.com.alyson.algafood.domain.entity.Category;
import br.com.alyson.algafood.domain.repository.CategoryRepository;
import br.com.alyson.algafood.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        category = new Category();
        category.setId(1L);
        category.setName("Food");
    }

    @Test
    void testFindAll() {
        List<Category> categories = Arrays.asList(category);
        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> result = categoryService.findAll();
        assertEquals(1, result.size());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void testFindById_Success() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Category result = categoryService.findById(1L);
        assertNotNull(result);
        assertEquals("Food", result.getName());
    }

    @Test
    void testFindById_NotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            categoryService.findById(1L);
        });
    }

    @Test
    void testSave() {
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category result = categoryService.save(category);
        assertNotNull(result);
        assertEquals("Food", result.getName());
    }

    @Test
    void testDeleteById() {
        doNothing().when(categoryRepository).deleteById(1L);

        categoryService.deleteById(1L);
        verify(categoryRepository, times(1)).deleteById(1L);
    }
}