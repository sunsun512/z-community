package zaritalk.zaritalkcommunity.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import zaritalk.zaritalkcommunity.dto.PostListResponse;
import zaritalk.zaritalkcommunity.repository.query.PostQueryRepository;

@SpringBootTest
public class PostQueryRepositoryTest {

    @Autowired
    private PostQueryRepository postQueryRepository;

    @Test
    void findPostListTest(){
        PageRequest pageable = PageRequest.of(0, 10);
        Page<PostListResponse> result = postQueryRepository.findPostList(pageable);
        Assertions.assertNotNull(result);
    }
}
