package com.example.services;

import com.example.wrapper.OwnerWrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private MainService mainService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        kafkaTemplate.send("findOwnerByUsername", username);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        OwnerWrap owner = mainService.ownerWrap;
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