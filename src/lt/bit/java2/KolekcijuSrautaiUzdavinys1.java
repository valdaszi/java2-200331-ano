package lt.bit.java2;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/*
Uzdavinys 1. Atspausdinti kiekvieno menesio studentu ir ju vidurkiu sarasa
pagal studento vidurki jo mazejimo tvarka.

T.y. kazkas panasaus i
2020-01: Jonas 9, Ona 8, Petras 7.5
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
//                .sorted((a, b) ->
//                        Integer.compare(a.getPazymys().getData().getYear(), b.getPazymys().getData().getYear()))

                // Kazkodel neleidzia parasyti comparatoriu grandineles kaip kad bandyta apacioje:
                // issiaiskinau - reikia nurodyti kompiliatoriui komparatoriaus parametro tipa (StudentasPazymys a)
                // ir tada pasidaro gerai
                .sorted(Comparator.comparingInt((StudentasPazymys a) -> a.getPazymys().getData().getYear())
                  .thenComparingInt(a -> a.getPazymys().getData().getMonthValue())
                  .thenComparing(a -> a.getStudentas().getName()))

                .forEach(a -> System.out.println(a.getPazymys().getData() + " " + a.getStudentas().getName()));

    }
}
