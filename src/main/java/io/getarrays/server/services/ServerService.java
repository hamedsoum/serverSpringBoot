package io.getarrays.server.services;

import java.util.Collection;

import io.getarrays.server.model.Server;

public interface ServerService {
	Server create(Server server);
	Server ping(String ipAdress);
	Collection<Server> list (int limit);
	Server get(Long id);
	Server update(Server server);
	Boolean delete (Long id);
	
}
