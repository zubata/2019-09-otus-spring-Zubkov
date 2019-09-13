package ru.otus.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class InOutServiceImpl implements InOutService{

    private BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));;

    @Override
    public void output(String s) {
        System.out.println(s);
    }

    @Override
    public String input() throws IOException { return rd.readLine(); }
}
