package com.server.tourApiProject.search;

import com.server.tourApiProject.bigPost.post.PostParams6;
import com.server.tourApiProject.bigPost.post.PostService;
import com.server.tourApiProject.observation.ObservationService;
import com.server.tourApiProject.touristPoint.touristData.TouristDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(tags = {"8.1 검색결과"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class SearchController {

    private final ObservationService observationService;
    private final TouristDataService touristDataService;
    private final PostService postService;

    @ApiOperation(value = "관측지 검색결과 ", notes = "검색어와 필터로 관측지 검색결과를 조회한다")
    @PostMapping(value = "search/observation")
    public List<SearchParams1> getObservationWithFilter(@RequestBody SearchKey searchKey){
        return observationService.getObservationWithFilter(searchKey.getFilter(), searchKey.getKeyword());
    }

    @ApiOperation(value = "관광지 검색결과 ", notes = "검색어와 필터로 관광지 검색결과를 조회한다")
    @PostMapping(value = "search/touristPoint")
    public List<SearchParams1> getTouristPointWithFilter(@RequestBody SearchKey searchKey){
        return touristDataService.getTouristPointWithFilter(searchKey.getFilter(), searchKey.getKeyword());
    }
    @ApiOperation(value = "게시물 정보 필터로 조회", notes = "필터로 걸러진 게시물을 조회한다")
    @PostMapping(value = "search/post")
    public List<PostParams6> getPostWithFilter(@RequestBody SearchKey searchKey){
        return postService.getPostDataWithFilter(searchKey.getFilter(),searchKey.getKeyword());
    }

}
