package com.dev.gslapigatewayzuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private JwtTokenStore tokenStore;

	private static final String[] PUBLIC = { "/gsl-oauth/oauth/token" };

	private static final String[] COLABORADOR = { "/gsl-cliente/**"};

	private static final String[] CLIENTE = { "/gsl-cliente/**", "/gsl-entrega/**" };

	private static final String[] ADMIN = { "/gsl-user/**" };
	//private static final String[] ADMIN = { "/gsl-cliente/**", "/gsl-entrega/**", "/gsl-user/**" };
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers(PUBLIC).permitAll() //antMatchers: pra definir autorizações
		.antMatchers(HttpMethod.GET, CLIENTE).hasAnyRole("CLIENTE", "ADMIN", "COLABORADOR")
		.antMatchers(HttpMethod.POST, CLIENTE).hasRole("CLIENTE")
		.antMatchers(ADMIN).hasRole("ADMIN")
		.anyRequest().authenticated(); //qquer rota não especificada anteriormente, exige que o usuário esteja autenticado
	}
	
}
