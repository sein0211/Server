package com.server.tourApiProject.myWish;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyWishParams {
    private String thumbnail; //썸네일
    private String title; //제목
}
