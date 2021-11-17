package hu.petrik.olimpiaoop;

import java.util.Comparator;

public class IdoSzerintiKomparator implements Comparator<Versenyzo> {
    @Override
    public int compare(Versenyzo o1, Versenyzo o2) {
        /*int valasz;
        if (o1.getMP() > o2.getMP()) {
            valasz = 1;
        }
        else if(o1.getMP() < o2.getMP()) {
            valasz = -1;
        }
        else {
            valasz = 0;
        }

        return valasz;*/
        return o1.getMP() - o2.getMP();
    }
}
