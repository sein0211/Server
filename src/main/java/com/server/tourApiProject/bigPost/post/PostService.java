package com.server.tourApiProject.bigPost.post;

import com.server.tourApiProject.bigPost.postHashTag.PostHashTag;
import com.server.tourApiProject.bigPost.postHashTag.PostHashTagRepository;
import com.server.tourApiProject.bigPost.postImage.PostImage;
import com.server.tourApiProject.bigPost.postImage.PostImageRepository;
import com.server.tourApiProject.bigPost.postObservePoint.PostObservePoint;
import com.server.tourApiProject.bigPost.postObservePoint.PostObservePointRepository;
import com.server.tourApiProject.user.User;
import com.server.tourApiProject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostObservePointRepository postObservePointRepository;
    private final PostImageRepository postImageRepository;
    private final PostHashTagRepository postHashTagRepository;

    public Post getPost(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(IllegalAccessError::new);
        return post;
    }

    public Long createPost(String observePointName, PostParams postParams) {
        Post post = new Post();
        PostObservePoint postObservePoint = postObservePointRepository.findByObservePointName(observePointName);
        Long postObservePointId = postObservePoint.getPostObservePointId();
        post.setPostContent(postParams.getPostContent());
        post.setPostTitle(postParams.getPostTitle());
        post.setYearDate(postParams.getYearDate());
        post.setTime(postParams.getTime());
        post.setUser(userRepository.findById(postParams.getUserId()).orElseThrow(IllegalAccessError::new));
        post.setUserId(postParams.getUserId());
        post.setPostObservePoint(postObservePoint);
        post.setPostObservePointId(postObservePointId);
        postRepository.save(post);
        return post.getPostId();
    }

    public List<PostParams2> getMyPost3(Long userId) {
        List<PostParams2> result = new ArrayList<>();
        List<Post> list = postRepository.findByUserId(userId);
        List<Post> three = new ArrayList<>();
        int len = list.size();
        if (len > 3){
            for (int i=len-1; i>len-4; i--){
                three.add(list.get(i));
            }
        } else {
            three = list;
        }

        for (Post post : three){
            PostParams2 postParams2 = new PostParams2();
            postParams2.setTitle(post.getPostTitle());
            List<PostImage> imageList = postImageRepository.findByPostId(post.getPostId());
            if (!imageList.isEmpty()) {
                PostImage postImage = imageList.get(0);
                postParams2.setThumbnail(postImage.getImageName());
            } else{
                postParams2.setThumbnail(null);
            }

            result.add(postParams2);
        }
        return result;
    }

    public List<PostParams3> getMyPost(Long userId) {
        List<PostParams3> result = new ArrayList<>();
        List<Post> posts = postRepository.findByUserId(userId);
        for (Post post : posts){
            PostParams3 postParams3 = new PostParams3();
            postParams3.setPostId(post.getPostId());

            List<PostImage> imageList = postImageRepository.findByPostId(post.getPostId());
            if (!imageList.isEmpty()) {
                PostImage postImage = imageList.get(0);
                postParams3.setThumbnail(postImage.getImageName());
            } else{
                postParams3.setThumbnail(null);
            }
            postParams3.setTitle(post.getPostTitle());
            Optional<User> userOp = userRepository.findById(post.getUserId());
            if (userOp.isPresent()){
                User user = userOp.get();
                postParams3.setNickName(user.getNickName());
                postParams3.setProfileImage(user.getProfileImage());
            }
            List<String> hashTagName = new ArrayList<>();
            List<PostHashTag> list = postHashTagRepository.findByPostId(post.getPostId());
            for(PostHashTag postHashTag : list){
                hashTagName.add(postHashTag.getHashTagName());
            }
            postParams3.setHashTagNames(hashTagName);

            result.add(postParams3);
        }
        return result;
    }

}
