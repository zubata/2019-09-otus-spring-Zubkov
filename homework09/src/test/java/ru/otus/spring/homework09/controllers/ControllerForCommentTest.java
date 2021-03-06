package ru.otus.spring.homework09.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.otus.spring.homework09.domain.Author;
import ru.otus.spring.homework09.domain.Book;
import ru.otus.spring.homework09.domain.Comment;
import ru.otus.spring.homework09.domain.Genre;
import ru.otus.spring.homework09.service.CommentService;

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

@DisplayName("Тест контроллера для комментариев")
@WebMvcTest(ControllerForComment.class)
class ControllerForCommentTest {

    private static final String TEST_BOOK_NAME_1 = "Война и Мир";
    private static final String TEST_BOOK_NAME_2 = "Не стихов златая пена";
    private static final Book TEST_BOOK_1 = new Book(1, TEST_BOOK_NAME_1, new Author("Толстой"), Arrays.asList(new Genre("Роман")));
    private static final Book TEST_BOOK_2 = new Book(2, TEST_BOOK_NAME_2, new Author("Есенин"), Arrays.asList(new Genre("Стихи")));
    private static final Comment TEST_COMMENT_1 = new Comment(TEST_BOOK_1);
    private static final Comment TEST_COMMENT_2 = new Comment(TEST_BOOK_2);
    private static final String TEST_COMMENT_1_STRING = "Amazing";
    private static final String TEST_COMMENT_2_STRING = "Not so bad";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @BeforeEach
    void setUp() {
        TEST_COMMENT_1.setId(1);
        TEST_COMMENT_1.setComment(TEST_COMMENT_1_STRING);
        TEST_COMMENT_1.setId(2);
        TEST_COMMENT_2.setComment(TEST_COMMENT_2_STRING);
    }

    @DisplayName("должен верно отображаться комментарий по id")
    @Test
    void getCommentByBook() throws Exception {
        List<Comment> list = Arrays.asList(TEST_COMMENT_1);
        given(commentService.showByBook(any())).willReturn(list);
        mockMvc.perform(get("/comment/book").param("bookname", "Война и Мир").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(TEST_BOOK_NAME_1)))
                .andExpect(content().string(Matchers.containsString(TEST_COMMENT_1_STRING)));
    }

    @DisplayName("должны верно отобразиться страница результата добавления комментария и корректно переданы параметры для создания комментария")
    @Test
    void insertComment() throws Exception {
        given(commentService.insert(any())).willReturn("Комментарий для книги Война и Мир успешно добавлен");
        MvcResult result = mockMvc.perform(post("/comment/add")
                .param("bookname", TEST_BOOK_NAME_1)
                .param("comment", TEST_COMMENT_2_STRING))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Комментарий для книги Война и Мир успешно добавлен")))
                .andReturn();
        assertThat(result.getRequest().getAttribute("commentDto"))
                .hasFieldOrPropertyWithValue("bookname", TEST_BOOK_NAME_1)
                .hasFieldOrPropertyWithValue("comment", TEST_COMMENT_2_STRING);
    }

    @DisplayName("должна верно отобразиться страница добавления комментария")
    @Test
    void getPageInsert() throws Exception {
        MvcResult result = mockMvc.perform(get("/comment/insertcomment").param("bookname",TEST_BOOK_NAME_1).accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Добавление комментария к книге "+TEST_BOOK_NAME_1)))
                .andReturn();
        assertThat(result.getRequest().getAttribute("comment")).hasFieldOrPropertyWithValue("bookname",TEST_BOOK_NAME_1).isNotNull();
    }

    @DisplayName("должны верно отобразиться страница результата удаления комментария и корректно переданы параметры на удаление комментария")
    @Test
    void deleteCommentById() throws Exception {
        mockMvc.perform(post("/comment/add").param("bookname", TEST_BOOK_NAME_1).param("comment", TEST_COMMENT_2_STRING));
        given(commentService.deleteById(anyLong())).willReturn("Комментарий удалён");
        MvcResult result = mockMvc.perform(post("/comment/del").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Комментарий удалён")))
                .andReturn();
        assertThat(result.getRequest().getParameter("id")).isEqualTo("1");
    }
}