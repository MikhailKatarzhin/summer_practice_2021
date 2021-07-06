package summer2021.mathelementaryschool.datebase.repository;

import org.springframework.data.repository.CrudRepository;
import summer2021.mathelementaryschool.datebase.model.MathProblem;

public interface MathProblemRepository extends CrudRepository<MathProblem, Long> {
}
