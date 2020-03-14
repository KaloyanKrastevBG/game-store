package com.softuni.gamestore.services.impls;

import com.softuni.gamestore.domain.dtos.EditGameDto;
import com.softuni.gamestore.domain.dtos.GameAddDto;
import com.softuni.gamestore.domain.entities.Game;
import com.softuni.gamestore.repositories.GameRepository;
import com.softuni.gamestore.services.GameService;
import com.softuni.gamestore.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper, UserService userService) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @Override
    public void addGame(GameAddDto gameAddDto) {

        try {
            if (!this.userService.isLoggedUserIsAdmin()) {
                System.out.println("User is not admin");
                return;
            }
            Game game = this.modelMapper
                    .map(gameAddDto, Game.class);

            this.gameRepository.saveAndFlush(game);
            System.out.printf("Added %s%n", game.getTitle());

        } catch (Exception ex) {
            System.out.println("No user is logged in. Log in first!");

        }
    }

    @Override
    public void editGame(EditGameDto editGameDto) {

        Game game = this.gameRepository.findById(editGameDto.getId());

        game.setPrice(editGameDto.getPrice());
        game.setSize(editGameDto.getSize());

        this.gameRepository.save(game);
        System.out.printf("Game %s successfully edited%n", game.getTitle());


    }


}
