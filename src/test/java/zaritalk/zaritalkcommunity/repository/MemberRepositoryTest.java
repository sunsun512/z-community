package zaritalk.zaritalkcommunity.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zaritalk.zaritalkcommunity.domain.MemberEntity;

import java.util.Optional;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void findByAccountIdTest(){
        String accountId = "account01";
        Optional<MemberEntity> member = memberRepository.findByAccountId(accountId);
        Assertions.assertTrue( member.isPresent());
    }
}
