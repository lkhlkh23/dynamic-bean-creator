package dynamic.bean.config;

import org.springframework.beans.factory.InitializingBean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@ToString
@Slf4j
@AllArgsConstructor
public class ContainerBean {

	private String name;
	private String id;
	private String password;
	private String region;

}
