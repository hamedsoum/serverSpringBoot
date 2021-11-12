package io.getarrays.server.servicesImpl;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.getarrays.server.enumeration.Status;
import io.getarrays.server.model.Server;
import io.getarrays.server.repository.ServerRepository;
import io.getarrays.server.services.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Soumahoro Hamed
 *
 */

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServicesImpl implements ServerService {
	
	private final ServerRepository serverRepository;

	@Override
	public Server create(Server server) {
		log.info("Creation d'un nouveau server : {}", server.getName());
		server.setImageUrl(setServerImageUrl());
		return serverRepository.save(server);
	}

	@Override
	public Collection<Server> list(int limit) {
		log.info("Fetching all servers");
		return serverRepository.findAll(PageRequest.of(0, limit)).toList();
	}

	@Override
	public Server get(Long id) {
		log.info("Fetching server by id");
		return serverRepository.findById(id).get();
	}

	@Override
	public Server update(Server server) {
		log.info("Updating server: {}", server.getName());
		return serverRepository.save(server);
	}

	@Override
	public Boolean delete(Long id) {
		log.info("Deleting server by ID : {}", id);
		serverRepository.deleteById(id);
		return true;
	}

	@Override
	public Server ping(String ipAdress) throws IOException {
		log.info("Pinging server IP : {}", ipAdress);
		Server server = serverRepository.findByIpAddress(ipAdress);
		InetAddress address = InetAddress.getByName(ipAdress);
		server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
		serverRepository.save(server);
		
		return server;
	}
	
	private String setServerImageUrl() {
		String[] imageNames = {"server1.png","server2.png","server3.png" };
		return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/" + imageNames[new Random().nextInt(3)]).toUriString();
	}
	
}
