package jonamatoka.violet.data.repo;

import jonamatoka.violet.data.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

    User findOneByUsernameAndHash(String username, long hash);

}
