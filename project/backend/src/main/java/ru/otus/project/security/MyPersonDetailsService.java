package ru.otus.project.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.project.domain.Person;
import ru.otus.project.stroge.PersonDao;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MyPersonDetailsService implements UserDetailsService {

    private final PersonDao personDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personDao.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority("ROLE_" + person.getRole()));
        return new org.springframework.security.core.userdetails.User(person.getUsername(), person.getPassword(), roles);
    }

}
