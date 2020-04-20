package com.stackroute.giphermanager.controller;

import com.stackroute.giphermanager.domain.FavouriteGIF;
import com.stackroute.giphermanager.domain.GipherUser;
import com.stackroute.giphermanager.exception.GIFAlreadyExistsException;
import com.stackroute.giphermanager.exception.GIFNotFoundException;
import com.stackroute.giphermanager.exception.UserNotFoundException;
import com.stackroute.giphermanager.service.GIFManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/gifmanager")
public class GIFManagerController {

    GIFManagerService gifyService;

    @Autowired
    public GIFManagerController(GIFManagerService gifyService){
        this.gifyService=gifyService;
    }

    @PostMapping("user/{username}/favourite")
    ResponseEntity<?> saveGIsaveFavouriteGIF2DB(@RequestBody FavouriteGIF favouriteGIF,@PathVariable("username") String username) throws UserNotFoundException,GIFAlreadyExistsException {
        gifyService.saveFavouriteGIF2DB(username,favouriteGIF);
        return ResponseEntity.status(HttpStatus.CREATED).body(favouriteGIF);
    }

    @DeleteMapping("user/{username}/{id}")
    ResponseEntity<?> deleteFavouriteGIFFromDB(@PathVariable("id") String id,@PathVariable("username") String username) throws UserNotFoundException,GIFNotFoundException {
        GipherUser gipherUser=gifyService.deleteFavouriteGIFFromDB(username,id);
        return ResponseEntity.status(HttpStatus.OK).body(gipherUser);
    }

    @PatchMapping("user/{username}/{id}")
    ResponseEntity<?> updateCommentsForFavouriteGIF(@PathVariable("id") String id,@RequestBody FavouriteGIF favouriteGIF,@PathVariable("username") String username) throws UserNotFoundException,GIFNotFoundException{
        GipherUser user =gifyService.saveCommentsForFavouriteGIF(username,favouriteGIF.getComments(),id);
        return ResponseEntity.status(HttpStatus.OK).body(favouriteGIF);
    }

    @GetMapping("user/{username}/favourites")
    ResponseEntity<?> getAllFavouriteGIF(@PathVariable("username") String username) throws UserNotFoundException,Exception{
        return ResponseEntity.status(HttpStatus.OK).body(gifyService.getAllFavouriteGIFromDB(username));
    }


}
