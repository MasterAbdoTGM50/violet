package jonamatoka.violet.web;

import jonamatoka.violet.Lib;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfigurator extends WebSecurityConfigurerAdapter {

    @Autowired
    private VioletAuthenticator violetAuthenticator;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
            .antMatchers("/h2/*", "/h2").permitAll()
            .antMatchers("/css/*").permitAll()
            .antMatchers("/register").permitAll()
            .antMatchers("/apts").hasAuthority(Lib.Privliges.ADMIN.toString())
            .antMatchers("/asts").hasAuthority(Lib.Privliges.OWNER.toString())
            .antMatchers("/vsts").hasAuthority(Lib.Privliges.OWNER.toString())
            .antMatchers("/acts").hasAuthority(Lib.Privliges.ADMIN.toString())
            .antMatchers("/abts").hasAuthority(Lib.Privliges.ADMIN.toString())
            .antMatchers("/abts").hasAuthority(Lib.Privliges.ADMIN.toString())

            .antMatchers("/vst").hasAnyAuthority(
                                                            Lib.Privliges.ADMIN.toString(),
                                                            Lib.Privliges.OWNER.toString(),
                                                            Lib.Privliges.USER.toString()
            )
            .antMatchers("/aptst").hasAuthority(Lib.Privliges.OWNER.toString())
            .antMatchers("/store").permitAll()
            .anyRequest().authenticated();

        http.formLogin().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.httpBasic();

        /* TODO//Temsah: Find valid alternative for production */
        http.csrf().disable();
        http.headers().frameOptions().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { auth.authenticationProvider(violetAuthenticator); }

}