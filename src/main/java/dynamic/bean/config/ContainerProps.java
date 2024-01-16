package dynamic.bean.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@ConfigurationProperties
public class ContainerProps {

	private List<Container> containers;

	@Getter
	@Setter
	public static class Container {

		private String name;
		private String id;
		private String password;
		private List<String> regions;

	}
}
