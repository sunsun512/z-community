package zaritalk.zaritalkcommunity.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;
import zaritalk.zaritalkcommunity.domain.base.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "posts")
public class PostEntity extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String contents;

    @ManyToOne(targetEntity = MemberEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private MemberEntity member;

    @Column(name = "is_like")
    @ColumnDefault(value = "false")
    private boolean isLike;

    @OneToMany(mappedBy = "post")
    @JsonIgnore
    @NotAudited
    @Builder.Default
    private List<LikeEntity> likes = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    @JsonIgnore
    @NotAudited
    @Builder.Default
    private List<CommentEntity> comments = new ArrayList<>();

    public PostEntity(Long id, String title, String contents, MemberEntity member){
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.member = member;
    }

    //연관관계
    public void setMember(MemberEntity member){
        this.member = member;
        member.getPosts().add(this);
    }

    //게시글 생성
    public static PostEntity createPostEntity(String title, String contents, MemberEntity member) {
        return PostEntity.builder()
                .title(title)
                .contents(contents)
                .member(member)
                .build();
    }
    public void editPost(String title, String contents){
        this.title = title;
        this.contents = contents;
    }

    // 좋아요 개수
    public int getTotalLikesCount(){
        return likes.size();
    }

}
