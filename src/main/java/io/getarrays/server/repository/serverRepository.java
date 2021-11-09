package io.getarrays.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.getarrays.server.model.Server;

public interface serverRepository extends JpaRepository<Server, Long>{
	Server findByIpAdress(String ipAdress);
}
