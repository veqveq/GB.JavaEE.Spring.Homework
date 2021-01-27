package ru.veqveq.lesson11.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.veqveq.lesson11.entities.Role;
import ru.veqveq.lesson11.entities.Score;
import ru.veqveq.lesson11.entities.User;
import ru.veqveq.lesson11.repositories.ScoreRepository;
import ru.veqveq.lesson11.repositories.UserRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ScoreRepository scoreRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void incScore(User user) {
        Score currentScore = scoreRepository.findByUser(user);
        currentScore.setScore(currentScore.getScore()+1);
        scoreRepository.save(currentScore);
    }

    public void decScore(User user) {
        Score currentScore = scoreRepository.findByUser(user);
        currentScore.setScore(currentScore.getScore()-1);
        scoreRepository.save(currentScore);
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
