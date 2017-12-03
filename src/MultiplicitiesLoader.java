import com.google.common.collect.Range;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Makarovaa on 24.11.17.
 */
public final class MultiplicitiesLoader {

   private ArrayList<Multiplicity> multiplicities = new ArrayList<>();

    private   MultiplicitiesLoader() {
    }

    public MultiplicitiesLoader(String filename) {
        loader(filename);
    }

    public ArrayList<Multiplicity> getMultiplicities() {
        return multiplicities;
    }

    private void loader (String fileName){

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            while (reader.ready()){
                multiplicities.add(new Multiplicity
                        (parseRanges(reader.readLine())));
            }
        }
        catch (IOException i){
            System.out.println("Неправильный путь.");
        }
    }
    private double parseDouble(String s) {
        return s.contains("i")? (s.startsWith("-")? Double.NEGATIVE_INFINITY: Double.POSITIVE_INFINITY): Double.parseDouble(s);
    }

     ArrayList<Range<Double>> parseRanges (String s){
        ArrayList<Range<Double>> ranges = new ArrayList<>();
        String pairTemp[] = s.split("U");
        Range<Double> r = null;
        for (String i:pairTemp){
            String pair[] = i.split(",");
            if (pair.length==2) {
                Double x1 = parseDouble(pair[0]);
                Double x2 = parseDouble(pair[1]);
                r = createRange(x1, x2);

            } else if (pair.length==1) {
                Double x1 = parseDouble(pair[0]);
                r = createRange(x1);
            }
            if (r == null) {
                throw new RuntimeException("Ошибка в чтении интервалов.");
                } else {
                    ranges.add(r);
                }

            }

        return ranges;
    }

     private Range<Double> createRange (Double x1, Double x2){

        if ((x1.isInfinite()) && (x2.isInfinite())){
            return Range.all();
        }
        else if ((!x1.isInfinite()) && (x2.isInfinite())){
            return Range.atLeast(x1);
        }
        else if ((x1.isInfinite() && (!x2.isInfinite()))){
            return Range.atMost(x2);
        }
        else if ((!x1.isInfinite() && (!x2.isInfinite()))){
            return Range.closed(x1,x2);
        }
        return null;
    }

    private Range<Double> createRange (Double x1){
        return Range.singleton(x1);
    }

}

