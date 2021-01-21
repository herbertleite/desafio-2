package br.com.herbert.reserva.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.herbert.reserva.security.JWTAuthenticationFilter;
import br.com.herbert.reserva.security.JWTAuthorizationFilter;
import br.com.herbert.reserva.security.JwtTokenProvider;

/*Essa classe e a configuração geral do spring security, no método configure é onde libera o cors, as
urls que serão públicas e que necessitem autenticação. E também onde está adicionado o filtro de
autenticação e autorização. Para habilitar a segurança HTTP no Spring, precisamos estender
o WebSecurityConfigurerAdapter para fornecer uma configuração padrão no método configure
*/

//A anotação Spring Configuration indica que a classe possui métodos de definição
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//A anotação fornece controle sobre onde e como a ligação entre os beans deve ser realizada
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UserDetailsService userDetailsService;

	/*A anotação basicamente quando você coloca a anotação @Bean, você está dizendo pro Spring que quer
	criar esse objeto e deixar ele disponível para outras classes utilizarem ele como dependência
	*/
	@Bean
	//metodo para criptografia de senha
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	//metodos que serão utilizados sem autenticação
	private static final String[] PUBLIC_MATCHERS = { "/auth/**" };

	private static final String[] PUBLIC_MATCHERS_POST = { "/api/usuario/adicionar_cliente" };

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	//metodo de configuração de cors, filtros e quais caminhos serão liberados sem autneticação
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();

		http.authorizeRequests().antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
				.antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();

		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtTokenProvider));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtTokenProvider, userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

}