package zaritalk.zaritalkcommunity.repository.query;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import zaritalk.zaritalkcommunity.domain.*;
import zaritalk.zaritalkcommunity.dto.PostListResponse;
import zaritalk.zaritalkcommunity.dto.PostResponse;

import java.util.List;


@Repository
public class PostQueryRepository extends QuerydslRepositorySupport {

    private JPAQueryFactory queryFactory;
    private QLikeEntity like;
    private QPostEntity post;
    private QMemberEntity member;

    public PostQueryRepository(JPAQueryFactory queryFactory) {
        super(PostEntity.class);
        this.queryFactory = queryFactory;
        post = QPostEntity.postEntity;
        like = QLikeEntity.likeEntity;
        member = QMemberEntity.memberEntity;
    }

    public PostResponse findPost(Long postId){
        return queryFactory
                .select(Projections.constructor(PostResponse.class,
                        post.id.as("id"),
                        post.title,
                        post.contents,
                        member.id.as("memberId"),
                        member.nickname,
                        member.accountType))
                .from(post)
                .leftJoin(post.member, member)
                .where(post.id.eq(postId))
                .fetchOne();
    }


    public Page<PostListResponse> findPostList(Pageable pageable){
       List<PostListResponse> queryResult = queryFactory
                .select(Projections.constructor(PostListResponse.class,
                        post.id.as("postId"),
                        post.title.as("title"),
                        ExpressionUtils.as(
                                JPAExpressions.select(like.count())
                                        .from(like), "cntLike"
                        ),
                        member.id.as("memberId"),
                        member.nickname.as("nickname"),
                        member.accountType.as("accountType"),
                        post.isLike.as("isLike")))
                .from(post)
                .leftJoin(post.member, member)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(post.updatedAt.desc())
                .fetch();

       Long count = queryFactory.select(post.count())
               .from(post)
               .fetchOne();

       return new PageImpl<>(queryResult, pageable, count);
    }

}
