package ru.otus.homework03.service;

import java.io.IOException;

public interface IOService {

    void output(String s);
    String input() throws IOException;

}
