import com.raylib.Jaylib.Vector2;
import com.raylib.Raylib;
import com.raylib.Raylib.Rectangle;

import java.util.ArrayList;


public class customer {

    Vector2 worldPos;
    float speed;
    float width;
    float height;

    public void spawn(ArrayList<customer> customer_list)
    {
        //get refrence to self
        var self = this;
        //add into customer list
        customer_list.add(self);
    }

}
