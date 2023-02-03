package org.example.labs.run;

import org.example.labs.model.Motorcycle;
import org.example.labs.serialize.JsonMapper;
import org.example.labs.serialize.TxtMapper;
import org.example.labs.serialize.XmlMapper;

public class DeserializationDemo {
    public static void main(String[] args) {
        Motorcycle motorcycle1, motorcycle2, motorcycle3;
        motorcycle1 = new JsonMapper<Motorcycle>().readObject("Motorcycle.json", Motorcycle.class);
        motorcycle2 = new XmlMapper<Motorcycle>().readObject("Motorcycle.xml", Motorcycle.class);
        motorcycle3 = new TxtMapper<Motorcycle>().readObject("Motorcycle.txt", Motorcycle.class);

        System.out.println(motorcycle1);
        System.out.println(motorcycle2);
        System.out.println(motorcycle3);
    }
}
