package jonamatoka.violet.web;

import jonamatoka.violet.Lib;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

                .antMatchers(HttpMethod.GET).permitAll()
                .antMatchers("/h2/*", "/h2").permitAll()
                .antMatchers("/css/*").permitAll()

                .antMatchers(Lib.Mappings.API_V1_USER).permitAll()

                .antMatchers(HttpMethod.POST, Lib.Mappings.API_V1_USER).hasAuthority(Lib.Privileges.ADMIN.toString())
                .antMatchers(HttpMethod.POST, Lib.Mappings.API_V1_BRAND).hasAuthority(Lib.Privileges.ADMIN.toString())
                .antMatchers(HttpMethod.POST, Lib.Mappings.API_V1_CATEGORY).hasAuthority(Lib.Privileges.ADMIN.toString())
                .antMatchers(HttpMethod.POST, Lib.Mappings.API_V1_PRODUCT).hasAuthority(Lib.Privileges.ADMIN.toString())
                .antMatchers(HttpMethod.POST, Lib.Mappings.API_V1_STORE).hasAnyAuthority(
                        Lib.Privileges.ADMIN.toString(),
                        Lib.Privileges.OWNER.toString()
                )

                .anyRequest().authenticated()

        .and().formLogin().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().httpBasic();

        /* TODO//Temsah: Find valid alternative for production */
        http.csrf().disable();
        http.headers().frameOptions().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { auth.authenticationProvider(violetAuthenticator); }

}