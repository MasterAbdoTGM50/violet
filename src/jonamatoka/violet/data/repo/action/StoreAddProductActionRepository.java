package jonamatoka.violet.data.repo.action;

import jonamatoka.violet.util.action.StoreAddProductAction;

import javax.transaction.Transactional;

@Transactional
public interface StoreAddProductActionRepository extends StoreActionBaseRepository<StoreAddProductAction> { }
