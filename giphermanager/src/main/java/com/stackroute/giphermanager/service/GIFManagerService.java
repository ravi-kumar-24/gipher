package com.stackroute.giphermanager.service;

import com.stackroute.giphermanager.domain.FavouriteGIF;
import com.stackroute.giphermanager.domain.GipherUser;
import com.stackroute.giphermanager.exception.GIFAlreadyExistsException;
import com.stackroute.giphermanager.exception.GIFNotFoundException;
import com.stackroute.giphermanager.exception.UserNotFoundException;

import java.util.List;

public interface GIFManagerService {

    GipherUser saveFavouriteGIF2DB(String username,FavouriteGIF favouriteGIF) throws UserNotFoundException,GIFAlreadyExistsException;
    GipherUser deleteFavouriteGIFFromDB(String user,String id) throws UserNotFoundException,GIFNotFoundException;
    GipherUser saveCommentsForFavouriteGIF(String username,String comments, String id) throws UserNotFoundException,GIFNotFoundException;
    List<FavouriteGIF> getAllFavouriteGIFromDB(String username) throws UserNotFoundException,Exception;
    GipherUser createNewUser(GipherUser user);

}
