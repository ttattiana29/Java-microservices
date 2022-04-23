package ru.itmo.kotikilab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itmo.kotikilab.entities.Owner;
import ru.itmo.kotikilab.repository.OwnerRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private OwnerRepository ownerRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Owner owner = ownerRepo.findByUsername(username);
        if (owner == null) {
            throw new UsernameNotFoundException("Unknown user: "+ username);
        }
        UserDetails user = User.builder()
                .username(owner.getUsername())
                .password(owner.getPassword())
                .roles(owner.getRole())
                .build();
        return user;
    }
}
