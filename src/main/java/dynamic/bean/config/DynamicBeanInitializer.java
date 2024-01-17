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
		for (final ContainerProps.Container container : props.getContainers()) {
			for (final String region : container.getRegions()) {
				final ContainerBean containerBean = new ContainerBean(container.getName(), container.getId(), container.getPassword(), region);
				((ConfigurableApplicationContext) context).getBeanFactory().registerSingleton(container.getName() + region, containerBean);
			}
		}

		return bean;
	}
}
