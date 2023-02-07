package zaritalk.zaritalkcommunity.dto;


import lombok.Data;
import zaritalk.zaritalkcommunity.domain.type.AccountType;

@Data
public class PostResponse {
    private Long id;

    private String title;

    private String contents;

    private Long memberId;

    private String nickname;

    private AccountType accountType;


    public PostResponse(Long id, String title, String contents, Long memberId,
                        String nickname, AccountType accountType){
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.memberId = memberId;
        this.nickname = nickname;
        this.accountType =accountType;
    }
}
