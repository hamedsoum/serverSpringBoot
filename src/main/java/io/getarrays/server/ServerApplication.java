package io.getarrays.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import io.getarrays.server.enumeration.Status;
import io.getarrays.server.model.Server;
import io.getarrays.server.repository.ServerRepository;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(ServerRepository serverRepo) {
		return  args -> {
			serverRepo.save(new Server(null, "192.168.1.14", "ubuntu linux", "16 GB", "Personnal PC","http://localhost:8080/server/image/server1.png", Status.SERVER_UP ));
			serverRepo.save(new Server(null, "192.168.1.15", "Fedora linux", "16 GB", "Dell Tower","http://localhost:8080/server/image/server2.png", Status.SERVER_DOWN ));
			serverRepo.save(new Server(null, "192.168.1.16", "Red Hat Entreprise linux", "32GB", "Web server","http://localhost:8080/server/image/server3.png", Status.SERVER_UP ));
			serverRepo.save(new Server(null, "192.168.1.17", "Blue Hat Entreprise linux", "32GB", "Web server","http://localhost:8080/server/image/server3.png", Status.SERVER_DOWN ));

		};
	}

}
