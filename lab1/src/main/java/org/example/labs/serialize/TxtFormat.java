package org.example.labs.serialize;

import org.example.labs.exception.ValidationException;

public interface TxtFormat<T> {
    String toStringSerialize();
    T toObject(String string) throws ValidationException;
}
