package br.com.alyson.algafood.resource;

import br.com.alyson.algafood.domain.entity.Category;
import br.com.alyson.algafood.domain.repository.CategoryRepository;
import br.com.alyson.algafood.domain.service.CategoryService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class CategoryResourceIT {

    @LocalServerPort
    private int port;

    @Autowired
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.basePath = "/api/categories";
        categoryService.deleteAll();
    }

    @Test
    public void shouldReturnAllCategories() {
        Category category1 = new Category();
        category1.setName("Food");
        categoryService.save(category1);

        Category category2 = new Category();
        category2.setName("Drink");
        categoryService.save(category2);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("", hasSize(2))
                .body("name", hasItems("Food", "Drink"));
    }

    @Test
    public void shouldReturnCategoryById() {
        Category category = new Category();
        category.setName("Food");
        category = categoryService.save(category);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/{id}", category.getId())
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(category.getId().intValue()))
                .body("name", equalTo("Food"));
    }

    @Test
    public void shouldReturnNotFoundWhenCategoryDoesNotExist() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/{id}", 999)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void shouldCreateNewCategory() {
        Category category = new Category();
        category.setName("Food");

        given()
                .contentType(ContentType.JSON)
                .body(category)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("name", equalTo("Food"));
    }

    @Test
    public void shouldUpdateExistingCategory() {
        Category category = new Category();
        category.setName("Food");
        category = categoryService.save(category);

        category.setName("Updated Food");

        given()
                .contentType(ContentType.JSON)
                .body(category)
                .when()
                .put("/{id}", category.getId())
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(category.getId().intValue()))
                .body("name", equalTo("Updated Food"));
    }

    @Test
    public void shouldDeleteExistingCategory() {
        Category category = new Category();
        category.setName("Food");
        category = categoryService.save(category);

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/{id}", category.getId())
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/{id}", category.getId())
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}