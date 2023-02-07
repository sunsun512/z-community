package zaritalk.zaritalkcommunity.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import zaritalk.zaritalkcommunity.config.LoginMember;
import zaritalk.zaritalkcommunity.domain.MemberEntity;
import zaritalk.zaritalkcommunity.domain.PostEntity;
import zaritalk.zaritalkcommunity.domain.util.MemberAdapter;
import zaritalk.zaritalkcommunity.dto.PostCreateRequest;
import zaritalk.zaritalkcommunity.dto.PostListResponse;
import zaritalk.zaritalkcommunity.dto.PostResponse;
import zaritalk.zaritalkcommunity.dto.PostUpdateRequest;
import zaritalk.zaritalkcommunity.exception.BadErrorRequestException;
import zaritalk.zaritalkcommunity.repository.MemberRepository;
import zaritalk.zaritalkcommunity.repository.PostRepository;
import zaritalk.zaritalkcommunity.service.MemberService;
import zaritalk.zaritalkcommunity.service.PostService;

import javax.validation.Valid;
import java.util.Optional;


@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    public void isAccountIdNull(String accountId){
        if(accountId == null){
            throw new IllegalStateException("회원이 아닙니다.");
        }
    }

    public String getAccountId(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }

    //게시물 생성
    @PostMapping("/post")
    public ResponseEntity<Long> createPost(Authentication authentication, @Valid @RequestBody PostCreateRequest request, Errors errors){
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String accountId = getAccountId(authentication);
        isAccountIdNull(accountId);

        Optional<MemberEntity> member = memberRepository.findByAccountId(accountId);
        PostEntity savedPost = postService.savePost(PostEntity.createPostEntity(request.getTitle(), request.getContents(), member.get()));

        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("post created - member "+ accountId + "creates " + "post no: " + savedPost.getId() + "at " + savedPost.getCreatedAt());

        return new ResponseEntity<>(savedPost.getId(), HttpStatus.CREATED);
    }

    //게시물 삭제
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<String> deletePost(Authentication authentication, @PathVariable Long postId){
        String accountId = getAccountId(authentication);
        isAccountIdNull(accountId);
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //게시물 수정
    @PutMapping("/post/{postId}")
    public ResponseEntity updatePost(Authentication authentication, @PathVariable Long postId, Errors errors,
                                     @Valid @RequestBody PostUpdateRequest request){
        if (errors.hasErrors()) {
            return new ResponseEntity<>(-1L, HttpStatus.NOT_MODIFIED);
        }

        String accountId = getAccountId(authentication);
        isAccountIdNull(accountId);
        PostEntity updatedPost = postService.updatePost(accountId, request);

        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("post updated - member "+ accountId + "updates " + "post no: " + updatedPost.getId() + "at " + updatedPost.getUpdatedAt());

        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    //게시물 목록
    @GetMapping("/posts")
    public Page<PostListResponse> selectAllPost(Authentication authentication,
                                                @PageableDefault(size = 9, value = 9) Pageable pageable){
        String accountId = getAccountId(authentication);
        return postService.findAllPost(accountId, pageable);

    }

    //1개 게시물 조회
    @GetMapping("/post/{postId}")
    public PostResponse getPost(@PathVariable Long postId){
        if(postId == null || postId < 0){
            throw new BadErrorRequestException("잘못된 요청입니다.");
        }
        PostResponse findPost = postService.findPost(postId);
        return findPost;
    }

    @GetMapping("/auth")
    public void test(@LoginMember MemberAdapter member){
        System.out.println("*************************");
        System.out.println(member);

    }


}
