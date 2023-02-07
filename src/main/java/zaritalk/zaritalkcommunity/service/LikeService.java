package zaritalk.zaritalkcommunity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zaritalk.zaritalkcommunity.domain.LikeEntity;
import zaritalk.zaritalkcommunity.domain.MemberEntity;
import zaritalk.zaritalkcommunity.domain.PostEntity;
import zaritalk.zaritalkcommunity.repository.LikeRepository;
import zaritalk.zaritalkcommunity.repository.MemberRepository;
import zaritalk.zaritalkcommunity.repository.PostRepository;

import java.util.Optional;

@Transactional
@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MemberRepository memberRepository;

    public boolean addLike(String accountId, Long postId) {
        Optional<MemberEntity> member = memberRepository.findByAccountId(accountId);
        Optional<PostEntity> post = postRepository.findById(postId);

        if(!isAlreadyLike(member.get(), post.get())){
            likeRepository.save(new LikeEntity(member.get(), post.get()));
            return true;
        }
        return false;
    }

    private boolean isAlreadyLike(MemberEntity member, PostEntity post) {
        return likeRepository.findByMemberAndPost(member, post).isPresent();
    }
}
