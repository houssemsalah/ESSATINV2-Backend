package tn.essatin.erp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tn.essatin.erp.security.jwt.AuthEntryPointJwt;
import tn.essatin.erp.security.service.CompteDetailsServiceImpl;
import tn.essatin.erp.security.jwt.AuthTokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CompteDetailsServiceImpl compteDetailsService;
    @Autowired
    private AuthEntryPointJwt authEntryPointJwt;

    @Bean
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder.userDetailsService(compteDetailsService).passwordEncoder(passwordEncoder());

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint(authEntryPointJwt).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests().antMatchers("/api/auth/**").permitAll()
            .antMatchers("/api/pay/**").permitAll()
                .antMatchers("/api/session/**").permitAll()
                .antMatchers("/api/niveaux/**").permitAll()
                .antMatchers("/api/etudiants/**").permitAll()
                .antMatchers("/api/cycle/**").permitAll()
                .antMatchers("/api/specialite/**").permitAll()
                .antMatchers("/api/parcours/**").permitAll()
                .antMatchers("/api/niveaux/**").permitAll()
                .antMatchers("/api/enregistrement/**").permitAll()
                .antMatchers("/api/personne/**").permitAll()
                .antMatchers("/api/role/**").permitAll()
                .antMatchers("/api/inscription/**").permitAll()
                .antMatchers("/api/nationalite/**").permitAll()
                .antMatchers("/api/typeidentificateur/**").permitAll()
                .antMatchers("/api/diplome/**").permitAll()
                .antMatchers("/api/diplomeetudiant/**").permitAll()
                .antMatchers("/api/contactetudiant/**").permitAll()
                .antMatchers("/api/niveausuivant/**").permitAll()
                .antMatchers("/api/enum/**").permitAll()


                .antMatchers("/api/contrat/**").permitAll()

            .anyRequest().authenticated();
        http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
