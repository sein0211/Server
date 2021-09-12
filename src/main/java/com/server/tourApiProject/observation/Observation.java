package com.server.tourApiProject.observation;

import com.server.tourApiProject.bigPost.post.Post;
import com.server.tourApiProject.observation.course.Course;
import com.server.tourApiProject.observation.observeFee.ObserveFee;
import com.server.tourApiProject.observation.observeHashTag.ObserveHashTag;
import com.server.tourApiProject.observation.observeImage.ObserveImage;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="observation")
public class Observation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long observationId;

    @Column(nullable = false, unique = true)
    private String observationName;

    @Column
    private String link;

    @Column
    private String intro;   //한줄소개

    @Column(nullable = false)
    private Double latitude;    //지도를 위한 위도

    @Column(nullable = false)
    private Double longitude;    //지도를 위한 경도

    @Column(nullable = false)
    private String address;

    @Column
    private String phoneNumber; //문의

    @Column
    private String operatingHour;

    @Column
    private String parking; //주차안내

    @Column
    private String observeType;    //관측지 타입(천문대,등등), 추후 enum으로 수정가능?

    @Column
    private String outline; //개요

    @Column
    private String guide;   //이용안내

    @Column
    private String closedDay;   //휴무일

    @Column
    private Double light;   //광공해

    @Column
    private Boolean nature;   //자연관광지

    @Column
    private Integer courseOrder;   //코스내에서의 관측지 순서서

    @OneToMany(mappedBy = "observation")
    private List<ObserveFee> observeFees=new ArrayList<>();

   @OneToMany(mappedBy = "observation")
    private List<ObserveHashTag> observeHashTags=new ArrayList<>();

    @OneToMany(mappedBy = "observation")
    private List<ObserveImage> observeImages = new ArrayList<>();

    @OneToMany(mappedBy = "observation")
    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "observation", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Post> posts=new ArrayList<>();
}