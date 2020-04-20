package com.stackroute.giphermanager.domain;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FavouriteGIF {
    private String id;
    private String title;
    private String url;
    private String comments;
}
