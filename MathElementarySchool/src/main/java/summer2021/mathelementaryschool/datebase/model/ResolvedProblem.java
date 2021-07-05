package summer2021.mathelementaryschool.datebase.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "resolvedProblem")
@Entity
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class ResolvedProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_resolvedProblem", updatable = false)
    public Integer  id_resolvedProblem;

    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", nullable = false, updatable = false, referencedColumnName = "id_user")
    public Integer  id_user;

    @OneToOne(targetEntity = MathProblem.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_mathProblem", nullable = false, updatable = false, referencedColumnName = "id_mathProblem")
    public Integer  id_mathProblem;
}
