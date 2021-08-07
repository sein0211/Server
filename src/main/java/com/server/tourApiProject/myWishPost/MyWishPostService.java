package com.server.tourApiProject.myWishPost;

import com.server.tourApiProject.post.Post;
import com.server.tourApiProject.post.PostRepository;
import com.server.tourApiProject.user.User;
import com.server.tourApiProject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MyWishPostService {
    private final UserRepository userRepository;
    private final MyWishPostRepository myWishPostRepository;
    private final PostRepository postRepository;

    public void createMyWishPost(Long userId, Long postId) {
        postRepository.findById(postId).orElseThrow(IllegalAccessError::new); //postId에 해당하는 게시물이 없으면 오류 발생
        MyWishPost myWishPost = new MyWishPost();
        myWishPost.setUserId(userId);
        myWishPost.setPostId(postId);
        myWishPost.setUser(userRepository.findById(userId).orElseThrow(IllegalAccessError::new));

        myWishPostRepository.save(myWishPost);
    }

    public List<MyWishPostParams> getMyWishPosts(Long userId) {
        List<MyWishPostParams> result= new ArrayList<>();

        List<MyWishPost> list = myWishPostRepository.findByUserId(userId);
        for (MyWishPost wp : list){
            Post post = postRepository.findById(wp.getPostId()).orElseThrow(IllegalAccessError::new);

            MyWishPostParams params = new MyWishPostParams();
            params.setPostId(post.getPostId());
            params.setThumbnail(""); //추후 첫번째 이미지로 수정
            params.setTitle(post.getPostContent()); //추후 제목으로 수정
            result.add(params);
        }
        return result;
    }
}
