package apps.younes.mvvm_with_roomdb.models;

import java.util.ArrayList;
import java.util.List;

public class PlaceCategory {
    private String name;
    private List<NicePlace> places;

    public PlaceCategory(String name) {
        this.name = name;
        places = new ArrayList<>();
    }

    public void addPlace(String title, String imageUrl, String details) {
        places.add(new NicePlace(title,imageUrl,details));
    }

    public String getName() {
        return name;
    }

    public List<NicePlace> getPlaces(){
        return places;
    }
}
