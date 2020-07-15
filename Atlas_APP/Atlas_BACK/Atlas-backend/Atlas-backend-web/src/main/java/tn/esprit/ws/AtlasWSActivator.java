package tn.esprit.ws;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.NameBinding;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;

import io.swagger.jaxrs.config.BeanConfig;
import tn.esprit.ws.controllers.UserController;

@ApplicationPath("atlas/api")
public class AtlasWSActivator extends Application {

	public AtlasWSActivator() {
		super();

		initSwagger();

	}

	public void initSwagger() {
		BeanConfig beanConfig = new BeanConfig();

		beanConfig.setVersion("1.0.0");
		beanConfig.setTitle("Atlas API");
		beanConfig.setBasePath("/Atlas-backend-web/atlas/api");
		beanConfig.setResourcePackage(UserController.class.getPackage().getName());
		beanConfig.setScan(true);

	}

	@NameBinding
	@Target({ ElementType.TYPE, ElementType.METHOD })
	@Retention(value = RetentionPolicy.RUNTIME)
	public @interface Secured {
	}

}
