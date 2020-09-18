package ru.ifmo.nefrology.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.nefrology.entity.User;
import ru.ifmo.nefrology.repository.UserRepository;

@RestController
public class UserController {
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/personalOffice") //Личный кабинет, обновление данных пользователя (остальные поля). метод должен вернуть Успех или причину проблемы
    public @ResponseBody
    String personalOffice(@RequestBody User user) { //клиент Андроид собирает объект User, формирует Json строчку
        // отправляет POST запрос на сервер на регистрацию (локал хост /registration) и в этом запросе в виде Json передаем информацию
        if (!userRepository.findByLogin(user.getLogin()).isEmpty()) {
            User anotherUser = userRepository.findByLogin(user.getLogin()).get();
            if (anotherUser.getPassword().equals(user.getPassword())) {
                anotherUser.setLogin(user.getLogin());
                anotherUser.setSecondName(user.getSecondName());
                anotherUser.setFirstName(user.getFirstName());
                anotherUser.setSex(user.getSex());
                userRepository.save(anotherUser);
                return "ok";
            }
            return "не совпадают пароли";
        }
        return "ERROR";
    }

    @PostMapping("/registration") //регистрация пользователя. метод должен вернуть Успех или причину проблемы
    public @ResponseBody
    String registration(@RequestBody User user) { //клиент Андроид собирает объект User, формирует Json строчку
        // отправляет POST запрос на сервер на регистрацию (локал хост /registration) и в этом запросе в виде Json передаем информацию
        System.out.println(user.getLogin());
        if (userRepository.findByLogin(user.getLogin()).isEmpty()) { // метод вернет объект User по логину
            userRepository.save(user);
            return "ok_reg";
        } else return "user with this E-mail already exists in system";
    }

    @PostMapping("/authorization") //Авторизация пользователя. метод должен вернуть Успех или причину проблемы
    public @ResponseBody
    String authorization(@RequestParam(value = "login") String login, @RequestParam(value = "password") String passwordAndr) { //клиент Андроид
        // отправляет POST запрос на сервер на авторизацию (локал хост /authorization) и в этом запросе в виде Json
        // передаем данные в виде параметров ключ-значение Логин/Пароль
//        System.out.println(login + ": " + passwordAndr);
        if (!userRepository.findByLogin(login).isEmpty()) { // метод вернет объект User по логину
            User user = userRepository.findByLogin(login).get();
            if (user.getPassword().equals(passwordAndr)) return "ok_auth";
            else return "authorization failed";
        } else return "didn't find user with this E-mail";
    }
}