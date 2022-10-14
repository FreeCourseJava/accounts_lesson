package org.homework.repository.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.homework.entity.HasKey;
import org.homework.exception.EntityNotFoundException;
import org.homework.repository.Repository;

import com.google.gson.Gson;

public abstract class AbstractRepository<ENT extends HasKey> implements Repository<ENT> {

    private final Class<ENT[]> serializationClass;
    private final Gson gson;

    private final String fileName;

    protected AbstractRepository(Class<ENT[]> serializationClass, String fileName) {
        this.serializationClass = serializationClass;
        this.fileName = fileName;
        this.gson = new Gson();
    }

    @Override
    public ENT load(String key) {
        ENT value = getValues().get(key.toLowerCase());
        if (value == null) {
            throw new EntityNotFoundException(key);
        }
        return value;
    }

    @Override
    public void save(ENT entity) {
        Map<String, ENT> values = getValues();
        values.replace(entity.getKey().toLowerCase(), entity);
        Collection<ENT> newValues = values.values();
        saveInternal(newValues);
    }

    private void saveInternal(Collection<ENT> newValues) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName); PrintWriter printWriter = new PrintWriter(fileOutputStream)) {
            printWriter.print(gson.toJson(newValues));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, ENT> getValues() {
        try (FileReader fileReader = new FileReader(fileName); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            List<ENT> list = bufferedReader.lines()
                    .map(line -> gson.fromJson(line, serializationClass))
                    .flatMap(array -> Arrays.stream(array))
                    .collect(Collectors.toList());

            return list.stream().collect(Collectors.toMap(value -> value.getKey().toLowerCase(), value -> value));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
