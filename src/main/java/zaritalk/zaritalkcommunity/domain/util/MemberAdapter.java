package zaritalk.zaritalkcommunity.domain.util;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import zaritalk.zaritalkcommunity.domain.MemberEntity;
import java.util.List;


@Getter
public class MemberAdapter extends User {
    private final MemberEntity member;

    public MemberAdapter(MemberEntity member) {
        super(member.getAccountId(),
                member.getPassword(),
                List.of(new SimpleGrantedAuthority(member.getAccountType().getName())));
        this.member = member;
    }
}
