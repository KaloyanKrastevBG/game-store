package com.softuni.gamestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;

@Component
public class AppController implements CommandLineRunner {

    private final BufferedReader bufferedReader;


    @Autowired
    public AppController(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }


    @Override
    public void run(String... args) throws Exception {

        while (true){

            String[] input = this.bufferedReader.readLine().split("\\|");

            switch (input[0]){
                case "RegisterUser":

                    System.out.println();
                    break;


            }

        }

    }
}
