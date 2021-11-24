package com.dev.gslapigatewayzuul.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private JwtTokenStore tokenStore;

	private static final String[] PUBLIC = { "/gsl-oauth/oauth/token" };

	private static final String[] COLABORADOR = {"/gsl-cliente/**/clientes/add",
			"/gsl-cliente/**/clientes/atualizar", 
			"/gsl-cliente/**/clientes/buscarPorContemNome/**", 
			"/gsl-cliente/**/clientes/buscarPorId/**", 
			"/gsl-cliente/**/clientes/buscarPorNome/**", 
			"/gsl-cliente/**/clientes/listar",
			"/gsl-entrega/**/entregas/buscarPorId/**",
			"/gsl-entrega/**/entregas/buscarTodas/**",
			"/gsl-entrega/**/entregas/coletarCarga/**",
			"/gsl-entrega/**/entregas/distribuirNosCds/**",
			"/gsl-entrega/**/entregas/efetuarRoteirizacao/**",
			"/gsl-entrega/**/entregas/emitirCte/**",
			"/gsl-entrega/**/entregas/finalizarEntrega/**",
			"/gsl-entrega/**/entregas/iniciarLastMile/**",
			"/gsl-entrega/**/entregas/iniciarTransporte/**"
			};

	private static final String[] CLIENTE = { "/gsl-cliente/**/add/**", 
			"/gsl-entrega/**/entregas/solicitar",
			"/gsl-entrega/**/entregas/cancelarEntrega/**",
			"/gsl-entrega/**/entregas/efetuarPagamento/**",
			"/gsl-entrega/**/entregas/estimarCalculoFrete/**"};
	
	private static final String[] CLIENTE_E_COLABORADOR = { "/gsl-cliente/**/add/**", 
			"/gsl-cliente/**/buscarPorCnpj/**", 
			"/gsl-entrega/**/entregas/buscarCliente/**",
			"/gsl-entrega/**/entregas/buscarPorCodigoSolicitacao/**",
			"/gsl-entrega/**/entregas/consultarAndamentoEntrega/**"};

	private static final String[] ADMIN = { "/gsl-user/**", "/actuator/**", "/gsl-cliente/actuator/**", "/gsl-oauth/actuator/**", "/gsl-oauth/**", 
			"/gsl-cliente/**", "/gsl-entrega/**"};
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

	http.authorizeRequests()
    .antMatchers(
                "/",
                "/actuator/**",
                "/v2/api-docs/**",
                "/swagger*/**",
                "/swagger-resources/**",
                "/webjars/**").permitAll()
		.antMatchers(PUBLIC).permitAll() //antMatchers: pra definir autorizações
		/*
		.antMatchers(CLIENTE).hasAnyRole("CLIENTE", "ADMIN", "COLABORADOR")
		.antMatchers(HttpMethod.POST, CLIENTE).hasRole("CLIENTE")
		.antMatchers(ADMIN).hasRole("ADMIN")
		*/
		.antMatchers(CLIENTE).hasAnyRole("CLIENTE", "ADMIN")
		.antMatchers(COLABORADOR).hasAnyRole("COLABORADOR", "ADMIN")
		.antMatchers(CLIENTE_E_COLABORADOR).hasAnyRole("CLIENTE", "COLABORADOR", "ADMIN")
		.antMatchers(ADMIN).hasRole("ADMIN")
		.anyRequest().authenticated(); //qquer rota não especificada anteriormente, exige que o usuário esteja autenticado

		http.cors().configurationSource(corsConfigurationSource());
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Arrays.asList("*"));
		corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "PATCH"));
		corsConfig.setAllowCredentials(true);
		corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);
		return source;
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> bean 
			= new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

}
