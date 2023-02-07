package zaritalk.zaritalkcommunity.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import zaritalk.zaritalkcommunity.domain.type.AccountType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional
@Rollback(value = false)
class AuditingTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void basicJpaAuditingTest() throws Exception {
        MemberEntity member = MemberEntity.createMember("newOne", AccountType.REALTOR, "account01", "pswd1");

        String title = "hello";
        String contents = "hi";

        PostEntity post = PostEntity.createPostEntity(title, contents, member);

        em.persist(member);
        em.persist(post);
        em.flush();
    }
}
