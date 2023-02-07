package zaritalk.zaritalkcommunity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zaritalk.zaritalkcommunity.domain.LikeEntity;
import zaritalk.zaritalkcommunity.domain.MemberEntity;
import zaritalk.zaritalkcommunity.domain.PostEntity;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    // 좋아요 유무 확인
    Optional<LikeEntity> findByMemberAndPost(MemberEntity member, PostEntity post);

}
