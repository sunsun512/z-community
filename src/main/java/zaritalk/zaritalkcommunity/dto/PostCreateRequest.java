package zaritalk.zaritalkcommunity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequest {
    private String title;

    private String contents;

}
