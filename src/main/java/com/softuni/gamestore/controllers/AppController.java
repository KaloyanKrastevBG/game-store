package com.softuni.gamestore.controllers;

import com.softuni.gamestore.domain.dtos.EditGameDto;
import com.softuni.gamestore.domain.dtos.GameAddDto;
import com.softuni.gamestore.domain.dtos.UserLoginDto;
import com.softuni.gamestore.domain.dtos.UserRegisterDto;
import com.softuni.gamestore.services.GameService;
import com.softuni.gamestore.services.UserService;
import com.softuni.gamestore.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import java.io.BufferedReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class AppController implements CommandLineRunner {

    private final BufferedReader bufferedReader;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserService userService;
    private final GameService gameService;


    @Autowired
    public AppController(BufferedReader bufferedReader, ModelMapper modelMapper, ValidationUtil validationUtil, UserService userService, GameService gameService) {
        this.bufferedReader = bufferedReader;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.gameService = gameService;
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

                    if (this.validationUtil.isValid(userRegisterDto)) {
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
                case "LoginUser":
                    UserLoginDto userLoginDto =
                            new UserLoginDto(input[1], input[2]);

                    this.userService.loginUser(userLoginDto);
                    break;

                case "Logout":
                    this.userService.logout();
                    break;

                case "AddGame":
                    GameAddDto gameAddDto = new GameAddDto(
                            input[1], new BigDecimal(input[2]),
                            Double.parseDouble(input[3]), input[4],
                            input[5], input[6], LocalDate.parse(input[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"))

                    );

                    if (this.validationUtil.isValid(gameAddDto)) {
                        this.gameService.addGame(gameAddDto);


                    } else {
                        this.validationUtil
                                .getViolation(gameAddDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);

                    }

                    break;

                case "EditGame":

                    int id = Integer.parseInt(input[1]);
                    String[] priceParam = input[2].split("=");
                    String[] sizeParam = input[3].split("=");

                    EditGameDto editGameDto = new EditGameDto(id, new BigDecimal(priceParam[1]),
                            Double.parseDouble(sizeParam[1]));

                    this.gameService.editGame(editGameDto);

                    break;

                case "DeleteGame":
                    try {
                        this.gameService.deleteGame(Long.parseLong(input[1]));

                    } catch (Exception ex){
                        System.out.println("No such game in GameRepo");
                        System.out.printf("%s%n", ex.getMessage());
                    }

                    break;

                case "AllGames":
                    this.gameService.printTitleAndPriceofAllGames();
                    break;

                case "DetailGame":
                    this.gameService.detailGame(input[1]);
                    break;


            }

        }

    }
}
