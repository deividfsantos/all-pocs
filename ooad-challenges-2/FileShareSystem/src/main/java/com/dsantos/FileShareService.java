package com.dsantos;

import java.util.List;
import java.util.UUID;

public interface FileShareService {
    UUID save(String originalName, byte[] data);
    byte[] restore(UUID id);
    boolean delete(UUID id);
    List<FileMetadata> listFiles();
    List<FileMetadata> search(String query);
}
