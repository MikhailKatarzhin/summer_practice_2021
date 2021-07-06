package summer2021.mathelementaryschool.datebase.repository;

import org.springframework.data.repository.CrudRepository;
import summer2021.mathelementaryschool.datebase.model.Game;

public interface GameRepository extends CrudRepository<Game, Long> {
}
