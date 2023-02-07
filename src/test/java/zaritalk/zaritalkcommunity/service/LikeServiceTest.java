package zaritalk.zaritalkcommunity.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LikeServiceTest {

    @Autowired
    private LikeService likeService;

    @Test
    void addLikeTest() {
        String account = "account01";
        Long postId = 3L;

        boolean result = likeService.addLike(account, postId);

        assertTrue(result);
    }
}