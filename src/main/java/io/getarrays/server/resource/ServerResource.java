package io.getarrays.server.resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.getarrays.server.enumeration.Status;
import io.getarrays.server.model.Response;
import io.getarrays.server.model.Server;
import io.getarrays.server.services.ServerService;
import io.getarrays.server.servicesImpl.ServerServicesImpl;
import lombok.RequiredArgsConstructor;


/**
 * 
 * @author Soumahoro Hamed
 *
 */
@RestController
@RequestMapping("/server")
public class ServerResource {
	@Autowired
	 ServerService serverService ;

	@GetMapping("/list")
	public ResponseEntity<Response> getServers(){
		return ResponseEntity.ok(
				Response.builder()
						.timeStamp(LocalDateTime.now())
						.data(Map.of("servers", serverService.list(30)))
						.message("Servers retrieved")
						.status(HttpStatus.OK)
						.statusCode(HttpStatus.OK.value())
						.build()
				);
	}
	

	@GetMapping("/ping/{ipAddress}")
	public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException{
		Server  server = serverService.ping(ipAddress);
		return ResponseEntity.ok(
				Response.builder()
						.timeStamp(LocalDateTime.now())
						.data(Map.of("servers", server))
						.message(server.getStatus() == Status.SERVER_UP ? "ping success" : "ping failed")
						.status(HttpStatus.OK)
						.statusCode(HttpStatus.OK.value())
						.build()
				);
	}

	@PostMapping("/save")
	public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
		return ResponseEntity.ok(
				Response.builder()
						.timeStamp(LocalDateTime.now())
						.data(Map.of("servers", serverService.create(server)))
						.message("server created")
						.status(HttpStatus.CREATED)
						.statusCode(HttpStatus.OK.value())
						.build()
				);
	}

	@GetMapping("/get/{id}")
		public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
			return ResponseEntity.ok(
					Response.builder()
							.timeStamp(LocalDateTime.now())
							.data(Map.of("servers", serverService.get(id)))
							.message("server retrieved")
							.status(HttpStatus.OK)
							.statusCode(HttpStatus.OK.value())
							.build()
					);
		}
		
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {
		return ResponseEntity.ok(
				Response.builder()
						.timeStamp(LocalDateTime.now())
						.data(Map.of("deleted", serverService.delete(id)))
						.message("server retrieved")
						.status(HttpStatus.OK)
						.statusCode(HttpStatus.OK.value())
						.build()
				);
	}

	@GetMapping(path = "/image/{fileName}", produces = org.springframework.http.MediaType.IMAGE_PNG_VALUE)
	public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
		return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Downloads/serverImages/" + fileName));
	}
}


