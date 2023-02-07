package zaritalk.zaritalkcommunity.dto;

import lombok.*;
import zaritalk.zaritalkcommunity.domain.type.AccountType;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostListResponse {
    private Long postId;

    private String title;

    private Long cntLike;

    private Long memberId;

    private String nickname;

    private AccountType accountType;

    private boolean isLike;

}
