package zaritalk.zaritalkcommunity.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zaritalk.zaritalkcommunity.domain.LikeEntity;
import zaritalk.zaritalkcommunity.domain.MemberEntity;
import zaritalk.zaritalkcommunity.domain.PostEntity;
import zaritalk.zaritalkcommunity.repository.query.LikeQueryRepository;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class LikeRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private LikeQueryRepository likeQueryRepository;

    @Test
    void findByMemberAndPostTest()throws Exception{
        Optional<MemberEntity> member = memberRepository.findById(1L);
        Optional<PostEntity> post = postRepository.findById(2L);
        Optional<LikeEntity> like = likeRepository.findByMemberAndPost(member.get(), post.get());
        Assertions.assertNotNull(like);
        Assertions.assertTrue(like.isPresent());
    }

    @Test
    void findLikePostIdTest(){
        Optional<MemberEntity> member = memberRepository.findById(1L);
        System.out.println(member.get().getId());
        List<Long> result = likeQueryRepository.findLikePostId(member.get());
       Assertions.assertNotNull(result);
       Assertions.assertFalse(result.isEmpty());
    }
}
