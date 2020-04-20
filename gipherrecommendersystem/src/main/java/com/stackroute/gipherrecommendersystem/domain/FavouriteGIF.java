package com.stackroute.gipherrecommendersystem.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document("recommendations")
public class FavouriteGIF {
    @Id
    private String id;
    private String title;
    private String url;
    private int downloadsCount;
}
