package tn.pfeconnect.pfeconnect;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import tn.pfeconnect.pfeconnect.role.Role;
import tn.pfeconnect.pfeconnect.role.RoleRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PfeConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PfeConnectApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(RoleRepository roleRepository) {
		return args -> {
			if (roleRepository.findByName("USER").isEmpty()) {
				roleRepository.save(Role.builder().name("USER").build());
			}
		};
	}
}