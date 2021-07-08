package summer2021.mathelementaryschool.datebase.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import summer2021.mathelementaryschool.datebase.model.ResolvedProblem;

@Repository
public interface ResolvedProblemRepository extends CrudRepository<ResolvedProblem, Long> {
    @Query(value = "SELECT COUNT(*) FROM resolved_problem WHERE id_user = :id ;", nativeQuery = true)
    Integer countResolvedProblemById(@Param("id") @NonNull Long id);
}
