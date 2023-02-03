package org.example.labs.serialize;

import org.example.labs.exception.ValidationException;

import java.io.*;

public class TxtMapper<T extends TxtFormat> implements IMapper<T> {

    @Override
    public void writeObject(String fileName, T object) {

        try (FileWriter fos = new FileWriter(fileName)) {
            fos.write(object.toStringSerialize());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T readObject(String fileName, Class<T> tClass) throws ValidationException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String objectLine = sb.toString();
            return (T) tClass.newInstance().toObject(objectLine);
        } catch (IOException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
