/**
 * 
 */
package com.alfatecsistemas.sina.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Alfatec Sistemas
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.alfatecsistemas.sina.web.controller")
public class RestApplicationConfig{
	
}
