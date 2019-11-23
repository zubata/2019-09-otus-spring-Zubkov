package ru.otus.spring.homework10.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.homework10.domain.Author;
import ru.otus.spring.homework10.domain.Book;
import ru.otus.spring.homework10.domain.Comment;
import ru.otus.spring.homework10.domain.Genre;
import ru.otus.spring.homework10.service.CommentService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    public String toJSON(Comment comment) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(comment);
    }

    @DisplayName("должен верно отображаться комментарий по id")
    @Test
    void getCommentByBook() throws Exception {
        List<Comment> list = Arrays.asList(TEST_COMMENT_1);
        given(commentService.showByBook(any())).willReturn(list);
        mockMvc.perform(get("/api/comment/book").param("bookname", "Война и Мир").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(TEST_BOOK_NAME_1)))
                .andExpect(content().string(Matchers.containsString(TEST_COMMENT_1_STRING)));
    }

    @DisplayName("должны верно отобразиться страница результата добавления комментария и корректно переданы параметры для создания комментария")
    @Test
    void insertComment() throws Exception {
        String inputJson = String.format("{ \"bookname\" : \"%s\", \"comment\" : \"%s\" }",TEST_BOOK_NAME_1,TEST_COMMENT_2_STRING);
        given(commentService.insert(any())).willReturn(TEST_COMMENT_1);
        mockMvc.perform(post("/api/comment/book")
                .contentType(MediaType.APPLICATION_JSON).content(inputJson))
                .andExpect(status().isCreated())
                .andExpect(content().json(toJSON(TEST_COMMENT_1)));
    }

    @DisplayName("должны верно отобразиться страница результата удаления комментария и корректно переданы параметры на удаление комментария")
    @Test
    void deleteCommentById() throws Exception {
        String inputJson = String.format("{ \"bookname\" : \"%s\", \"comment\" : \"%s\" }",TEST_BOOK_NAME_1,TEST_COMMENT_2_STRING);
        mockMvc.perform(post("/api/comment/book").contentType(MediaType.APPLICATION_JSON).content("{ \"id\" : \"1\" }"));
        mockMvc.perform(delete("/api/comment/book").contentType(MediaType.APPLICATION_JSON).content(inputJson))
                .andExpect(status().isOk());
    }
}