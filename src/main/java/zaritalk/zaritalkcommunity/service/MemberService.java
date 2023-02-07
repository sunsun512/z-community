package zaritalk.zaritalkcommunity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zaritalk.zaritalkcommunity.domain.MemberEntity;
import zaritalk.zaritalkcommunity.exception.MemberNotFoundException;
import zaritalk.zaritalkcommunity.repository.MemberRepository;

import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public MemberEntity findOne(Long id){
        Optional<MemberEntity> member = memberRepository.findById(id);
        if(!member.isPresent()){
            throw new MemberNotFoundException("존재하지 않는 회원입니다.");
        }
        return member.get();
    }


}
