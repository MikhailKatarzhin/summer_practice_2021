package summer2021.mathelementaryschool.datebase.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import summer2021.mathelementaryschool.datebase.model.FieldOfMath;

@Repository
public interface FieldOfMathRepository extends CrudRepository<FieldOfMath, Long> {
}
