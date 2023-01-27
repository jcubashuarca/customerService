package com.whiz.customermanagement.customerService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.whiz.customermanagement.customerService.entity.Clients;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Clients, UUID> {
}
