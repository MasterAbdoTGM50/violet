package jonamatoka.violet.data.repo.action;

import jonamatoka.violet.util.action.StoreRemProductAction;

import javax.transaction.Transactional;

@Transactional
public interface StoreRemProductActionRepository extends StoreActionBaseRepository<StoreRemProductAction> { }
