package br.com.alyson.algafood.cucumber;

import br.com.alyson.algafood.AlgafoodApplication;
import br.com.alyson.algafood.domain.entity.Category;
import br.com.alyson.algafood.domain.repository.CategoryRepository;
import br.com.alyson.algafood.domain.service.CategoryService;
import br.com.alyson.algafood.exceptions.ResourceNotFoundException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = CucumberSpringConfiguration.class)
public class CategoryServiceStepDefinitions {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    private List<Category> categories;
    private Category category;
    private Exception exception;

    @Given("there are categories in the system")
    public void there_are_categories_in_the_system() {
        Category category1 = new Category();
        category1.setName("Food");
        categoryRepository.save(category1);

        Category category2 = new Category();
        category2.setName("Drink");
        categoryRepository.save(category2);
    }

    @When("I request all categories")
    public void i_request_all_categories() {
        categories = categoryService.findAll();
    }

    @Then("I should receive a list of categories")
    public void i_should_receive_a_list_of_categories() {
        assertFalse(categories.isEmpty());
        assertEquals(2, categories.size());
    }

    @Given("there is a category with id {long}")
    public void there_is_a_category_with_id(Long id) {
        Category category = new Category();
        category.setId(id);
        category.setName("Food");
        categoryRepository.save(category);
    }

    @When("I request the category with id {long}")
    public void i_request_the_category_with_id(Long id) {
        try {
            category = categoryService.findById(id);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("I should receive the category with id {long}")
    public void i_should_receive_the_category_with_id(Long id) {
        assertNotNull(category);
        assertEquals(id, category.getId());
    }

    @Given("there is no category with id {long}")
    public void there_is_no_category_with_id(Long id) {
        categoryRepository.deleteById(id);
    }

    @Then("I should receive a resource not found error")
    public void i_should_receive_a_resource_not_found_error() {
        assertNotNull(exception);
        assertTrue(exception instanceof ResourceNotFoundException);
    }
}