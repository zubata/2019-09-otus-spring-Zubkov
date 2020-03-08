package ru.otus.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class IOServiceImpl implements IOService {

    private BufferedReader rd;
    private PrintStream stream;

    @Autowired
    public IOServiceImpl() throws UnsupportedEncodingException {
        rd = new BufferedReader(new InputStreamReader(System.in));
        stream = new PrintStream(System.out, true, "UTF-8");
    }

    @Override
    public void output(String s) {
        stream.println(s);
    }

    @Override
    public String input() {
        try {
            return rd.readLine();
        } catch (IOException e) {
            new RuntimeException("Не введены данные");
        }
        return null;
    }

    @Override
    public void writeToFile(List list, FileOutputStream out) throws IOException {
        for (int i = 0; i < list.size(); i++) {
            String line = list.get(i).toString() + "\n";
            out.write(line.getBytes());
            out.flush();
        }
    }
}
