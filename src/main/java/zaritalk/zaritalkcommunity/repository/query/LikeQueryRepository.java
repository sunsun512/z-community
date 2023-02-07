package zaritalk.zaritalkcommunity.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import zaritalk.zaritalkcommunity.domain.*;

import java.util.List;

@Repository
public class LikeQueryRepository extends QuerydslRepositorySupport {

    private JPAQueryFactory queryFactory;

    public LikeQueryRepository(JPAQueryFactory queryFactory ) {
        super(LikeEntity.class);
        this.queryFactory = queryFactory;
    }

    public List<Long> findLikePostId(MemberEntity memberEntity){
        QLikeEntity like = QLikeEntity.likeEntity;

        return queryFactory.select(like.post.id)
                .from(like)
                .where(like.member.id.eq(memberEntity.getId()))
                .fetch();
    }
}
