package unit.services.user;

import jonamatoka.violet.data.repo.UserRepository;
import jonamatoka.violet.web.services.UserServices;

import org.springframework.beans.factory.annotation.Autowired;

import unit.TestVioletService;

public class TestUserServices extends TestVioletService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServices userServices;

    public UserRepository getUserRepository() { return userRepository; }

    public UserServices getUserServices() { return userServices; }

}
