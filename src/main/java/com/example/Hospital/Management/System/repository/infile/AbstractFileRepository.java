package com.example.Hospital.Management.System.repository.infile;

import com.example.Hospital.Management.System.repository.IdGenerator;
import com.example.Hospital.Management.System.repository.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractFileRepository<T> implements Repository<T> {
    private final String filePath;
    private final Class<T> type;
    protected final ObjectMapper objectMapper;
    protected final Map<String, T> storage = new ConcurrentHashMap<>();

    public AbstractFileRepository(String filePath, Class<T> type) {
        this.filePath = filePath;
        this.type = type;

        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        loadFromFile();
    }

    protected abstract String getEntityId(T entity);
    protected abstract void setEntityId(T entity, String id);
    protected abstract String getIdPrefix();

    @Override
    public T save(T entity) {
        if (getEntityId(entity) == null || getEntityId(entity).isEmpty()) {
            setEntityId(entity, IdGenerator.generateId(getIdPrefix()));
        }
        storage.put(getEntityId(entity), entity);
        saveToFile();
        return entity;
    }

    @Override
    public Optional<T> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void delete(String id) {
        storage.remove(id);
        saveToFile();
    }

    @Override
    public void deleteAll() {
        storage.clear();
        saveToFile();
    }

    protected void saveToFile() {
        try {
            objectMapper.writeValue(new File(filePath), new ArrayList<>(storage.values()));
        } catch (IOException e) {
            System.err.println("Eroare la salvarea fisierului " + filePath + ": " + e.getMessage());
        }
    }

    protected void loadFromFile() {
        File file = new File(filePath);
        if (file.exists() && file.length() > 0) {
            try {
                CollectionType listType = objectMapper.getTypeFactory()
                        .constructCollectionType(List.class, type);
                List<T> list = objectMapper.readValue(file, listType);
                list.forEach(item -> storage.put(getEntityId(item), item));
            } catch (IOException e) {
                System.err.println("Eroare la citirea fisierului " + filePath + ": " + e.getMessage());
            }
        }
    }
}