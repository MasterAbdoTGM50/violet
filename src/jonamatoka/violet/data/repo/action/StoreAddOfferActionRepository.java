package jonamatoka.violet.data.repo.action;

import jonamatoka.violet.util.action.StoreAddOfferAction;

import javax.transaction.Transactional;

@Transactional
public interface StoreAddOfferActionRepository extends StoreActionBaseRepository<StoreAddOfferAction> { }
