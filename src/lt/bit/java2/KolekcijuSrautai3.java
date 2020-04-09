package lt.bit.java2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KolekcijuSrautai3 {

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

        // 1. Suskaiciuoti studentu pazymiu sumas
        // a) Paleidziam studentu stream'a
        // b) Paleidziam studento pazymius i pazymiu stream'a
        // c) Taikome reduce suskaiciuoti pazymius

        /*
            element -> element -> element -> ...

            result = null | identity;
            while (element) {
                result = lambda.apply(result, element);
            }
            return result;

         */

        int suma = studentai.stream()
                .flatMap(s -> s.getPazymiai().stream())
                .map(Pazymys::getPazymys)
                .reduce((result, element) -> result + element) // Integer::sum
                .orElse(0);
        System.out.println(suma);


        suma = studentai.stream()
                .flatMap(s -> s.getPazymiai().stream())
                .reduce(100,
                        (result, element) -> result + element.getPazymys(),
                        (result1, result2) -> result1 + result2); // Integer::sum
        System.out.println(suma);


        // 2. Suskaiciuoti ir atspausdinti visu studentu pazymiu vidurki
        // a) Paleidziam studentu stream'a
        // b) Paleidziam studento pazymius i pazymiu stream'a
        // c) Taikome reduce suskaiciuoti ir atspausdinti tarpinius ir galutini vidurkius
        System.out.println("***** Uzdavinys #2");

        class EinamasisVidurkis {
            int count;
            int suma;

            public EinamasisVidurkis() {}

            public EinamasisVidurkis(int count, int suma) {
                this.count = count;
                this.suma = suma;
            }

            double vidurkis() {
                return (double)suma / count;
            }

            void print(String header) {
                System.out.println(header + " pazymiu skaicius: " + count + ", vidurkis: " + (count > 0 ? vidurkis() : "-nera-"));
            }
        }
        /*

            ------A-
                    C(A, A) -|
            ------A-         |
                             C(A, A)
            ------A- --------|

         */
        studentai.parallelStream()
                .flatMap(s -> s.getPazymiai().stream())
                .reduce(new EinamasisVidurkis(),    // identity
                        // accumulator
                        (result, element) -> {
                            var naujasRes = new EinamasisVidurkis(
                                    result.count + 1,
                                    result.suma + element.getPazymys());
                            naujasRes.print("" + element.getPazymys());
                            return naujasRes;
                        },
                        // combiner
                        (result1, result2) -> {
                            var naujasRes = new EinamasisVidurkis(
                                    result1.count + result2.count,
                                    result1.suma + result2.suma);
                            naujasRes.print("combiner");
                            return naujasRes;
                        }
                )
                .print("VISO");

        // 3. Uzdavinys toks pat kaip 2 tik bandome ji spresti nekurdami
        // kiekvienos operacijos metu naujo EinamasisVidurkis objekto
        System.out.println("***** Uzdavinys #3");

        studentai.parallelStream()
                .flatMap(s -> s.getPazymiai().stream())
                .collect(
                        EinamasisVidurkis::new,
                        (result, element) -> {
                            result.count++;
                            result.suma += element.getPazymys();
                            result.print(element.getPazymys() + "");
                        },
                        (result, result2) -> {
                            result.count += result2.count;
                            result.suma += result2.suma;
                            result.print("combiner");
                        }

                )
                .print("VISO");

        // 4. Sugrupuoti studentus pagal miestus
        // t.y. gauti resultata: Map<String, List<Studentas>>
        System.out.println("***** Uzdavinys #4");

        Map<String, List<Studentas>> mapas = studentai.stream()
                .collect(Collectors.groupingBy(Studentas::getMiestas));

        mapas.keySet().forEach(k ->
            System.out.println("Is miesto " + k + " yra " +
                    mapas.get(k).size() + " studentai/as/u")
        );



    }
}
