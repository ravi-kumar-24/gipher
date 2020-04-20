package com.stackroute.accountmanager.service;

import com.stackroute.accountmanager.domain.GipherUser;
import com.stackroute.accountmanager.exception.UserNotFoundException;

public interface AccountManagerService {
    public GipherUser saveUser(GipherUser user);
    public GipherUser findByUsernameAndPassword(String userName,String passsword) throws UserNotFoundException;

}
