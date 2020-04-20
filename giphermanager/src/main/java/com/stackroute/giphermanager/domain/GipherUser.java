package com.stackroute.giphermanager.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document("favourite")
public class GipherUser {
    @Id
    private String username;
    private String email;
    private List<FavouriteGIF> favouriteGIFS;
}
