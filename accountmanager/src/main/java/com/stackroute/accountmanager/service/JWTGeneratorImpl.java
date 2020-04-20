package com.stackroute.accountmanager.service;

import com.stackroute.accountmanager.domain.GipherUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTGeneratorImpl implements JWTGenerator {
    @Override
    public Map<String, String> generateToken(GipherUser user) {
        String jwtToken= Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"s9cr3at_k3y")
                .compact();

        Map<String,String> map=new HashMap<>();
        map.put("token",jwtToken);
        map.put("message","User logged in ..");
        return map;
    }
}
