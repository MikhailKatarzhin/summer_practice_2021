package summer2021.mathelementaryschool.datebase.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "field_of_math")
@Entity
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class FieldOfMath {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long                id;

    @Column(name = "name_field_of_math", nullable = false, updatable = false, unique = true)
    private String              name_field_of_math;

    @OneToMany(mappedBy = "field_of_math", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MathProblem>   mathProblems  = new ArrayList<>();

    @OneToMany(mappedBy = "field_of_math", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Game>          games  = new ArrayList<>();

    public void addMathProblem(MathProblem mathProblem){
        this.mathProblems.add(mathProblem);
        mathProblem.setField_of_math(this);
    }

    public void removeMathProblem(MathProblem mathProblem){
        this.mathProblems.remove(mathProblem);
        mathProblem.setField_of_math(null);
    }

    public void addGame(Game game){
        this.games.add(game);
        game.setField_of_math(this);
    }

    public void removeGame(Game game){
        this.games.remove(game);
        game.setField_of_math(null);
    }

    public FieldOfMath(@NonNull String name_field_of_math){
        this.name_field_of_math = name_field_of_math;
    }
}
