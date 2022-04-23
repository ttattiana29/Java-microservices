package ru.itmo.kotikilab.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.itmo.kotikilab.entities.Owner;
import ru.itmo.kotikilab.repository.OwnerRepository;
import ru.itmo.kotikilab.tools.KotikiException;

@Component
public class CustomAuthencationProvider implements AuthenticationProvider {
    @Autowired
    private OwnerRepository ownerRepo;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Owner owner = ownerRepo.findByUsername(username);
        if(owner == null) {
            throw new KotikiException("Unknown owner" + username);
        }
        if(!password.equals(owner.getPassword())) {
            throw new KotikiException("Bad password");
        }
        UserDetails principal = User.builder()
                .username(owner.getUsername())
                .password(owner.getPassword())
                .roles(owner.getRole())
                .build();
        return new UsernamePasswordAuthenticationToken(principal, password, principal.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
