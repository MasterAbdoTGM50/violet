package jonamatoka.violet.web;

import jonamatoka.violet.account.User;

import jonamatoka.violet.data.repo.UserRepository;
import net.openhft.hashing.LongHashFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class VioletAuthenticator implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        long hash = LongHashFunction.xx().hashChars(authentication.getCredentials().toString());

        User user = userRepository.findOneByUsernameAndHash(username, hash);

        if (null == user) { throw new BadCredentialsException("Invalid Username or Password Douche!");}

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(String.valueOf(user.getPriviliges())));

        return new UsernamePasswordAuthenticationToken(user, hash, grantedAuthorities);

    }

    @Override
    public boolean supports(Class<?> authentication) { return authentication.equals(UsernamePasswordAuthenticationToken.class); }

}
