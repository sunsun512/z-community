package zaritalk.zaritalkcommunity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import zaritalk.zaritalkcommunity.domain.LikeEntity;
import zaritalk.zaritalkcommunity.domain.MemberEntity;
import zaritalk.zaritalkcommunity.domain.PostEntity;
import zaritalk.zaritalkcommunity.domain.type.AccountType;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;
        public void dbInit1(){
            MemberEntity member1 = MemberEntity.createMember("firstMem", AccountType.REALTOR, "account01", "password1");
            em.persist(member1);
            MemberEntity member2 = MemberEntity.createMember("secondMem", AccountType.LESSEE, "account02", "password1");
            em.persist(member2);
            MemberEntity member3 = MemberEntity.createMember("thirdMem", AccountType.LESSOR, "account03", "password1");
            em.persist(member3);

            PostEntity post1 = PostEntity.createPostEntity("title1", "contents1", member1);
            em.persist(post1);
            PostEntity post2 = PostEntity.createPostEntity("title2", "contents2", member2);
            em.persist(post2);
            PostEntity post3 = PostEntity.createPostEntity("title3", "contents3", member3);
            em.persist(post3);

            LikeEntity like1 = LikeEntity.builder().member(member1).post(post2).build();
            em.persist((like1));
            LikeEntity like2 = LikeEntity.builder().member(member2).post(post3).build();
            em.persist((like2));




        }

    }

}
