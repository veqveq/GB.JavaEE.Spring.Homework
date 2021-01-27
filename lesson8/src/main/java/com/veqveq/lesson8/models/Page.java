package com.veqveq.lesson8.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Page<T extends Object> {

    private List<T> entities;

    public Page(int number, int size, List<T> objectList) {
        number = (number - 1) * size;
        size = number + size;
        entities = new ArrayList<>();

        for (int i = number; i < size; i++) {
            if (i == objectList.size()) return;
            entities.add(objectList.get(i));
        }
    }

    public List<T> getEntities() {
        return Collections.unmodifiableList(entities);
    }
}
