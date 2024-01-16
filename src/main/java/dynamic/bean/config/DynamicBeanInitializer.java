package dynamic.bean.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DynamicBeanInitializer implements BeanPostProcessor {

	private final ApplicationContext context;

	@Override
	public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
		if(!(bean instanceof ContainerProps)) {
			return bean;
		}

		final ContainerProps props = ((ContainerProps) bean);

		props.getContainers()
		     .forEach(c -> {
				 for (final String region : c.getRegions()) {
					 final ContainerBean containerBean = new ContainerBean(c.getName(), c.getId(), c.getPassword(), region);
					 if(InitializingBean.class.isAssignableFrom(containerBean.getClass())) {
						 try {
							 ((InitializingBean)containerBean).afterPropertiesSet();
					   	 } catch (Exception e) {
							 e.printStackTrace();
					     }
				   	 }
					 ((ConfigurableApplicationContext) context).getBeanFactory().registerSingleton(c.getName() + region, containerBean);
			   }
		   });

		return bean;
	}
}
