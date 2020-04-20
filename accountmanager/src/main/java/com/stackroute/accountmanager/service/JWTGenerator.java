package com.stackroute.accountmanager.service;

import com.stackroute.accountmanager.domain.GipherUser;

import java.util.Map;

public interface JWTGenerator {
    Map<String,String> generateToken(GipherUser user);
}
