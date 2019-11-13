package ru.otus.spring.homework10.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.homework10.domain.Author;
import ru.otus.spring.homework10.service.AuthorService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Тест контроллера для авторов")
@WebMvcTest(ControllerForAuthor.class)
class ControllerForAuthorTest {

    private static final String TEST_ID = "1";
    private static final String TEST_NAME = "Толстой";
    private static final Author TEST_AUTHOR = new Author(1,TEST_NAME);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    public String toJSON(Author author) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(author);
    }

    @DisplayName("должны верно отображаться все авторы")
    @Test
    void getAuthorAll() throws Exception {
        List<Author> list = Arrays.asList(TEST_AUTHOR,new Author(2,"Есенин"));
        given(authorService.showAllRows()).willReturn(list);
        mockMvc.perform(get("/api/author").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(TEST_ID)))
                .andExpect(content().string(Matchers.containsString(TEST_NAME)))
                .andExpect(content().string(Matchers.containsString("2")))
                .andExpect(content().string(Matchers.containsString("Есенин")));
    }

    @DisplayName("должны верно отобразиться страница результата добавления автора и корректно переданы параметры для создания автора")
    @Test
    void insertAuthor() throws Exception {
        given(authorService.insert(any())).willReturn(TEST_AUTHOR);
        mockMvc.perform(post("/api/author")
                .contentType(MediaType.APPLICATION_JSON).content(String.format("{ \"name\" : \"%s\"}",TEST_NAME)))
                .andExpect(status().isCreated())
                .andExpect(content().json(toJSON(TEST_AUTHOR)));
    }

    @DisplayName("должны верно отобразиться страница результата удаления автора и корректно переданы параметры на удаление автора")
    @Test
    void deleteAuthorById() throws Exception {
        mockMvc.perform(post("/api/author").param("name",TEST_NAME));
        mockMvc.perform(delete("/api/author")
                .contentType(MediaType.APPLICATION_JSON).content(String.format("{ \"id\" : \"%s\"}",TEST_ID)))
                .andExpect(status().isOk());
    }
}