package com.softuni.gamestore.services.impls;

import com.softuni.gamestore.domain.dtos.UserRegisterDto;
import com.softuni.gamestore.domain.entities.Role;
import com.softuni.gamestore.domain.entities.User;
import com.softuni.gamestore.repositories.UserRepository;
import com.softuni.gamestore.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {

        User user = this.modelMapper.map(userRegisterDto, User.class);

        user.setRole(this.userRepository.count() == 0 ? Role.ADMIN : Role.USER);

        this.userRepository.saveAndFlush(user);

    }
}
