package summer2021.mathelementaryschool.datebase.model;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "math_problem")
@Entity
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class MathProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Setter
    @ManyToOne(targetEntity = FieldOfMath.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_field_of_math", nullable = false, referencedColumnName = "id")
    private FieldOfMath field_of_math;

    @Setter
    @Column(name = "problem_image_name", nullable = false)
    private String  problem_image_name;

    @Setter
    @Column(name = "description", nullable = false)
    private String  description;

    @Setter
    @Column(name = "answer", nullable = false)
    private String  answer;

    @OneToMany(mappedBy = "mathProblem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResolvedProblem> resolvedProblems = new ArrayList<>();

    public void addResolvedProblem(ResolvedProblem ResolvedProblem){
        this.resolvedProblems.add(ResolvedProblem);
        ResolvedProblem.setMathProblem(this);
    }

    public void removeResolvedProblem(ResolvedProblem ResolvedProblem){
        this.resolvedProblems.remove(ResolvedProblem);
        ResolvedProblem.setMathProblem(null);
    }

    public MathProblem(@NonNull String problem_image_name, @NonNull String answer){
        this.problem_image_name = problem_image_name;
        this.answer             = answer;
    }
}
