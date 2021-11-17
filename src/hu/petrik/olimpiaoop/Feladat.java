package hu.petrik.olimpiaoop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Feladat {

    private HashMap<String, ArrayList<Versenyzo>> statisztics;

    public Feladat() {
        this.statisztics = new HashMap<>();
        Beolvasas();
        Kiir();
        sportagak(); //sout(adatok.size());
        distinctVersenyzokSzam();
        LegtobbGyoztes();
    }

    private void Beolvasas() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("eredmenyek.txt"));
            String sor = br.readLine();
            while (sor != null) {
                String[] st = sor.split(" ");
                String sportag = st[0];
                String ido = st[1];
                String nev = st[2] + " " + st[3];
                Versenyzo v = new Versenyzo(nev, ido);
                statisztics.putIfAbsent(sportag, new ArrayList<>());
                statisztics.put(sportag, statisztics.get(sportag)).add(v);
                sor = br.readLine();
            }
            br.close();
        }
        catch (IOException e){
            e.getMessage();
        }
    }

    private void Kiir() {
        for (Map.Entry<String, ArrayList<Versenyzo>> entry : statisztics.entrySet()) {
            System.out.println(entry.getKey() + ": ");
            for (Versenyzo item :
                    entry.getValue()) {
                System.out.println("\t" + item);
            }
            System.out.println();
        }
    }

    public void sportagak() {
        int sportagakSzama = 0;
        for (Map.Entry<String, ArrayList<Versenyzo>> entry : statisztics.entrySet()) {
            sportagakSzama++;
        }
        System.out.println(sportagakSzama);
    }

    public void distinctVersenyzokSzam() {
        HashSet<String> nevek = new HashSet<>();
        for (Map.Entry<String, ArrayList<Versenyzo>> entry : statisztics.entrySet()) {
            for (Versenyzo item : entry.getValue()) {
                nevek.add(item.getNev());
            }
        }
        System.out.println("A versenyzők " + "(" + nevek.size() + "):");
        for (String item: nevek) {
            System.out.println(item);
        }
        System.out.println();
    }

    public void IdoSzerintiRendezes() {
        for (Map.Entry<String, ArrayList<Versenyzo>> entry : statisztics.entrySet()) {
            entry.getValue().sort(new IdoSzerintiKomparator());
        }
    }

    public void LegtobbGyoztes() {
        HashMap<String, Integer> gyoztesek = new HashMap<>();
        for (Map.Entry<String, ArrayList<Versenyzo>> entry : statisztics.entrySet()) {
            String nev = entry.getValue().get(0).getNev();
            gyoztesek.putIfAbsent(nev, 0);
            gyoztesek.put(nev, gyoztesek.get(nev)+1);
        }
        ArrayList<Map.Entry<String, Integer>> ee = new ArrayList<>(gyoztesek.entrySet());
        ee.sort(new HashMapKomperator());
        for (Map.Entry<String, Integer> entry: ee) {
            System.out.println(entry);
        }
        System.out.println("A győztes: " + ee.get(0));
    }
}
