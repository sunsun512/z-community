package zaritalk.zaritalkcommunity.domain;



import lombok.*;


import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Builder
@Table(name = "likes")
public class LikeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(targetEntity = MemberEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @ManyToOne(targetEntity = PostEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity post;

    public LikeEntity(MemberEntity member, PostEntity post) {
        this.post = post;
        this.member = member;
    }


}
