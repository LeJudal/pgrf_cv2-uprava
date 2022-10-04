package objdata;

import java.util.ArrayList;
import java.util.List;

public class PolygonData {

    public List <Points> PolPoints = new ArrayList<>();


    public List<Points> getPolPoints() {
        return PolPoints;
    }
    public boolean addPolPoints(Points point){
        return PolPoints.add(point);
    }
}

