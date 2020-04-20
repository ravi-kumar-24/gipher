package com.stackroute.giphermanager.repository;

import com.stackroute.giphermanager.domain.FavouriteGIF;
import com.stackroute.giphermanager.domain.GipherUser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataMongoTest
public class GIFManagerRepositoryTest {

    @Autowired
    GIFManagerRepository gifManagerRepository;

    private FavouriteGIF favouriteGIF;
    private GipherUser user;

    @Before
    public void setup(){
        favouriteGIF =new FavouriteGIF("123","gif-title","http://url",null);
        ArrayList<FavouriteGIF> gifs=new ArrayList<>();
        gifs.add(favouriteGIF);
        user=new GipherUser("abc","email@gamil.com",gifs);
    }

    @After
    public void teardown(){
        user=null;
        favouriteGIF =null;
        gifManagerRepository.deleteAll();
    }

    @Test
    public void saveUserGIFsTest(){
        gifManagerRepository.insert(user);
        GipherUser fetchedGipherUser = gifManagerRepository.findByUsername(user.getUsername());
        Assert.assertEquals(fetchedGipherUser.getFavouriteGIFS().get(0).getId(), favouriteGIF.getId());
    }

    /*@Test
    public void updateFavouriteGIFCommentTest(){
        gifManagerRepository.insert(user);
        GipherUser fetchedGipherUser = gifManagerRepository.findByUsername(user.getUsername());
        fetchedGipherUser.getFavouriteGIFS().get(0).setComments("comments test");
        gifManagerRepository.save(fetchedGipherUser);
        fetchedGipherUser = gifManagerRepository.findByUsername(fetchedGipherUser.getUsername());
        Assert.assertEquals(user.getFavouriteGIFS().get(0).getId(), fetchedGipherUser.getFavouriteGIFS().get(0).getId());
        Assert.assertEquals("comments test",fetchedGipherUser.getFavouriteGIFS().get(0).getComments());
    }
*/
    /*@Test
    public void deleteBookmarkTest(){
        gifManagerRepository.insert(user);
        GipherUser fetchedGipherUser = gifManagerRepository.findByUsername(user.getUsername());
        gifManagerRepository.delete(fetchedGipherUser);
        Assert.assertEquals(Optional.empty(), gifManagerRepository.findById(favouriteGIF.getId()));
    }*/

    @Test
    public void getAllBookmarksTest(){
        favouriteGIF =new FavouriteGIF("345","gif-title","http://url",null);
        user.getFavouriteGIFS().add(favouriteGIF);
        gifManagerRepository.save(user);
        List<FavouriteGIF> list= gifManagerRepository.findByUsername(user.getUsername()).getFavouriteGIFS();
        Assert.assertEquals(2,list.size());
        Assert.assertEquals("123",list.get(0).getId());
        Assert.assertEquals("345",list.get(1).getId());
    }


}
