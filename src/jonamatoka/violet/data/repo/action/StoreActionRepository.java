package jonamatoka.violet.data.repo.action;

import jonamatoka.violet.util.action.StoreAction;

import javax.transaction.Transactional;

@Transactional
public interface StoreActionRepository extends StoreActionBaseRepository<StoreAction> { }
