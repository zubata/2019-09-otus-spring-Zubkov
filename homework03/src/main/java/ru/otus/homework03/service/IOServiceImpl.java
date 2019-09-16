package ru.otus.homework03.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class IOServiceImpl implements IOService {

    private BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));;

    @Override
    public void output(String s) {
        System.out.println(s);
    }

    @Override
    public String input() throws IOException { return rd.readLine(); }
}
