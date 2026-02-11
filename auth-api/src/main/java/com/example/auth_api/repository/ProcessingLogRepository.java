package com.example.auth_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProcessingLogRepository extends JpaRepository<ProcessingLogEntity, UUID> {

}
