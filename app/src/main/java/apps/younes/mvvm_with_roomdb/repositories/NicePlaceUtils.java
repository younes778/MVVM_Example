package apps.younes.mvvm_with_roomdb.repositories;

import java.util.ArrayList;
import java.util.List;

import apps.younes.mvvm_with_roomdb.models.NicePlace;

public class NicePlaceUtils {

    public static List<NicePlace> fillNicePlaces(){
        List<NicePlace> nicePlaces = new ArrayList<>();
        nicePlaces.add(new NicePlace("Havasu Falls","https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg"));
        nicePlaces.add(new NicePlace("Trondheim","https://i.redd.it/tpsnoz5bzo501.jpg"));
        nicePlaces.add(new NicePlace("Portugal","https://i.redd.it/qn7f9oqu7o501.jpg"));
        nicePlaces.add(new NicePlace("Rocky Mountain National Park","https://i.redd.it/j6myfqglup501.jpg"));
        nicePlaces.add(new NicePlace("Mahahual","https://i.redd.it/0h2gm1ix6p501.jpg"));
        nicePlaces.add(new NicePlace("Frozen Lake","https://i.redd.it/k98uzl68eh501.jpg"));
        nicePlaces.add(new NicePlace("White Sands Desert","https://i.redd.it/glin0nwndo501.jpg"));
        nicePlaces.add(new NicePlace("Austrailia","https://i.redd.it/obx4zydshg601.jpg"));
        nicePlaces.add(new NicePlace("Washington","https://i.imgur.com/ZcLLrkY.jpg"));

        return nicePlaces;
    }
}
