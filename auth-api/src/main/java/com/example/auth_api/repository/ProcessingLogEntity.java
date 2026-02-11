package com.example.auth_api.repository;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "processing_log")
public class ProcessingLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "user_id", nullable = false)
    private UUID userId;
    @Column(name = "input_text", nullable = false)
    private String inputText;
    @Column(name = "output_text", nullable = false)
    private String outputText;
    @Column(
            name = "create_at",
            nullable = false,
            updatable = false
    )
    @CreationTimestamp
    private LocalDateTime createAt;

    public ProcessingLogEntity(UUID userId, String inputText, String outputText) {
        this.userId = userId;
        this.inputText = inputText;
        this.outputText = outputText;
    }

    public ProcessingLogEntity() {

    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getOutputText() {
        return outputText;
    }

    public void setOutputText(String outputText) {
        this.outputText = outputText;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

}
