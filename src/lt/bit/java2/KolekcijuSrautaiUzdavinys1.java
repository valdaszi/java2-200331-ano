package lt.bit.java2;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/*
Uzdavinys 1. Atspausdinti kiekvieno menesio studentu ir ju vidurkiu sarasa
pagal studento vidurki jo mazejimo tvarka.

T.y. kazkas panasaus i
2020-01: Jonas 9,
2020-01: Ona 8,
2020-01: Petras 7.5
2020-02: Ona 9.5, Jonas 8, Petras 6
 */
public class KolekcijuSrautaiUzdavinys1 {

    public static void main(String[] args) {
        List<Studentas> studentai = Studentas.generuotiStudentus();

        class StudentasPazymys {
            Studentas studentas;
            Pazymys pazymys;

            public StudentasPazymys(Studentas studentas, Pazymys pazymys) {
                this.studentas = studentas;
                this.pazymys = pazymys;
            }

            public Studentas getStudentas() {
                return studentas;
            }

            public Pazymys getPazymys() {
                return pazymys;
            }
        }

//        class MetaiMenuoStudentas {
//            int year;
//            int  month;
//            String name;
//        }
//        Map<MetaiMenuoStudentas, List<StudentasPazymys>> maps;

        // demo - isrenkame studento pazymius pagal metus ir menesi (kokia menesio diena yra nesvarbu)
        studentai.stream()
                .flatMap(s -> s.getPazymiai().stream().map(p -> new StudentasPazymys(s, p)))    // Stream<Pazymys>  -> Stream<StudentasPazymys>

                // Kazkodel neleidzia parasyti comparatoriu grandineles kaip kad bandyta apacioje:
                // issiaiskinau - reikia nurodyti kompiliatoriui komparatoriaus parametro tipa (StudentasPazymys a)
                // ir tada pasidaro gerai
                .sorted(Comparator.comparingInt((StudentasPazymys a) -> a.getPazymys().getData().getYear())
                  .thenComparingInt(a -> a.getPazymys().getData().getMonthValue())
                  .thenComparing(a -> a.getStudentas().getName()))

                .forEach(a -> System.out.println(a.getPazymys().getData() + " " + a.getStudentas().getName()));

        class MetaiMenuo {
            int metai;
            int menuo;

            public MetaiMenuo(int metai, int menuo) {
                this.metai = metai;
                this.menuo = menuo;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                MetaiMenuo that = (MetaiMenuo) o;
                return metai == that.metai &&
                        menuo == that.menuo;
            }

            @Override
            public int hashCode() {
                return Objects.hash(metai, menuo);
            }

            @Override
            public String toString() {
                return metai + "-" + menuo;
            }
        }

        // 1.
        studentai.stream()
                .flatMap(s -> s.getPazymiai().stream().map(p -> new StudentasPazymys(s, p)))    // Stream<Pazymys>  -> Stream<StudentasPazymys>

                // reikia sugrupuoti pagal metus-menesi: Map<MetaiMenuo, List<StudentasPazymys>>
                .collect(Collectors.groupingBy(a -> new MetaiMenuo(a.getPazymys().getData().getYear(),
                        a.getPazymys().getData().getMonthValue())))
                .entrySet().stream()
                .forEach(x -> System.out.println(x.getKey() + ": " + x.getValue().size()));


        // 2. Vietoj MetaiMenuo panaudojam LocalDate - visada tas gerai panaudoti standartines klases jei tinka,
        // bet ju negalime modifikuoti jei to prireikia
        studentai.stream()
                .flatMap(s -> s.getPazymiai().stream().map(p -> new StudentasPazymys(s, p)))    // Stream<Pazymys>  -> Stream<StudentasPazymys>

                // reikia sugrupuoti pagal metus-menesi: Map<LocalDate, List<StudentasPazymys>>
                .collect(Collectors.groupingBy(a -> LocalDate.of(a.getPazymys().getData().getYear(),
                        a.getPazymys().getData().getMonthValue(), 1)))
                .entrySet().stream()
                .forEach(x -> System.out.println(x.getKey() + ": " + x.getValue().size()));


    }
}


