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

                .antMatchers(Lib.Mappings.REGISTER).permitAll()

                .antMatchers(Lib.Mappings.BRAND_SERVICES).permitAll()
                .antMatchers(Lib.Mappings.ADD_BRAND_SYSTEM).hasAuthority(       Lib.Privileges.ADMIN.toString())

                .antMatchers(Lib.Mappings.CATEGORY_SERVICES).permitAll()
                .antMatchers(Lib.Mappings.ADD_CATEGORY_SYSTEM).hasAuthority(    Lib.Privileges.ADMIN.toString())

                .antMatchers(Lib.Mappings.PRODUCT_SERVICES).permitAll()
                .antMatchers(Lib.Mappings.ADD_PRODUCT_SYSTEM).hasAuthority(     Lib.Privileges.ADMIN.toString())

                .antMatchers(Lib.Mappings.STORE_SERVICES).permitAll()
                .antMatchers(Lib.Mappings.GET_STORE_SYSTEM).permitAll()
                .antMatchers(Lib.Mappings.ADD_STORE_SYSTEM).hasAnyAuthority(    Lib.Privileges.ADMIN.toString(),
                                                                                Lib.Privileges.OWNER.toString())

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