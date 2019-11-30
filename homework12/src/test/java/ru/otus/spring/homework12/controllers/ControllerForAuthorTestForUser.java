package ru.otus.spring.homework12.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.homework12.domain.Author;
import ru.otus.spring.homework12.security.MyPersonDetailsService;
import ru.otus.spring.homework12.service.AuthorService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Тест контроллера для авторов для роли USER")
@WebMvcTest(ControllerForAuthor.class)
class ControllerForAuthorTestForUser {

    private static final String TEST_ID = "1";
    private static final String TEST_NAME = "Толстой";
    private static final Author TEST_AUTHOR = new Author(1, TEST_NAME);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;
    @MockBean
    private MyPersonDetailsService myPersonDetailsService;

    @WithMockUser(authorities = {"ROLE_USER"})
    @DisplayName("должен верно отображаться автор по id")
    @Test
    void getAuthorById() throws Exception {
        given(authorService.showById(1)).willReturn(TEST_AUTHOR);
        mockMvc.perform(get("/author/id=1").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(TEST_ID)))
                .andExpect(content().string(Matchers.containsString(TEST_NAME)));
    }

    @WithMockUser(authorities = {"ROLE_USER"})
    @DisplayName("должны верно отображаться все авторы")
    @Test
    void getAuthorAll() throws Exception {
        List<Author> list = Arrays.asList(TEST_AUTHOR, new Author(2, "Есенин"));
        given(authorService.showAllRows()).willReturn(list);
        mockMvc.perform(get("/author").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(TEST_ID)))
                .andExpect(content().string(Matchers.containsString(TEST_NAME)))
                .andExpect(content().string(Matchers.containsString("2")))
                .andExpect(content().string(Matchers.containsString("Есенин")));
    }

    @WithMockUser(authorities = {"ROLE_USER"})
    @DisplayName("должны верно отобразиться страница результата добавления автора и корректно переданы параметры для создания автора")
    @Test
    void insertAuthor() throws Exception {
        given(authorService.insert(any())).willReturn("Автор Толстой успешно добавлен");
        mockMvc.perform(post("/author/add").param("name", TEST_NAME))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"ROLE_USER"})
    @DisplayName("должна верно отобразиться страница добавления автора")
    @Test
    void getPageInsert() throws Exception {
        mockMvc.perform(get("/author/insertauthor").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"ROLE_USER"})
    @DisplayName("должны верно отобразиться страница результата удаления автора и корректно переданы параметры на удаление автора")
    @Test
    void deleteAuthorById() throws Exception {
        given(authorService.deleteById(anyLong())).willReturn("Автор с id 1 удален из БД");
        mockMvc.perform(post("/author/del").param("id", TEST_ID))
                .andExpect(status().isForbidden());
    }
}