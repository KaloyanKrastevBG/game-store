package com.softuni.gamestore.controllers;

import com.softuni.gamestore.domain.dtos.UserRegisterDto;
import com.softuni.gamestore.services.UserService;
import com.softuni.gamestore.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import java.io.BufferedReader;

@Component
public class AppController implements CommandLineRunner {

    private final BufferedReader bufferedReader;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserService userService;


    @Autowired
    public AppController(BufferedReader bufferedReader, ModelMapper modelMapper, ValidationUtil validationUtil, UserService userService) {
        this.bufferedReader = bufferedReader;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {

        while (true) {
            System.out.println("Please enter command:");
            String[] input = this.bufferedReader.readLine().split("\\|");

            switch (input[0]) {
                case "RegisterUser":

                    if (!input[2].equals(input[3])) {
                        System.out.println("Passwords dont match");
                        break;
                    }
                    UserRegisterDto userRegisterDto =
                            new UserRegisterDto(input[1], input[2], input[4]);

                    if (this.validationUtil.isValid(userRegisterDto)){
                            this.userService.registerUser(userRegisterDto);
                        System.out.printf("User %s is registered successfully!%n", input[4]);

                    } else {
                        this.validationUtil.
                                getViolation(userRegisterDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);

                    }

                    break;


            }

        }

    }
}
