package org.homework.repository;

import java.util.ArrayList;

public interface  JSONSerializer<Type> {

   void write(Type[] objekt);

    Type[] read();

}
