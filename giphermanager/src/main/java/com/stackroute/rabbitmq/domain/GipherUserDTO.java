package com.stackroute.rabbitmq.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GipherUserDTO {
    private String username;
    private String email;
    private List<FavouriteGIFDTO> favouriteGIFS;
    
}
