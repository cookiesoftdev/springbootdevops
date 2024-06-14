package br.com.alyson.algafood.domain.repository;

import br.com.alyson.algafood.domain.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("dev")
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category1;
    private Category category2;

    @BeforeEach
    public void setUp() {
        categoryRepository.deleteAll();

        category1 = new Category();
        category1.setName("Food");
        categoryRepository.save(category1);

        category2 = new Category();
        category2.setName("Drink");
        categoryRepository.save(category2);
    }

    @Test
    public void testFindAll() {
        List<Category> categories = categoryRepository.findAll();
        assertEquals(2, categories.size());
        assertTrue(categories.contains(category1));
        assertTrue(categories.contains(category2));
    }

    @Test
    public void testFindById() {
        Optional<Category> foundCategory = categoryRepository.findById(category1.getId());
        assertTrue(foundCategory.isPresent());
        assertEquals("Food", foundCategory.get().getName());
    }

    @Test
    public void testSave() {
        Category newCategory = new Category();
        newCategory.setName("Dessert");
        Category savedCategory = categoryRepository.save(newCategory);
        assertNotNull(savedCategory.getId());
        assertEquals("Dessert", savedCategory.getName());
    }

    @Test
    public void testDeleteById() {
        categoryRepository.deleteById(category1.getId());
        Optional<Category> deletedCategory = categoryRepository.findById(category1.getId());
        assertFalse(deletedCategory.isPresent());
    }
}