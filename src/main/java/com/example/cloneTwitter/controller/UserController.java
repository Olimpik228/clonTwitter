package com.example.cloneTwitter.controller;

import com.example.cloneTwitter.domain.Role;
import com.example.cloneTwitter.domain.User;
import com.example.cloneTwitter.repos.UserRepo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());

        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String,String> form,
            @RequestParam("userId") User user
    ) {
        user.setUsername(username);           // устанавливаем пользователю имя, которое пришло с фронта
        //берем все существующие в приложении роли,
        // преобразуем массив этих ролей в стрим (java stream api),
        // где получаем имена ролей и полученый список имён складываем в set
Set<String> roles = Arrays.stream(Role.values())
        .map(Role::name)
        .collect(Collectors.toSet());

user.getRoles().clear(); //очищаем персистентную коллекцию от установленных ролей
//обходим список полей, пришедших от пользователя,
// проверяем, если имя какого-то поля является именем роли,
// то ищем такую роль в enum Role и устанавливаем эти роли пользователю.
        for (String key : form.keySet()) {
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
            
        }
        //Почему так:
        // 1) коллекции у объектов, полученных через JPA не являются обычными коллекциями,
        // их нельзя просто заменить, можно только обновлять.
        // 2) чтобы не городить каких-то сложных проверок, мы просто берем всё поля,
        // полученные от пользователя и проверяем, являются ли они ролями,
        // если да - устанавливаем их пользователю,
        // обновляя таким образом нашу персистентную коллекцию ролей﻿


        userRepo.save(user);
        return "redirect:/user";
    }
}