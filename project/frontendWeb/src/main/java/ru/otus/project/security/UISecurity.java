package ru.otus.project.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;

@Configuration
@EnableOAuth2Sso
@RequiredArgsConstructor
public class UISecurity extends WebSecurityConfigurerAdapter {

    private final CustomLogoutFilter logoutFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().cors()
                .and()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**","/headers", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .addLogoutHandler(logoutFilter)
                .invalidateHttpSession(true)
                .clearAuthentication(true).logoutSuccessUrl("/").deleteCookies("JSESSIONID").permitAll().and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and().headers().referrerPolicy().policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.ORIGIN);
    }


}
