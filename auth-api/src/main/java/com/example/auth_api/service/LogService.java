package com.example.auth_api.service;

import com.example.auth_api.dto.LogDto;
import com.example.auth_api.repository.ProcessingLogEntity;
import com.example.auth_api.repository.ProcessingLogRepository;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private final ProcessingLogRepository processingLogRepository;

    public LogService(ProcessingLogRepository processingLogRepository) {
        this.processingLogRepository = processingLogRepository;
    }

    public void logs(LogDto log ) {
        ProcessingLogEntity processingLogEntity =
                new ProcessingLogEntity(log.userId(), log.inputText(), log.outputText());
        processingLogRepository.save(processingLogEntity);
    }

}
