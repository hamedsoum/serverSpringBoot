package io.getarrays.server.services;

import java.io.IOException;
import java.util.Collection;

import org.springframework.stereotype.Service;

import io.getarrays.server.model.Server;

@Service
public interface ServerService {
	Server create(Server server);
	Server ping(String ipAdress) throws IOException;
	Collection<Server> list (int limit);
	Server get(Long id);
	Server update(Server server);
	Boolean delete (Long id);
	
}
