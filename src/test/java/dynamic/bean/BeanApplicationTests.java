package dynamic.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import dynamic.bean.config.ContainerBean;

@SpringBootTest
class BeanApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Autowired
	@Qualifier("consumer-1east")
	private ContainerBean bean;

	@Test
	void test_create_dynamic_bean() {
		// given

		// when
		final ContainerBean containerBean = (ContainerBean) context.getBean("consumer-1east");

		// then
		assertEquals("consumer-1", containerBean.getName());
	}

}
