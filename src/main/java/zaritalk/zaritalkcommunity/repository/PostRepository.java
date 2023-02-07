package zaritalk.zaritalkcommunity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zaritalk.zaritalkcommunity.domain.PostEntity;


@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
