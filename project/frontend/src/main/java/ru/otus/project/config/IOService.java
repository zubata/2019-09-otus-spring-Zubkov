package ru.otus.project.config;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public interface IOService {
    void output(String s);

    String input();

    void writeToFile(List list, FileOutputStream out) throws IOException;
}
