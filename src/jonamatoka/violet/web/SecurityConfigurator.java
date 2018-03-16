package jonamatoka.violet.web;

import jonamatoka.violet.Lib;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurator extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
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
                                                            Lib.Privliges.USER.toString())

            .antMatchers("/aptst").hasAuthority(Lib.Privliges.OWNER.toString())

                .antMatchers("/store").permitAll()

                .anyRequest().authenticated()
            .and().formLogin()
            .loginPage("/login").permitAll()
            .and().logout().permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { auth.authenticationProvider(new VioletAuthenticator()); }

}