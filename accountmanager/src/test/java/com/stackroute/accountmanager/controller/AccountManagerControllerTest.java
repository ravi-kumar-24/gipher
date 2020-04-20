package com.stackroute.accountmanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.accountmanager.domain.GipherUser;
import com.stackroute.accountmanager.service.AccountManagerService;
import com.stackroute.accountmanager.service.JWTGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountManagerController.class)
public class AccountManagerControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    AccountManagerService accountManagerService;
    @MockBean
    JWTGenerator jwtGenerator;
    @InjectMocks
    AccountManagerController accountManagerController;
    GipherUser user;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc= MockMvcBuilders.standaloneSetup(accountManagerController).build();
        user=new GipherUser();
        user.setUsername("abc");
        user.setPassword("xyz");
        user.setEmail("abc@xyz.com");
    }

    @After
    public void tearDown(){
        user=null;
    }

    @Test
    public void registerUserTest() throws Exception{
        when(accountManagerService.saveUser(user)).thenReturn(user);
        mockMvc.perform(post("/api/v1/accountmanager/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(user)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(accountManagerService,times(1)).saveUser(user);
    }

    @Test
    public void loginUserTest() throws Exception{
        when(accountManagerService.findByUsernameAndPassword(user.getUsername(),user.getPassword())).thenReturn(user);
        mockMvc.perform(post("/api/v1/accountmanager/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(user)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(accountManagerService,times(1)).findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    private static String toJson(GipherUser gipherUser){
        String result=null;
        try {
            final ObjectMapper objectMapper=new ObjectMapper();
            final String json=objectMapper.writeValueAsString(gipherUser);
            result=json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;

    }

}
