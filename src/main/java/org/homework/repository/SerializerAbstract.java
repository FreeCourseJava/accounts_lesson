package org.homework.repository;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public abstract class SerializerAbstract<Type> implements JSONSerializer<Type> {

    private Class<Type[]> readType;

    private String fileName;

    private Gson gson;

    public SerializerAbstract(Class<Type[]> readType, String fileName) {
        this.readType = readType;
        this.gson = new Gson();
        this.fileName=fileName;
    }

    @Override
    public void write(Type[] objekt) {
        String toWrite = gson.toJson(objekt);
        try (OutputStream outputStream = new FileOutputStream(fileName);
             PrintStream writer = new PrintStream(outputStream)) {
            writer.println(toWrite);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка записи");
        }
    }

    @Override
    public Type[] read() {
        String temp = "";
        try (InputStream inputStream = new FileInputStream(fileName);
             Scanner reader = new Scanner(inputStream)) {
            temp = reader.nextLine();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка чтения");
        }
        return gson.fromJson(temp, readType);
    }


}
