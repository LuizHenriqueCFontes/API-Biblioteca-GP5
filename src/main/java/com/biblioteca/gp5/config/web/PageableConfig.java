package com.biblioteca.gp5.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Configuração global do Spring MVC.
 * Responsável por ajustar comportamentos da camada web,
 * como paginação e outros resolvers de requisição.
 */
@Configuration
public class PageableConfig implements WebMvcConfigurer {
	
	/**
     * Customiza o comportamento padrão do Pageable.
     * Define um limite máximo de itens por página para evitar
     * requisições com cargas excessivas (ex: size muito alto).
     */
	@Bean
	PageableHandlerMethodArgumentResolverCustomizer customizer() {
		return resolver -> resolver.setMaxPageSize(20);
	}
}
