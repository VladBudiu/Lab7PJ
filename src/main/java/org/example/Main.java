package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;


//import static jdk.internal.org.jline.reader.impl.LineReaderImpl.CompletionType.List;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

record Carte(String titlul, String autorul,int anul){
    public String getAutorul()
    {
        return this.autorul;
    }
}
public class Main {
    public static void main(String[] args) {
        Map<Integer, Carte> map2 = new HashMap<Integer, Carte>();
        map2 = readFile();
        //System.out.println(map2.toString());
        var entrySet = map2.entrySet();
        entrySet.stream()
                .forEach(System.out::println);
        for (Map.Entry<Integer, Carte> entry : entrySet) {
            if (entry.getKey() == 2) {
                map2.remove(2);
                System.out.println("A fost stearsa id = " + 2);
                break;
            }
        }

        var iterator = entrySet.iterator();

        map2.putIfAbsent(7,new Carte("Adevar","Grai",2020));
    writeFile(map2);
//        System.out.println(map2.toString());
      // entrySet.stream().forEach(System.out::println);


        Set<Carte> set=new HashSet<Carte>();
        set= map2.values().stream()
                .filter(carte -> "Yuval Noah Harari".equals(carte.autorul())).collect(Collectors.toSet());
        set.stream().forEach(System.out::println);

        set.stream()
                .sorted(Comparator.comparing(Carte::getAutorul))
                .forEach(System.out::println);

        Optional <Carte> carte = set.stream().min(Comparator.comparing(Carte::anul));
        carte.ifPresent(carte1 -> System.out.println("Cea mai veche carte:\n"+ carte1.toString()));
    }

    public static void writeFile(Map<Integer,Carte> list) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("src/main/resources/carti.json");
            mapper.writeValue(file, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<Integer,Carte> readFile() {
        try {
            File file = new File("src/main/resources/carti.json");
           ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.readValue(file, new TypeReference<Map<Integer,Carte>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}