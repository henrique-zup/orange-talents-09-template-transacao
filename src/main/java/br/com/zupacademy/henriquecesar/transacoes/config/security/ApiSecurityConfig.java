package br.com.zupacademy.henriquecesar.transacoes.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
@EnableWebSecurity
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(requests -> requests
			.antMatchers(HttpMethod.GET, "/transacoes/*").hasAuthority("SCOPE_escopo-api-transacoes")
			.anyRequest().authenticated())
		.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
	}

}
