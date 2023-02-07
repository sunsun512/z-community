package zaritalk.zaritalkcommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zaritalk.zaritalkcommunity.domain.MemberEntity;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByAccountId(String accountId);
}
