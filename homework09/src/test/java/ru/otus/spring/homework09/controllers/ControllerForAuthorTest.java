package ru.otus.spring.homework09.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.otus.spring.homework09.domain.Author;
import ru.otus.spring.homework09.service.AuthorService;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @DisplayName("должен верно отображаться автор по id")
    @Test
    void getAuthorById() throws Exception {
        given(authorService.showById(1)).willReturn(TEST_AUTHOR);
        mockMvc.perform(get("/author/id=1").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(TEST_ID)))
                .andExpect(content().string(Matchers.containsString(TEST_NAME)));
    }

    @DisplayName("должны верно отображаться все авторы")
    @Test
    void getAuthorAll() throws Exception {
        List<Author> list = Arrays.asList(TEST_AUTHOR,new Author(2,"Есенин"));
        given(authorService.showAllRows()).willReturn(list);
        mockMvc.perform(get("/author").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(TEST_ID)))
                .andExpect(content().string(Matchers.containsString(TEST_NAME)))
                .andExpect(content().string(Matchers.containsString("2")))
                .andExpect(content().string(Matchers.containsString("Есенин")));
    }

    @DisplayName("должны верно отобразиться страница результата добавления автора и корректно переданы параметры для создания автора")
    @Test
    void insertAuthor() throws Exception {
        given(authorService.insert(any())).willReturn("Автор Толстой успешно добавлен");
        MvcResult result = mockMvc.perform(post("/author/add").param("name",TEST_NAME))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Автор Толстой успешно добавлен")))
                .andReturn();
        assertThat(result.getRequest().getAttribute("author")).hasFieldOrPropertyWithValue("name",TEST_NAME);
    }

    @DisplayName("должна верно отобразиться страница добавления автора")
    @Test
    void getPageInsert() throws Exception {
        MvcResult result = mockMvc.perform(get("/author/insertauthor").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Добавление нового автора")))
                .andReturn();
        assertThat(result.getRequest().getAttribute("author")).isNotNull();
    }

    @DisplayName("должны верно отобразиться страница результата удаления автора и корректно переданы параметры на удаление автора")
    @Test
    void deleteAuthorById() throws Exception {
        mockMvc.perform(post("/author/add").param("name",TEST_NAME));
        given(authorService.deleteById(anyLong())).willReturn("Автор с id 1 удален из БД");
        MvcResult result = mockMvc.perform(post("/author/del").param("id",TEST_ID))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Автор с id 1 удален из БД")))
                .andReturn();
        assertThat(result.getRequest().getParameter("id")).isEqualTo(TEST_ID);
    }
}