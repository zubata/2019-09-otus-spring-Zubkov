package ru.otus.spring.homework10.rest;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.otus.spring.homework10.domain.Author;
import ru.otus.spring.homework10.domain.Book;
import ru.otus.spring.homework10.domain.Genre;
import ru.otus.spring.homework10.service.AuthorService;
import ru.otus.spring.homework10.service.BookService;

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

    @DisplayName("должны верно отображаться все книги")
    @Test
    void getBookAll() throws Exception {
        List<Book> list = Arrays.asList(TEST_BOOK_1, TEST_BOOK_2);
        given(bookService.showAllRows()).willReturn(list);
        mockMvc.perform(get("/api/book").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(TEST_ID_1)))
                .andExpect(content().string(Matchers.containsString(TEST_NAME_1)))
                .andExpect(content().string(Matchers.containsString(TEST_ID_2)))
                .andExpect(content().string(Matchers.containsString(TEST_NAME_2)));
    }

    @DisplayName("должны верно отобразиться страница результата добавления книги и корректно переданы параметры для создания книги")
    @Test
    void insertBook() throws Exception {
        String inputJson = String.format("{ \"name\" : \"%s\", \"author\" : \"%s\", \"genre\" : \"%s\" }",TEST_NAME_1,"Толстой","Роман");
        given(bookService.insert(any())).willReturn("Книга Война и Мир была успешно добавлена");
        mockMvc.perform(post("/api/book/add")
                .contentType(MediaType.APPLICATION_JSON).content(inputJson))
                .andExpect(status().isCreated())
                .andExpect(content().string(Matchers.containsString("Книга Война и Мир была успешно добавлена")));
    }

    @DisplayName("должны верно отобразиться страница результата удаления книги и корректно переданы параметры на удаление книги")
    @Test
    void deleteBookById() throws Exception {
        String inputJson = String.format("{ \"name\" : \"%s\", \"author\" : \"%s\", \"genre\" : \"%s\" }",TEST_NAME_1,"Толстой","Роман");
        mockMvc.perform(post("/api/book/add").contentType(MediaType.APPLICATION_JSON).content(inputJson));
        given(bookService.deleteById(anyLong())).willReturn("Книга с названием Война и Мир удалена из БД");
        mockMvc.perform(post("/api/book/del").contentType(MediaType.APPLICATION_JSON).content(String.format("{ \"id\" : \"%s\"}",TEST_ID_1)))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Книга с названием Война и Мир удалена из БД")));
    }

}