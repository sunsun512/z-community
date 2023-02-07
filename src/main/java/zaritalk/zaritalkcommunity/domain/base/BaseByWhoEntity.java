package zaritalk.zaritalkcommunity.domain.base;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import zaritalk.zaritalkcommunity.domain.MemberEntity;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public abstract class BaseByWhoEntity {
    @CreatedBy
    private Long memberId;

}
