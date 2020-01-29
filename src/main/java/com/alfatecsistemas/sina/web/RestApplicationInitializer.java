/**
 * 
 */
package com.alfatecsistemas.sina.web;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.alfatecsistemas.sina.config.InfrastructureConfig;
import com.alfatecsistemas.sina.web.config.RestApplicationConfig;

/**
 * @author Alfatec Sistemas
 *
 */
public class RestApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { InfrastructureConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { RestApplicationConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}
