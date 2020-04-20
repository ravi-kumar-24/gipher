package com.stackroute.giphermanager.service;

import com.stackroute.giphermanager.config.RabbitMQMessageProducer;
import com.stackroute.giphermanager.domain.FavouriteGIF;
import com.stackroute.giphermanager.domain.GipherUser;
import com.stackroute.giphermanager.exception.GIFAlreadyExistsException;
import com.stackroute.giphermanager.exception.GIFNotFoundException;
import com.stackroute.giphermanager.exception.UserNotFoundException;
import com.stackroute.giphermanager.repository.GIFManagerRepository;
import com.stackroute.rabbitmq.domain.FavouriteGIFDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GIFManagerServiceImpl implements GIFManagerService {

    GIFManagerRepository gifyRepository;
    RabbitMQMessageProducer rabbitMQMessageProducer;

    @Autowired
    public GIFManagerServiceImpl(GIFManagerRepository gifyRepository, RabbitMQMessageProducer rabbitMQMessageProducer){
        this.gifyRepository=gifyRepository;
        this.rabbitMQMessageProducer=rabbitMQMessageProducer;
    }


    @Override
    public GipherUser saveFavouriteGIF2DB(String username,FavouriteGIF favouriteGIF) throws UserNotFoundException,GIFAlreadyExistsException {
        GipherUser user=gifyRepository.findByUsername(username);
        if(user==null){
            throw new UserNotFoundException();
        }
        List<FavouriteGIF> fetchedList=user.getFavouriteGIFS();
        if(fetchedList!=null){
            for(FavouriteGIF gifObj:fetchedList){
                if(gifObj.getId().equals(favouriteGIF.getId())){
                    throw new GIFAlreadyExistsException();
                }
            }
            fetchedList.add(favouriteGIF);
            user.setFavouriteGIFS(fetchedList);
            gifyRepository.save(user);

        }else{
            fetchedList=new ArrayList<>();
            fetchedList.add(favouriteGIF);
            user.setFavouriteGIFS(fetchedList);
            gifyRepository.save(user);

        }

        FavouriteGIFDTO favouriteGIFDTO=new FavouriteGIFDTO(favouriteGIF.getId(),favouriteGIF.getTitle(),favouriteGIF.getUrl(),favouriteGIF.getComments(),1);
        rabbitMQMessageProducer.sendMessageToReccomendationQueue(favouriteGIFDTO);
        return user;
    }

    @Override
    public GipherUser deleteFavouriteGIFFromDB(String username,String id) throws UserNotFoundException,GIFNotFoundException {
        GipherUser user=gifyRepository.findByUsername(username);
        if(user==null){
            throw new UserNotFoundException();
        }
        List<FavouriteGIF> fetchedList=user.getFavouriteGIFS();

        if(fetchedList.size()>0){
            for(int i=0;i<fetchedList.size();i++){
                if(id.equals(fetchedList.get(i).getId())){
                    fetchedList.remove(i);
                    user.setFavouriteGIFS(fetchedList);
                    gifyRepository.save(user);
                    break;
                }
            }
        }else{
            throw new GIFNotFoundException();
        }
        return user;
    }

    @Override
    public GipherUser saveCommentsForFavouriteGIF(String username,String comments, String id) throws UserNotFoundException,GIFNotFoundException {
        GipherUser user=gifyRepository.findByUsername(username);
        if(user==null){
            throw new UserNotFoundException();
        }
        List<FavouriteGIF> fetchedList=user.getFavouriteGIFS();

        if(fetchedList.size()>0){
            for(int i=0;i<fetchedList.size();i++){
                if(id.equals(fetchedList.get(i).getId())){
                    fetchedList.get(i).setComments(comments);
                    gifyRepository.save(user);
                    break;
                }
            }
        }else{
            throw new GIFNotFoundException();
        }
        return user;
    }

    @Override
    public List<FavouriteGIF> getAllFavouriteGIFromDB(String username) throws Exception {
        GipherUser user=gifyRepository.findByUsername(username);
        if(user==null){
            throw new UserNotFoundException();
        }
        return user.getFavouriteGIFS();
    }

    @Override
    public GipherUser createNewUser(GipherUser user) {
        gifyRepository.save(user);
        return user;
    }
}
