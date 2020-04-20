package com.stackroute.rabbitmq.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FavouriteGIFDTO {
    private String id;
    private String title;
    private String url;
    private String comments;
    private int downloadCount;
}
