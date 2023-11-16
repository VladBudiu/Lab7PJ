package Exercitiu1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

import java.util.*;
import java.util.stream.Collectors;

record Carte(String titlul, String autorul,int anul){
    @Override
    public String toString() {
        return "Carte:{" +
                "\ntitlu: " + titlul +
                "\nautor: " + autorul +
                "\nan: " + anul +
                "\n}";
    }
}
public class Main {
    public static void main(String[] args) {
        Map<Integer, Carte> map2 = new HashMap<Integer, Carte>();
        map2 = readFile();
       //subpunct 1
        System.out.println("\nSubiect 1:");
        var entrySet = map2.entrySet();
        entrySet.stream()
                .forEach(System.out::println);
        //subpunct2
        System.out.println("\nSubiect 2:");
        for (Map.Entry<Integer, Carte> entry : entrySet) {
            if (entry.getKey() == 2) {
                map2.remove(2);
                System.out.println("A fost stearsa id = " + 2);
                break;
            }
        }
        entrySet.stream().forEach(System.out::println);
        //subpunct 3
        System.out.println("\nSubiect 3:");
        map2.putIfAbsent(7,new Carte("Adevar","Grai",2020));
        entrySet.stream().forEach(System.out::println);
        //subpunct 4
        System.out.println("\nSubiect 4:");
        writeFile(map2);
        //subpunct 5
        System.out.println("\nSubiect 5:");
        Set<Carte> set=new HashSet<Carte>();
        set= map2.values().stream()
                .filter(carte -> "Yuval Noah Harari".equals(carte.autorul())).collect(Collectors.toSet());
        set.stream().forEach(System.out::println);
        //subpunct 6
        System.out.println("\nSubiect 6:");
        set.stream()
                .sorted(Comparator.comparing(c->c.autorul()))
                .forEach(System.out::println);
        //subpunct 7
        System.out.println("\nSubiect 7:");
        Optional <Carte> carte = set.stream().min(Comparator.comparing(Carte::anul));
        carte.ifPresent(carte1 -> System.out.println("Cea mai veche carte:\n"+ carte1.toString()));
    }

    public static void writeFile(Map<Integer,Carte> list) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("src/main/resources/carti.json");
            mapper.writeValue(file, list);
            System.out.println("A fost scris in fisier!");
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