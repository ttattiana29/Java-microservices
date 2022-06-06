package com.example.security;

import com.example.services.MainService;
import com.example.tools.KotikiException;
import com.example.wrapper.OwnerWrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthencationProvider implements AuthenticationProvider {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private MainService mainService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        kafkaTemplate.send("findOwnerByUsername", username);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        OwnerWrap owner = mainService.ownerWrap;
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
