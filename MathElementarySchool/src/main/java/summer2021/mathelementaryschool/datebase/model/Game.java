package summer2021.mathelementaryschool.datebase.model;

import lombok.*;
import net.bytebuddy.utility.RandomString;

import javax.persistence.*;

@Table(name = "game")
@Entity
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false, updatable = false)
    private Long        id;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = User.class, orphanRemoval = true)
    @JoinColumn(name = "id_user", nullable = false, updatable = false, referencedColumnName = "id")
    private User        user;

    @Setter
    @ManyToOne(targetEntity = FieldOfMath.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_field_of_math", nullable = false, referencedColumnName = "id")
    private FieldOfMath field_of_math;

    @Column(name = "scores", nullable = false)
    private Long        scores;

    @Column(name = "shuffle_key", nullable = false, updatable = false)
    private String      shuffle_key;

    public Game(@NonNull User user, @NonNull FieldOfMath fieldOfMath){
        this.field_of_math  = fieldOfMath;
        this.user           = user;
        this.scores         = 0L;
        this.shuffle_key    = RandomString.make(12);
    }
}
