package ru.otus.spring.homework13.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.otus.spring.homework13.domain.Author;
import ru.otus.spring.homework13.security.MyAclService;
import ru.otus.spring.homework13.security.MyPersonDetailsService;
import ru.otus.spring.homework13.service.AuthorService;

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
    @MockBean
    private MyPersonDetailsService myPersonDetailsService;
    @MockBean
    private MyAclService myAclService;

    @WithMockUser(authorities = {"ROLE_ADMIN"})
    @DisplayName("Роль ADMIN, должен верно отображаться автор по id")
    @Test
    void getAuthorById1() throws Exception {
        given(authorService.showById(1)).willReturn(TEST_AUTHOR);
        mockMvc.perform(get("/author/id=1").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(TEST_ID)))
                .andExpect(content().string(Matchers.containsString(TEST_NAME)));
    }

    @WithMockUser(authorities = {"ROLE_ADMIN"})
    @DisplayName("Роль ADMIN, должны верно отображаться все авторы")
    @Test
    void getAuthorAll1() throws Exception {
        List<Author> list = Arrays.asList(TEST_AUTHOR,new Author(2,"Есенин"));
        given(authorService.showAllRows()).willReturn(list);
        given(myAclService.isAdmin()).willReturn(true);
        mockMvc.perform(get("/author").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(TEST_ID)))
                .andExpect(content().string(Matchers.containsString(TEST_NAME)))
                .andExpect(content().string(Matchers.containsString("2")))
                .andExpect(content().string(Matchers.containsString("Есенин")));
    }

    @WithMockUser(authorities = {"ROLE_ADMIN"})
    @DisplayName("Роль ADMIN, должны верно отобразиться страница результата добавления автора и корректно переданы параметры для создания автора")
    @Test
    void insertAuthor1() throws Exception {
        given(authorService.insert(any())).willReturn("Автор Толстой успешно добавлен");
        MvcResult result = mockMvc.perform(post("/author/add").param("name",TEST_NAME))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Автор Толстой успешно добавлен")))
                .andReturn();
        assertThat(result.getRequest().getAttribute("author")).hasFieldOrPropertyWithValue("name",TEST_NAME);
    }

    @WithMockUser(authorities = {"ROLE_ADMIN"})
    @DisplayName("Роль ADMIN, должна верно отобразиться страница добавления автора")
    @Test
    void getPageInsert1() throws Exception {
        MvcResult result = mockMvc.perform(get("/author/insertauthor").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Добавление нового автора")))
                .andReturn();
        assertThat(result.getRequest().getAttribute("author")).isNotNull();
    }

    @WithMockUser(authorities = {"ROLE_ADMIN"})
    @DisplayName("Роль ADMIN, должны верно отобразиться страница результата удаления автора и корректно переданы параметры на удаление автора")
    @Test
    void deleteAuthorById1() throws Exception {
        mockMvc.perform(post("/author/add").param("name",TEST_NAME));
        given(authorService.deleteById(anyLong())).willReturn("Автор с id 1 удален из БД");
        MvcResult result = mockMvc.perform(post("/author/del").param("id",TEST_ID))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Автор с id 1 удален из БД")))
                .andReturn();
        assertThat(result.getRequest().getParameter("id")).isEqualTo(TEST_ID);
    }





    @WithMockUser(authorities = {"ROLE_USERADULT"})
    @DisplayName("Роль USERADULT, должен верно отображаться автор по id")
    @Test
    void getAuthorById2() throws Exception {
        given(authorService.showById(1)).willReturn(TEST_AUTHOR);
        mockMvc.perform(get("/author/id=1").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(TEST_ID)))
                .andExpect(content().string(Matchers.containsString(TEST_NAME)));
    }

    @WithMockUser(authorities = {"ROLE_USERADULT"})
    @DisplayName("Роль USERADULT, должны верно отображаться все авторы")
    @Test
    void getAuthorAll2() throws Exception {
        List<Author> list = Arrays.asList(TEST_AUTHOR, new Author(2, "Есенин"));
        given(authorService.showAllRows()).willReturn(list);
        given(myAclService.isAdmin()).willReturn(false);
        mockMvc.perform(get("/author").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(TEST_ID)))
                .andExpect(content().string(Matchers.containsString(TEST_NAME)))
                .andExpect(content().string(Matchers.containsString("2")))
                .andExpect(content().string(Matchers.containsString("Есенин")));
    }

    @WithMockUser(authorities = {"ROLE_USERADULT"})
    @DisplayName("Роль USERADULT, должны верно отобразиться страница результата добавления автора и корректно переданы параметры для создания автора")
    @Test
    void insertAuthor2() throws Exception {
        given(authorService.insert(any())).willReturn("Автор Толстой успешно добавлен");
        mockMvc.perform(post("/author/add").param("name", TEST_NAME))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"ROLE_USERADULT"})
    @DisplayName("Роль USERADULT, должна верно отобразиться страница добавления автора")
    @Test
    void getPageInsert2() throws Exception {
        mockMvc.perform(get("/author/insertauthor").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"ROLE_USERADULT"})
    @DisplayName("Роль USERADULT, должны верно отобразиться страница результата удаления автора и корректно переданы параметры на удаление автора")
    @Test
    void deleteAuthorById2() throws Exception {
        given(authorService.deleteById(anyLong())).willReturn("Автор с id 1 удален из БД");
        mockMvc.perform(post("/author/del").param("id", TEST_ID))
                .andExpect(status().isForbidden());
    }
}