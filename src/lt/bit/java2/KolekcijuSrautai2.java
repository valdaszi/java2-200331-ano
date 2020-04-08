package lt.bit.java2;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KolekcijuSrautai2 {

    static List<Studentas> studentai;
    static {
        studentai = new ArrayList<>();
        studentai.add(new Studentas("Ona", "ona@gmail.com", "Kaunas",
                Arrays.asList(
                        new Pazymys(LocalDate.of(2020, 1, 31), 9),
                        new Pazymys(LocalDate.of(2020, 1, 31), 8)
                )));
        studentai.add(new Studentas("Jonas", "jonas@gmail.com", "Babtai",
                Arrays.asList(
                        new Pazymys(LocalDate.of(2020, 1, 30), 5),
                        new Pazymys(LocalDate.of(2020, 1, 31), 10),
                        new Pazymys(LocalDate.of(2020, 2, 1), 8),
                        new Pazymys(LocalDate.of(2020, 2, 2), 7)
                )));
        studentai.add(new Studentas("Petras", "petras@takas.lt", "Kaunas",
                Arrays.asList(
                        new Pazymys(LocalDate.of(2020, 1, 30), 8),
                        new Pazymys(LocalDate.of(2020, 2, 1), 9),
                        new Pazymys(LocalDate.of(2020, 2, 2), 2)
                )));
    }

    public static void main(String[] args) {
        // 1. is kiek skirtingu miestu turime studentus
        // a) paleidziam studentus i srauta
        // b) is studento istraukiam miesta
        // c) isrenkame unikaliu elementus
        // d) paskaiciuojam stream'o dydi

        long x = studentai.stream()
                .map(Studentas::getMiestas)  // Studentas -> Miestas (String)
                .distinct()
                .count();
        System.out.println(x);


        // 2. Koks studentu is Kauno pazymiu, gautu 2020m vasario menesi, vidurkis
        // a) paleidziam studentus i srauta
        // b) atfiltruojam studentus pagal miesta
        // c) istraukiam is studento pazymius ir juos paleidziam i srauta
        // d) filtruojam pazymius pagal reikiama menesi
        // e) istraukima is pazymiu ivertinimo bala ir paleidziam i int tipo srauta
        // f) suskaiciuojam vidurki

        double y = studentai.stream()
                .filter(s -> s.getMiestas().equalsIgnoreCase("Kaunas"))
                .flatMap(s -> s.getPazymiai().stream())
                .filter(p ->
                        p.getData().getYear() == 2020 &&
                        p.getData().getMonth() == Month.FEBRUARY)
                .mapToInt(Pazymys::getPazymys)
                .average().orElse(-1);
        System.out.println(y);

        // 3. Reikia paskaiciuoti kiekvieno studento pazymiu vidurki
        // List<StudentoVidurkis> resultatas;
        class StudentoVidurkis {
            Studentas studentas;
            double vidurkis;
        }
        // a) paleidziam studentus i srauta
        // b) suskaiciuojam studento pazymiu vidurki suformuodami StudentoVidurkis
        //      b.1) paleidziam pazmius i srauta
        //      b.2) pakeiciam pazymius i pazymio balo int srauta
        //      b.3) suskaiciuojam vidurki
        // c) sudedame StudentoVidurkis objektus i lista

        List<StudentoVidurkis> rezultatas = studentai.stream()
                .map(s -> {
                    StudentoVidurkis studentoVidurkis = new StudentoVidurkis();
                    studentoVidurkis.studentas = s;
                    studentoVidurkis.vidurkis =
                            s.getPazymiai().stream()
                            .mapToInt(Pazymys::getPazymys)
                            .average()
                            .orElse(0);
                    return studentoVidurkis;
                })
                .collect(Collectors.toList());

        rezultatas.forEach(s ->
                System.out.println(s.studentas.getName() + " " + s.vidurkis));

        studentai.stream()
                .map(s -> {
                    StudentoVidurkis studentoVidurkis = new StudentoVidurkis();
                    studentoVidurkis.studentas = s;
                    studentoVidurkis.vidurkis =
                            s.getPazymiai().stream()
                                    .mapToInt(Pazymys::getPazymys)
                                    .average()
                                    .orElse(0);
                    return studentoVidurkis;
                })
                .forEach(s ->
                        System.out.println(s.studentas.getName() + " " + s.vidurkis));



        // stream'as (studentsNamesStream) nebus paleistas kol nebus galutines operacijos
        Stream<String> studentsNamesStream = studentai.stream()
                .filter(s -> "kaunas".equalsIgnoreCase(s.getMiestas()))
                .map(Studentas::getName)
                .peek(System.out::println);
        System.out.println("studentsNamesStream -->");

        // tik kai bandysim suskaiciuoti stream'o dydi (count) tai stream'as pasileis
        // ir tik dabar bus atspausdinti ir studentu vardai!!!
        long length = studentsNamesStream.count();
        System.out.println(length);

        // cia bus klaida nes studentsNamesStream jau panaudotas!!!!
        System.out.println(studentsNamesStream.findAny().isEmpty());

    }


}


