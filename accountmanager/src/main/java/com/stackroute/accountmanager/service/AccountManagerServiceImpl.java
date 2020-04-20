package com.stackroute.accountmanager.service;

import com.stackroute.accountmanager.config.RabbitMQMessageProducer;
import com.stackroute.accountmanager.domain.GipherUser;
import com.stackroute.accountmanager.exception.UserNotFoundException;
import com.stackroute.accountmanager.repository.AccountManagerRepository;
import com.stackroute.rabbitmq.domain.GipherUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountManagerServiceImpl implements AccountManagerService {

    AccountManagerRepository accountManagerRepository;
    RabbitMQMessageProducer producer;

    @Autowired
    public AccountManagerServiceImpl(AccountManagerRepository accountManagerRepository, RabbitMQMessageProducer producer){
        this.accountManagerRepository=accountManagerRepository;
        this.producer=producer;
    }

    @Override
    public GipherUser saveUser(GipherUser user) {
        GipherUser savedUser=accountManagerRepository.save(user);
        //push to rabbit MQ user queue
        GipherUserDTO userDTO=new GipherUserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        producer.sendMessage2UserQueue(userDTO);
        //
        return savedUser;

    }

    @Override
    public GipherUser findByUsernameAndPassword(String userName, String password) throws UserNotFoundException {
        GipherUser user=accountManagerRepository.findByUsernameAndPassword(userName,password);
        if(user!=null){
            return user;
        }
        throw new UserNotFoundException();
    }
}
