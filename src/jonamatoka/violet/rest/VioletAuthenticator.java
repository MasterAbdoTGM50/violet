package jonamatoka.violet.rest;

import jonamatoka.violet.account.User;
import jonamatoka.violet.util.NitriteHelper;
import net.openhft.hashing.LongHashFunction;
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

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        long hash = LongHashFunction.xx().hashChars(authentication.getCredentials().toString());

        User user = NitriteHelper.all(User.class).stream()
                .filter(u -> u.getUsername().equals(username) && u.getHash() == hash)
                .findFirst().orElse(null);

        if(null == user) { throw new BadCredentialsException("Invalid Username or Password Douche!");}

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(String.valueOf(user.getPriviliges())));

        return new UsernamePasswordAuthenticationToken(user, hash, grantedAuthorities);

    }

    @Override
    public boolean supports(Class<?> authentication) { return authentication.equals(UsernamePasswordAuthenticationToken.class); }

}
