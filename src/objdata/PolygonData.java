package objdata;

import java.util.ArrayList;
import java.util.List;

public class PolygonData {

    public List <Point> PolPoints = new ArrayList<>();


    public List<Point> getPolPoints() {
        return PolPoints;
    }
    public boolean addPolPoints(Point point){
        return PolPoints.add(point);
    }
}

