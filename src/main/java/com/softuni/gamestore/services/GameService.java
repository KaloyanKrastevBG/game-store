package com.softuni.gamestore.services;

import com.softuni.gamestore.domain.dtos.EditGameDto;
import com.softuni.gamestore.domain.dtos.GameAddDto;

public interface GameService {

    void addGame(GameAddDto gameAddDto);

   void editGame(EditGameDto editGameDto);

   void deleteGame(long id);
}
