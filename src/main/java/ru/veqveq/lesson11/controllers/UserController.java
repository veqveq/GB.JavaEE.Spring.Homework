package ru.veqveq.lesson11.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.veqveq.lesson11.entities.User;
import ru.veqveq.lesson11.exception_handling.UserNotFoundException;
import ru.veqveq.lesson11.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/score")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/inc")
    public String incUserScore(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() ->
                new RuntimeException(String.format("Unable to find user by name : %s", principal.getName())));
        userService.incScore(user);
        return String.format("Score INC [User: %s; Score: %d]", user.getUsername(), user.getScores().getScore());
    }

    @GetMapping("/dec")
    public String decUserScore(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() ->
                new RuntimeException(String.format("Unable to find user by name : %s", principal.getName())));
        userService.decScore(user);
        return String.format("Score DEC [User: %s; Score: %d]", user.getUsername(), user.getScores().getScore());
    }

    @GetMapping("/get/{id}")
    public String getUserScoreById(@PathVariable Long id) {
        User user = userService.findById(id).orElseThrow(() ->
                new UserNotFoundException(String.format("User [id: %d] not found", id))
        );
        return String.format("Score by [ID: %d User: %s; Score: %d]", id, user.getUsername(), user.getScores().getScore());
    }


}
