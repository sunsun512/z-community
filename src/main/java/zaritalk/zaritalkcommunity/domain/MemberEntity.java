package zaritalk.zaritalkcommunity.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.ColumnDefault;
import zaritalk.zaritalkcommunity.domain.type.AccountType;
import zaritalk.zaritalkcommunity.domain.type.AccountTypeConverter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
@Getter
@NoArgsConstructor
@Table(name = "member")
public class MemberEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @NonNull
    @Column(name = "account_id")
    private String accountId;

    @NonNull
    private String password;

    @NonNull
    private String nickname;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Convert(converter = AccountTypeConverter.class)
    private AccountType accountType;

    @ColumnDefault(value = "false")
    private boolean quit;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<PostEntity> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<LikeEntity> likes = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<CommentEntity> comments = new ArrayList<>();

    public MemberEntity(String nickname, AccountType accountType, String accountId, String password) {
        this.accountId = accountId;
        this.password = password;
        this.nickname = nickname;
        this.accountType = accountType;
    }


    public String getAccountTypeName(MemberEntity member) {
        return member.getAccountType().getName();
    }

    public static MemberEntity createMember(String nickname, AccountType accountType, String accountId, String password) {
        return new MemberEntity(nickname, accountType, accountId, password);
    }

    public void setQuit() {
        this.quit = true;
    };
}
