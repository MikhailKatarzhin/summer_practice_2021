package summer2021.mathelementaryschool.datebase.model;

import lombok.*;

import javax.persistence.*;

@Table(name = "resolved_problem")
@Entity
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class ResolvedProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Long id;

    @Setter
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false,referencedColumnName = "id")
    private User user;

    @Setter
    @ManyToOne(targetEntity = MathProblem.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_math_problem", nullable = false, referencedColumnName = "id")
    private MathProblem mathProblem;

}
