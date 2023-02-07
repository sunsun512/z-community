package zaritalk.zaritalkcommunity.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import zaritalk.zaritalkcommunity.domain.util.AccountDetailsService;
import zaritalk.zaritalkcommunity.service.MemberService;

import javax.sql.DataSource;
import java.nio.file.AccessDeniedException;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final AccountDetailsService accountDetailsService;
    private final DataSource dataSource;
    private final MemberService memberService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/post/**", "/post/**/like").hasAnyRole("USER", "ADMIN")
                .antMatchers("/posts").permitAll()
                .and().httpBasic();

        http.httpBasic().and();
        http.csrf().disable();

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
            auth.inMemoryAuthentication()
                    .withUser("account01")
                    .password("{noop}password1")
                    .roles("USER");
    }

//    private Authentication getValidationToken(Authentication Authentication) throws AccessDeniedException {
//
//        boolean isValid = true;
//        if (isValid)
//            return new PreAuthenticatedAuthenticationToken("Authentication", "ROLE_ADMIN");
//        else
//            throw new AccessDeniedException("Invalid authetication token");
//
//    }

    /*
     * Remember-me
     * */
    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }



}
