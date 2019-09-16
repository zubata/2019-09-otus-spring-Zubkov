package ru.otus.service;

import java.io.IOException;

public interface InOutService {

    void output(String s);
    String input() throws IOException;

}
