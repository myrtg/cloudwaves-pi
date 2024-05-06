package tn.pfeconnect.pfeconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class PfeConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PfeConnectApplication.class, args);
	}

}
