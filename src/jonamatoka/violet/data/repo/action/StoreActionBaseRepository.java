package jonamatoka.violet.data.repo.action;

import jonamatoka.violet.util.action.StoreAction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface StoreActionBaseRepository<T extends StoreAction> extends CrudRepository<T, Long> { }
