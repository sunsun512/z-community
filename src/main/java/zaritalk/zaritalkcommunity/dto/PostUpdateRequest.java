package zaritalk.zaritalkcommunity.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateRequest {

    private Long id;

    private String title;

    private String contents;

}
