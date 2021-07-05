package summer2021.mathelementaryschool.datebase.model;


import lombok.*;

import javax.persistence.*;

@Table(name = "mathProblem")
@Entity
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class MathProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_mathProblem", updatable = false)
    public Integer  id_mathProblem;

    @ManyToOne(targetEntity = FieldOfMath.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_fieldOfMath", nullable = false, referencedColumnName = "id_fieldOfMath", updatable = false)
    public Integer  id_fieldOfMath;

    @Setter
    @Column(name = "problemImageName", nullable = false)
    public String   problemImageName;

    @Setter
    @Column(name = "answer", nullable = false, updatable = false)
    public String   answer;
}
