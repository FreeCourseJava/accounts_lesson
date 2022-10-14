package org.homework.repository;


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

import org.homework.entity.Currency;
import org.homework.exception.EntityNotFoundException;

import com.google.gson.Gson;

public class CurrencyRepository {

    private static final Gson gson = new Gson();
    private static final String fileName = "currencies.json";

    private static final Class<Currency[]> serializationClass = Currency[].class;

    public static Currency load(String key) {
        Currency value = getValues().get(key.toLowerCase());
        if (value == null) {
            throw new EntityNotFoundException(key);
        }
        return value;
    }


    public static void save(Currency entity) {
        Map<String, Currency> values = getValues();
        values.replace(entity.getKey().toLowerCase(), entity);
        Collection<Currency> newValues = values.values();
        saveInternal(newValues);
    }

    private static void saveInternal(Collection<Currency> newValues) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName); PrintWriter printWriter = new PrintWriter(fileOutputStream)) {
            printWriter.print(gson.toJson(newValues));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, Currency> getValues() {
        try (FileReader fileReader = new FileReader(fileName); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            List<Currency> list = bufferedReader.lines()
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
