package com.dsantos;

import java.time.Instant;
import java.util.UUID;

public class FileMetadata {
    private final UUID id;
    private final String originalName;
    private final long size;
    private final Instant createdAt;
    private final String path;

    public FileMetadata(UUID id, String originalName, long size, Instant createdAt, String path) {
        this.id = id;
        this.originalName = originalName;
        this.size = size;
        this.createdAt = createdAt;
        this.path = path;
    }

    public UUID getId() { return id; }
    public String getOriginalName() { return originalName; }
    public long getSize() { return size; }
    public Instant getCreatedAt() { return createdAt; }
    public String getPath() { return path; }

    @Override
    public String toString() {
        return "FileMetadata{" +
                "id=" + id +
                ", originalName='" + originalName + '\'' +
                ", size=" + size +
                ", createdAt=" + createdAt +
                ", path='" + path + '\'' +
                '}';
    }
}
