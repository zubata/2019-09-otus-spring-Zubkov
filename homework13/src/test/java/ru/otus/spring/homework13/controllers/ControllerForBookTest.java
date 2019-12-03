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
import ru.otus.spring.homework13.domain.Book;
import ru.otus.spring.homework13.domain.Genre;
import ru.otus.spring.homework13.security.MyAclService;
import ru.otus.spring.homework13.security.MyPersonDetailsService;
import ru.otus.spring.homework13.service.AuthorService;
import ru.otus.spring.homework13.service.BookService;

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

@DisplayName("Тест контроллера для книг")
@WebMvcTest(ControllerForBook.class)
class ControllerForBookTest {

    private static final String TEST_ID_1 = "1";
    private static final String TEST_NAME_1 = "Война и Мир";
    private static final String TEST_ID_2 = "2";
    private static final String TEST_NAME_2 = "Не стихов златая пена";
    private static final Book TEST_BOOK_1 = new Book(1, TEST_NAME_1, new Author("Толстой"), Arrays.asList(new Genre("Роман")));
    private static final Book TEST_BOOK_2 = new Book(2, TEST_NAME_2, new Author("Есенин"), Arrays.asList(new Genre("Стихи")));

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private MyPersonDetailsService myPersonDetailsService;
    @MockBean
    private MyAclService myAclService;

    @WithMockUser(authorities = {"ROLE_ADMIN"})
    @DisplayName("Роль ADMIN, должна верно отображаться книга по id")
    @Test
    void getBookById1() throws Exception {
        given(bookService.showById(1)).willReturn(TEST_BOOK_1);
        mockMvc.perform(get("/book/id=1").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(TEST_ID_1)))
                .andExpect(content().string(Matchers.containsString(TEST_NAME_1)));
    }

    @WithMockUser(authorities = {"ROLE_ADMIN"})
    @DisplayName("Роль ADMIN, должны верно отображаться все книги")
    @Test
    void getBookAll1() throws Exception {
        List<Book> list = Arrays.asList(TEST_BOOK_1, TEST_BOOK_2);
        given(bookService.showAllRows()).willReturn(list);
        given(myAclService.isAdmin()).willReturn(true);
        mockMvc.perform(get("/book").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(TEST_ID_1)))
                .andExpect(content().string(Matchers.containsString(TEST_NAME_1)))
                .andExpect(content().string(Matchers.containsString(TEST_ID_2)))
                .andExpect(content().string(Matchers.containsString(TEST_NAME_2)));
    }

    @WithMockUser(authorities = {"ROLE_ADMIN"})
    @DisplayName("Роль ADMIN, должны верно отобразиться страница результата добавления книги и корректно переданы параметры для создания книги")
    @Test
    void insertBook1() throws Exception {
        given(bookService.insert(any())).willReturn("Книга Война и Мир была успешно добавлена");
        MvcResult result = mockMvc.perform(post("/book/add")
                .param("name",TEST_NAME_1)
                .param("author","Толстой")
                .param("genre","Роман"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Книга Война и Мир была успешно добавлена")))
                .andReturn();
        assertThat(result.getRequest().getAttribute("bookDto"))
                .hasFieldOrPropertyWithValue("name",TEST_NAME_1)
                .hasFieldOrPropertyWithValue("author","Толстой")
                .hasFieldOrPropertyWithValue("genre","Роман");
    }

    @WithMockUser(authorities = {"ROLE_ADMIN"})
    @DisplayName("Роль ADMIN, должна верно отобразиться страница добавления книги")
    @Test
    void getPageInsert1() throws Exception {
        List<Author> authors = Arrays.asList(new Author("Толстой"),new Author("Есенин"));
        given(authorService.showAllRows()).willReturn(authors);
        MvcResult result = mockMvc.perform(get("/book/insertbook").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Добавление новой книги")))
                .andReturn();
        assertThat(result.getRequest().getAttribute("book")).isNotNull();
        assertThat(result.getRequest().getAttribute("authors")).isEqualToComparingFieldByField(authors);
    }

    @WithMockUser(authorities = {"ROLE_ADMIN"})
    @DisplayName("Роль ADMIN, должны верно отобразиться страница результата удаления книги и корректно переданы параметры на удаление книги")
    @Test
    void deleteBookById1() throws Exception {
        mockMvc.perform(post("/book/add").param("name",TEST_NAME_1));
        given(bookService.deleteById(anyLong())).willReturn("Книга с названием Война и Мир удалена из БД");
        MvcResult result = mockMvc.perform(post("/book/del").param("id",TEST_ID_1))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Книга с названием Война и Мир удалена из БД")))
                .andReturn();
        assertThat(result.getRequest().getParameter("id")).isEqualTo(TEST_ID_1);
    }





    @WithMockUser(authorities = {"ROLE_USERADULT"})
    @DisplayName("Роль USERADULT, должна верно отображаться книга по id")
    @Test
    void getBookById2() throws Exception {
        given(bookService.showById(1)).willReturn(TEST_BOOK_1);
        mockMvc.perform(get("/book/id=1").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(TEST_ID_1)))
                .andExpect(content().string(Matchers.containsString(TEST_NAME_1)));
    }

    @WithMockUser(authorities = {"ROLE_USERADULT"})
    @DisplayName("Роль USERADULT, должны верно отображаться все книги")
    @Test
    void getBookAll2() throws Exception {
        List<Book> list = Arrays.asList(TEST_BOOK_1, TEST_BOOK_2);
        given(bookService.showAllRows()).willReturn(list);
        given(myAclService.isAdmin()).willReturn(false);
        mockMvc.perform(get("/book").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(TEST_ID_1)))
                .andExpect(content().string(Matchers.containsString(TEST_NAME_1)))
                .andExpect(content().string(Matchers.containsString(TEST_ID_2)))
                .andExpect(content().string(Matchers.containsString(TEST_NAME_2)));
    }

    @WithMockUser(authorities = {"ROLE_USERADULT"})
    @DisplayName("Роль USERADULT, должны верно отобразиться страница результата добавления книги и корректно переданы параметры для создания книги")
    @Test
    void insertBook2() throws Exception {
        given(bookService.insert(any())).willReturn("Книга Война и Мир была успешно добавлена");
        mockMvc.perform(post("/book/add")
                .param("name",TEST_NAME_1)
                .param("author","Толстой")
                .param("genre","Роман"))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"ROLE_USERADULT"})
    @DisplayName("Роль USERADULT, должна верно отобразиться страница добавления книги")
    @Test
    void getPageInsert2() throws Exception {
        List<Author> authors = Arrays.asList(new Author("Толстой"),new Author("Есенин"));
        given(authorService.showAllRows()).willReturn(authors);
        mockMvc.perform(get("/book/insertbook").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"ROLE_USERADULT"})
    @DisplayName("Роль USERADULT, должны верно отобразиться страница результата удаления книги и корректно переданы параметры на удаление книги")
    @Test
    void deleteBookById2() throws Exception {
        given(bookService.deleteById(anyLong())).willReturn("Книга с названием Война и Мир удалена из БД");
        mockMvc.perform(post("/book/del").param("id",TEST_ID_1))
                .andExpect(status().isForbidden());
    }

}