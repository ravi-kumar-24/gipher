package com.stackroute.accountmanager.repository;

import com.stackroute.accountmanager.domain.GipherUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountManagerRepository extends JpaRepository<GipherUser,Integer> {
    public GipherUser findByUsernameAndPassword(String username,String password);
}
