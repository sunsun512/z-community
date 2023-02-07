package zaritalk.zaritalkcommunity.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
public class CommentEntity {
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String detail;

    @ManyToOne(targetEntity = PostEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @ManyToOne(targetEntity = MemberEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;


    public CommentEntity(MemberEntity member, PostEntity post, String detail) {
        this.detail = detail;
        this.post = post;
        this.member = member;
    }
}
