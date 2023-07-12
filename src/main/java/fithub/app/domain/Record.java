package fithub.app.domain;


import fithub.app.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Record extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(30)")
    private String Contents;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Long likes;

    private String imageUrl;

    @Column(columnDefinition = "BIGINT DEFAULT 0")
    private Long dailyLikes;

    @Column(columnDefinition = "BIGINT DEFAULT 0")
    private Long weeklyLikes;

    @Column(columnDefinition = "BIGINT DEFAULT 0")
    private Long reported;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    private ExerciseCategory exerciseCategory;
}