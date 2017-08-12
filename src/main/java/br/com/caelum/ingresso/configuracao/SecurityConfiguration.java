package br.com.caelum.ingresso.configuracao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
@ImportResource("/WEB-INF/spring-context.xml")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		    .csrf().disable().authorizeRequests()
		        .antMatchers("/admin/**").hasRole("ADMIN")
		        .antMatchers("/compra/**").hasRole("COMPRADOR")
		        .antMatchers("/filme/**").permitAll()
		        .antMatchers("/sessao/**/lugares").permitAll()
		        .antMatchers("/magic/**").permitAll()
		        .antMatchers("/").permitAll()
		    .anyRequest()
		        .authenticated()
		    .and()
		        .formLogin()
		           .usernameParameter("email")
		           .loginPage("/login")
		           .permitAll();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/assets/**");
	}
}
