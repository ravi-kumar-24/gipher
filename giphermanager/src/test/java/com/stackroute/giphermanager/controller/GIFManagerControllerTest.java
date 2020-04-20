package com.stackroute.giphermanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.giphermanager.domain.FavouriteGIF;
import com.stackroute.giphermanager.domain.GipherUser;
import com.stackroute.giphermanager.service.GIFManagerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(GIFManagerController.class)
public class GIFManagerControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    GIFManagerService gifManagerService;

    GipherUser gipherUser;
    FavouriteGIF favouriteGIF;
    List<FavouriteGIF> list;

    @Before
    public void setup(){
        favouriteGIF=new FavouriteGIF("abc","title","http://url","abc");
        list=new ArrayList<>();
        list.add(favouriteGIF);
        gipherUser=new GipherUser("user","email@gmail.com",list);
    }

    @After
    public void tearDown(){
        gipherUser=null;
        favouriteGIF=null;
        list=null;
    }

    @Test
    public void saveFavouriteGIFTest() throws Exception {
        when(gifManagerService.saveFavouriteGIF2DB(eq(gipherUser.getUsername()),any())).thenReturn(gipherUser);
        mockMvc.perform(post("/api/v1/gifmanager/user/{username}/favourite", gipherUser.getUsername())
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(favouriteGIF)))
                .andExpect(status().isCreated())
                .andDo(print());
        verify(gifManagerService,times(1)).saveFavouriteGIF2DB(eq(gipherUser.getUsername()),any());
    }

    @Test
    public void deleteFavouriteGIFTest() throws Exception{
        when(gifManagerService.deleteFavouriteGIFFromDB(gipherUser.getUsername(),favouriteGIF.getId())).thenReturn(gipherUser);
        mockMvc.perform(delete("/api/v1/gifmanager/user/{username}/{id}",gipherUser.getUsername(),favouriteGIF.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(favouriteGIF)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(gifManagerService,times(1)).deleteFavouriteGIFFromDB(gipherUser.getUsername(),favouriteGIF.getId());
    }

    @Test
    public void updateCommentsTest() throws Exception{
        when(gifManagerService.saveCommentsForFavouriteGIF(gipherUser.getUsername(),favouriteGIF.getComments(),favouriteGIF.getId())).thenReturn(gipherUser);
        mockMvc.perform(patch("/api/v1/gifmanager/user/{username}/{id}",gipherUser.getUsername(),favouriteGIF.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(favouriteGIF)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(gifManagerService,times(1)).saveCommentsForFavouriteGIF(gipherUser.getUsername(),favouriteGIF.getComments(),favouriteGIF.getId());
    }


    @Test
    public void getAllFavouriteGIFTest() throws Exception{
        when(gifManagerService.getAllFavouriteGIFromDB(gipherUser.getUsername())).thenReturn(gipherUser.getFavouriteGIFS());
        mockMvc.perform(get("/api/v1/gifmanager/user/{username}/favourites",gipherUser.getUsername())
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(favouriteGIF)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(gifManagerService,times(1)).getAllFavouriteGIFromDB(gipherUser.getUsername());
    }

    private static String toJson(FavouriteGIF favouriteGIF){
        String result=null;
        try {
            final ObjectMapper objectMapper=new ObjectMapper();
            final String json=objectMapper.writeValueAsString(favouriteGIF);
            result=json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;

    }
}
