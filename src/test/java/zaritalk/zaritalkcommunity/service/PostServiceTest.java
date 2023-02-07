package zaritalk.zaritalkcommunity.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import zaritalk.zaritalkcommunity.domain.MemberEntity;
import zaritalk.zaritalkcommunity.domain.PostEntity;
import zaritalk.zaritalkcommunity.dto.PostListResponse;
import zaritalk.zaritalkcommunity.dto.PostResponse;
import zaritalk.zaritalkcommunity.dto.PostUpdateRequest;
import zaritalk.zaritalkcommunity.repository.MemberRepository;
import zaritalk.zaritalkcommunity.repository.PostRepository;

import java.util.Optional;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    void savePostTest(){
        MemberEntity member = memberRepository.findById(3L).get();
        PostEntity post = PostEntity.createPostEntity("Title", "Contents00", member);

        PostEntity savedPost = postService.savePost(post);

        Assertions.assertNotNull(savedPost);
        Assertions.assertNotNull(savedPost.getId());
    }

    @Test
    void updatePostTest(){
        String accountId = "account01";
        PostUpdateRequest updateRequest = new PostUpdateRequest(1L, "update title", "update contents");

        PostEntity updatedPost = postService.updatePost(accountId, updateRequest);

        Assertions.assertEquals(updatedPost.getId(), updateRequest.getId());
        Assertions.assertTrue((updatedPost.getTitle() == updateRequest.getTitle()));
    }

    @Test
    void deletePostTest(){
        MemberEntity member = memberRepository.findById(3L).get();
        PostEntity post = PostEntity.createPostEntity("Title", "Contents00", member);

        PostEntity savedPost = postService.savePost(post);
        postService.deletePost(savedPost.getId());

        PostResponse deletePost = postService.findPost(savedPost.getId());

        Assertions.assertNull(deletePost);
    }

    @Test
    void findAllPostTest() {
        PageRequest pageable = PageRequest.of(0, 10);
        Optional<MemberEntity> member = memberRepository.findById(1L);

        Page<PostListResponse> result = postService.findAllPost(member.get().getAccountId(), pageable);

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.get());
    }




}