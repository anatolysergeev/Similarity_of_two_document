package Duplicate;

import java.io.InputStream;
import java.util.*;
import java.util.zip.CRC32;

public class ShinglingText {
    static final int shingleLen = 10;
    static final String[] stopWord = {
            "это", "как", "так",
            "и", "в", "над",
            "к", "до", "не",
            "на", "но", "за",
            "то", "с", "ли",
            "а", "во", "от",
            "со", "для", "о",
            "же", "ну", "вы",
            "бы", "что", "кто",
            "он", "она" };

    private InputReader in;
    private String[] canonizeText;
    private Long[] checkSum;
    private CRC32 hash;

    public ShinglingText(InputStream stream) {
        in = new InputReader(stream);
        canonize();
        genShingle();
    }

    public int compare(ShinglingText that) {
        int same = 0;
        for (int i = 0; i < this.checkSum.length; ++i)
            for (int j = 0; j < that.checkSum.length; ++j)
                if (this.checkSum[i].equals(that.checkSum[j])) { ++same; break; }

        //return (int) (same / (double) (this.checkSum.length + that.checkSum.length - same) * 100);
        return (int) ((same * 2) / (double) (this.checkSum.length + that.checkSum.length) * 100) ;
    }

    private void canonize() {
        List<String> canonize = new ArrayList<>();
        try {
            outer: while (true) {
                String str = in.next();
                for (String x : stopWord) if (x.equals(str)) continue outer;
                String strLC = str.toLowerCase();
                canonize.add(strLC);
            }
        } catch (InputMismatchException e) {}
        canonizeText = new String[canonize.size()];
        canonizeText = canonize.toArray(canonizeText);
    }

    private void genShingle() {
        HashSet<Long> hs = new HashSet<>();
        hash = new CRC32();
        for (int i = 0; i < canonizeText.length - shingleLen + 1; ++i) {
            hash.reset();
            for (int j = 0; j < shingleLen; ++j)
                hash.update(canonizeText[j + i].getBytes());
            hs.add(hash.getValue());
        }
        checkSum = new Long[hs.size()];
        checkSum = hs.toArray(checkSum);
    }
}
