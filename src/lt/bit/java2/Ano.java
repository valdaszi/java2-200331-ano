package lt.bit.java2;

import java.util.*;

/**
 * Pagrindine klase nuo kurios pradedame vykdyma
 */
public class Ano {

    public static void main(String[] args) {
        System.out.println("Yes");

        List<Adresas> adresai = new ArrayList<>();
        adresai.add(new Adresas("Babtai", "Vytauto", "12a"));
        adresai.add(new Adresas("Babtai", "Vyturiu", "1"));
        adresai.add(new Adresas("Kaunas", "Savanoriu", "123"));

        // key -> value
        Map<String, List<Adresas>> map = new HashMap<>();

        for (Adresas adresas : adresai) {
            if (map.containsKey(adresas.miestas)) {
                map.get(adresas.miestas).add(adresas);
            } else {
                List<Adresas> adrs = new ArrayList<>();
                adrs.add(adresas);
                map.put(adresas.miestas, adrs);
            }
        }

        System.out.println("Viso skirtingu miestu radome: " + map.size());

        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key).size());
        }

        // optimaliau iteruoti per Map.Entry objektus (map.entrySet())
        // nes is karto galime prieiti ir prie rakto (key) ir prie reiksmes (value)
        for (Map.Entry<String, List<Adresas>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().size());
        }

    }

}

class Adresas {
    String miestas;
    String gatve;
    String namas;

    public Adresas(String miestas, String gatve, String namas) {
        this.miestas = miestas;
        this.gatve = gatve;
        this.namas = namas;
    }
}
