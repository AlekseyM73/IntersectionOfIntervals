import com.google.common.collect.Range;
import java.util.ArrayList;

/**
 * Created by Makarovaa on 24.11.17.
 */
public final class Multiplicity {

    private ArrayList<Range<Double>> range;

    public Multiplicity(ArrayList<Range<Double>> range) {
        this.range = range;
    }

    public ArrayList<Range<Double>> getRanges() {
        return range;
    }

}


