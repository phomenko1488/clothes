package iam.phomenko.clothes.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
//                .antMatchers(USER_ONLY_URLS)
//                .hasRole("USER")
//                .antMatchers(HttpMethod.POST,"/createUser")
//                .hasAuthority("CREATE_USERS")
//                .antMatchers("/entity**", "/entity/*")
//                .hasRole("OWNER")
//                .antMatchers("/transactions**", "/transactions/*")
//                .hasAuthority("TRANSACTIONS_READ")
//                .antMatchers("/requests**", "/requests/*")
//                .hasAuthority("REQUESTS_READ")
//                .antMatchers("/requests/close")
//                .hasAuthority("REQUESTS_EDIT")
//                .antMatchers("/wallets**", "/wallets/*")
//                .hasAuthority("WALLETS_READ")
//                .antMatchers("/users**", "/users/*")
//                .hasAuthority("USERS_READ")
//                .antMatchers("/users/changeUserEnabled", "/users/removePermission")
//                .hasAuthority("USERS_EDIT")
//                .antMatchers("/users/createUser")
//                .hasAuthority("USERS_CREATE")
                .antMatchers("/css/*","/js/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/forbidden")
                .and()
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/", true)
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .permitAll()
                );
    }


    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }
}