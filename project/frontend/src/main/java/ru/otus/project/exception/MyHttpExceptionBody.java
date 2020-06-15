package ru.otus.project.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import lombok.Data;

import java.util.Date;

@Data
public class MyHttpExceptionBody {

    private static final ObjectMapper mapper = new ObjectMapper();

    private Date timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public static MyHttpExceptionBody getHttpException(Response response) {
        String body = response.body().toString();
        MyHttpExceptionBody httpException = null;
        try { httpException = mapper.readValue(body, MyHttpExceptionBody.class); }
        catch (Exception e) { e.printStackTrace(); }
        return httpException;
    }

}
