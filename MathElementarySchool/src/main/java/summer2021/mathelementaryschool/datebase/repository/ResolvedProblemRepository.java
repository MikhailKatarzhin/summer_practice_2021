package summer2021.mathelementaryschool.datebase.repository;

import org.springframework.data.repository.CrudRepository;
import summer2021.mathelementaryschool.datebase.model.ResolvedProblem;

public interface ResolvedProblemRepository extends CrudRepository<ResolvedProblem, Long> {
}
