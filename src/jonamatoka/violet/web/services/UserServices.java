package jonamatoka.violet.web.services;

import jonamatoka.violet.Lib;
import jonamatoka.violet.data.model.User;
import jonamatoka.violet.data.repo.UserRepository;
import net.openhft.hashing.LongHashFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(Lib.Mappings.API_V1_USER)
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public UserServices(UserRepository userRepository) {

        this.userRepository = userRepository;

    }

    @PostMapping
    public ResponseEntity<?> register(@RequestParam("username") String username,
                                            @RequestParam("email") String email,
                                            @RequestParam("pass") String pass) {

        User user = new User().setUsername(username).setEmail(email);
        userRepository.save(user.setHash(LongHashFunction.xx().hashChars(pass)).setPriviliges(6));

        return new ResponseEntity<>(true,  HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<?> login(@AuthenticationPrincipal String username) {

        User user = userRepository.findOne(username);

        return new ResponseEntity<>(user, HttpStatus.OK);

    }

}
