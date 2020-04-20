package com.stackroute.accountmanager.controller;


import com.stackroute.accountmanager.domain.GipherUser;
import com.stackroute.accountmanager.exception.UserNotFoundException;
import com.stackroute.accountmanager.service.AccountManagerService;
import com.stackroute.accountmanager.service.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/accountmanager")
public class AccountManagerController {


    AccountManagerService accountManagerService;
    JWTGenerator jwtGenerator;

    @Autowired
    public AccountManagerController(AccountManagerService accountManagerService,JWTGenerator jwtGenerator){
        this.accountManagerService=accountManagerService;
        this.jwtGenerator=jwtGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody GipherUser user){
        GipherUser registeredUser= accountManagerService.saveUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(registeredUser);
    }

    @GetMapping("/user/abc")
    public void dummyMethod(){
        System.out.println("dummy method");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody GipherUser user) throws UserNotFoundException {
        Map<String,String> map=null;
        GipherUser registeredUser=accountManagerService.findByUsernameAndPassword(user.getUsername(),user.getPassword());
        if(registeredUser.getUsername().equals(user.getUsername())){
           map=jwtGenerator.generateToken(registeredUser);
        }
        return ResponseEntity.status(HttpStatus.OK).body(map);

    }

}
