package jonamatoka.violet.data.repo;

import jonamatoka.violet.account.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

    User findOneByUsernameAndHash(String username, long hash);

}
