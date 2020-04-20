package com.stackroute.accountmanager.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class GipherUser {

    @Id @GeneratedValue
    private int id;
    private String username;
    private String email;
    private String password;
	
}
