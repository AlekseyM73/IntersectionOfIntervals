import com.google.common.collect.Range;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by Makarovaa on 01.12.17.
 */
public final class IntersectionOfIntervals {

   private MultiplicitiesLoader multiplicitiesLoader;
   private double x;

    private IntersectionOfIntervals(){

   }
    public IntersectionOfIntervals(MultiplicitiesLoader multiplicitiesLoader) {
        this.multiplicitiesLoader = multiplicitiesLoader;
    }

    public IntersectionOfIntervals(MultiplicitiesLoader multiplicitiesLoader, double x) {
        this.multiplicitiesLoader = multiplicitiesLoader;
        this.x = x;
    }

    public HashSet<Range<Double>> setOfIntervals (){

        ArrayList<Multiplicity> multiplicities = multiplicitiesLoader.getMultiplicities();
        Range<Double> rangeTemp = null;
        HashSet<Range<Double>> result = new HashSet<>();
        int intersectionCounter = 0;
        LinkedList<Multiplicity> linkedList = new LinkedList<>();

        for (int i = 0; i < multiplicities.size(); i++) {
            linkedList.addAll(multiplicities);
            Multiplicity multiplicityFirst = multiplicities.get(i);
            ArrayList<Range<Double>> rangeFirst = multiplicityFirst.getRanges();
            linkedList.remove(multiplicities.get(i));

            for (int a = 0; a < rangeFirst.size(); a++) {
                int j = 0;

                while (j<multiplicities.size()-1) {
                    Multiplicity multiplicitySecond = linkedList.removeFirst();
                    ArrayList<Range<Double>> rangeSecond = multiplicitySecond.getRanges();

                    for (int b = 0; b < rangeSecond.size(); b++) {
                        if (rangeFirst.get(a).isConnected(rangeSecond.get(b))) {
                            if (rangeTemp == null) {

                                rangeTemp = rangeFirst.get(a).intersection(rangeSecond.get(b));
                                intersectionCounter++;
                            } else if (rangeTemp.isConnected(rangeSecond.get(b))) {

                                rangeTemp = rangeTemp.intersection(rangeSecond.get(b));
                                intersectionCounter++;
                            }
                        }
                    }
                    linkedList.addLast(multiplicitySecond);
                    j++;
                }
                if (!(rangeTemp == null)&&(intersectionCounter == multiplicities.size()-1)){
                    result.add(rangeTemp);
                }
                rangeTemp = null;
                intersectionCounter = 0;
            }
            linkedList.removeAll(multiplicities);
        }

        return result;
    }

    public double findNumber () {
        HashSet<Range<Double>> intervals = setOfIntervals();
        for (Range<Double> range : intervals) {
            if (range.contains(x)) {
                return x;
            }
        }
        double x1;
        double x2;
        double result = 0;
        double odds = Double.MAX_VALUE;
        for (Range<Double> range : intervals) {
            x1 = range.lowerEndpoint();
            x2 = range.upperEndpoint();
            if (Math.abs(x - x1) < Math.abs(x - x2)) {
                if (Math.abs(x - x1) < odds) {
                    result = x1;
                    odds = Math.abs(x - x1);
                }
            } else {
                if (Math.abs(x - x2) < odds) {
                    result = x2;
                    odds = Math.abs(x - x2);
                }
            }
        }
        return result;
    }
}

