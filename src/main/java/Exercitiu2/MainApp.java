package Exercitiu2;

import Exercitiu2.Enums.TipChitara;
import Exercitiu2.Enums.TipTobe;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MainApp {
    public static void main(String[] args) {
        Set<InstrumentMuzical> set = new HashSet<InstrumentMuzical>();

    //subpunct 1
        Chitara c1 = new Chitara("DENIM",100, TipChitara.ACUSTICA,6);
        Chitara c2 = new Chitara("MARICICA",320, TipChitara.ELECTRICA,12);
        Chitara c3 = new Chitara("HEAVY",890, TipChitara.CLASICA,6);

        SetTobe s1 = new SetTobe("INCREDIBILE", 123, TipTobe.ACUSTICE,10,4);
        SetTobe s2 = new SetTobe("GRAVITATE", 534, TipTobe.ELECTRONICE,5,7);
        SetTobe s3 = new SetTobe("IMPRESIONANTE", 14000, TipTobe.ELECTRONICE,24,41);

        set.add(c1);
        set.add(c2);
        set.add(c3);
        set.add(s1);
        set.add(s2);
        set.add(s3);
     //   set.stream().forEach(System.out::println);

   //subpunct 2
   writeFile(set);

   //subpunct 3
        set=readFile();
        set.stream().forEach(System.out::println);

    //subpunct 4
        System.out.println("\nSubiectul 4:");
        System.out.println("\nImplementarea folosita pentru Set: "+ set.getClass());

    //subpunct 5
        System.out.println("\nSubiectul 5:");
        SetTobe s4 = new SetTobe("GRAVITATE", 534, TipTobe.ELECTRONICE,5,7);

        if(set.add(s4))
        {
            System.out.println("\nA fost adaugat");
        }
        else System.out.println("\nSetul de tobe nu a fost adaugat pentru ca a fost gasit duplicat\n");

    //subpunct 6
        System.out.println("\nSubiectul 6:");
        set.removeIf(instrumentMuzical -> instrumentMuzical.getPret()>3000);
        set.stream().forEach(System.out::println);

    //subpunct 7
        System.out.println("\nSubiectul 7:");
        set.stream()
                .filter(s-> s instanceof Chitara)
                .forEach(System.out::println);

    //subpunct 8
        System.out.println("\nSubiectul 8:");
        set.stream()
                .filter(s-> s.getClass()== SetTobe.class)
                .forEach(System.out::println);

    //subpucnt 9
        System.out.println("\nSubiectul 9:");

        Optional<Chitara> mostCorzi = set.stream()
                .filter(s->s.getClass()== Chitara.class)
                .map(m->(Chitara)m)
                .max(Comparator.comparing(s->(s.getNrCorzi_())));
        mostCorzi.ifPresent(
                chitara-> System.out.println("\nChitara cu cele mai multe corzi: " + mostCorzi.toString())
        );
    //var 2:
        Set<Chitara> set2 = set.stream().filter(s-> s instanceof Chitara)
                .map(s->(Chitara) s).collect(Collectors.toSet());
        Optional<Chitara> chitaraCorziMaxim = set2.stream()
                .max(Comparator.comparing(Chitara::getNrCorzi_));
        chitaraCorziMaxim.ifPresent(chitara -> System.out.println("\nCHitara cu cele mai multe corzi var 2: "+ chitaraCorziMaxim.toString()));

    //var 3:
        Optional<Chitara> chitaraCorziMaxi = set.stream()
                .filter(s -> s instanceof Chitara)
                .map(s -> (Chitara) s)
                .collect(Collectors.toSet())
                .stream()
                .max(Comparator.comparing(Chitara::getNrCorzi_));
        System.out.println(chitaraCorziMaxi);


        //subpunct 10
        System.out.println("\nSubiectul 10:");

        set.stream()
                .filter(s-> s instanceof SetTobe)
                .sorted(Comparator.comparing(s->((SetTobe)s).getNrTobe_()))
                .forEach(System.out::println);
        System.out.println("\n");
        set.stream()
                .filter(s-> s instanceof SetTobe)
                .sorted(Comparator.comparing(s->((SetTobe)s).getNrTobe_()).reversed())
                .forEach(System.out::println);
    }

    public static void writeFile(Set<InstrumentMuzical> list) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            //mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
            mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());

            File file = new File("src/main/resources/instrumente.json");
            mapper.writeValue(file, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<InstrumentMuzical> readFile() {
        try {
            File file = new File("src/main/resources/instrumente.json");
            ObjectMapper mapper = new ObjectMapper();
           // mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);

            //mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());
            return mapper.readValue(file, new TypeReference<Set<InstrumentMuzical>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
