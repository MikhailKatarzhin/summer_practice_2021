package summer2021.mathelementaryschool.datebase.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import summer2021.mathelementaryschool.datebase.model.MathProblem;

@Repository
public interface MathProblemRepository extends CrudRepository<MathProblem, Long> {
}
