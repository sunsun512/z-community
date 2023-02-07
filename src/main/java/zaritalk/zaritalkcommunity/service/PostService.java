package zaritalk.zaritalkcommunity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zaritalk.zaritalkcommunity.domain.PostEntity;
import zaritalk.zaritalkcommunity.dto.PostListResponse;
import zaritalk.zaritalkcommunity.dto.PostResponse;
import zaritalk.zaritalkcommunity.dto.PostUpdateRequest;
import zaritalk.zaritalkcommunity.exception.PostNotFoundException;
import zaritalk.zaritalkcommunity.exception.WrongMemberException;
import zaritalk.zaritalkcommunity.repository.MemberRepository;
import zaritalk.zaritalkcommunity.repository.PostRepository;
import zaritalk.zaritalkcommunity.repository.query.LikeQueryRepository;
import zaritalk.zaritalkcommunity.repository.query.PostQueryRepository;

import java.util.List;

@Service
@Transactional
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostQueryRepository postQueryRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private LikeQueryRepository likeQueryRepository;


    public PostEntity savePost(PostEntity post){
        return postRepository.save(post);
    }

    public void deletePost(Long id){
        postRepository.delete(
                postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("게시물이 존재하지 않습니다."))
        );
    }

    public PostResponse findPost(Long id){
        return postQueryRepository.findPost(id);
    }


    public PostEntity updatePost(String accountId, PostUpdateRequest request){
        PostEntity findPost = postRepository.findById(request.getId()).get();

        if(accountId != findPost.getMember().getAccountId()){
            throw new WrongMemberException("해당 게시물의 작성자가 아닙니다.");
        }
        findPost.editPost(request.getTitle(), request.getContents());
        return findPost;
    }



    public Page<PostListResponse> findAllPost(String accountId, Pageable pageable) {
        Page<PostListResponse> postListResponseDto = postQueryRepository.findPostList(pageable);

        if(accountId == null){
            return postListResponseDto;
        }
        List<Long> postIdList = likeQueryRepository.findLikePostId(memberRepository.findByAccountId(accountId).get());
        for(PostListResponse post : postListResponseDto){
            if(postIdList.contains(post.getPostId())){
                post.setLike(true);
            }
        }
        return postListResponseDto;
    }

}
