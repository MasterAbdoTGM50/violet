package jonamatoka.violet.data.repo;

import jonamatoka.violet.data.model.Transaction;
import org.springframework.data.repository.CrudRepository;


public interface TransactionRepository extends CrudRepository<Transaction, Long> { }
