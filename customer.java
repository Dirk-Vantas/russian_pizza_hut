import com.raylib.Jaylib.Vector2;
import com.raylib.Raylib;
import com.raylib.Raylib.Rectangle;

import java.util.ArrayList;


public class customer {

    Vector2 worldPos;
    Rectangle collision_body;
    float speed;
    int width;
    int height;
    int angerLimit;
    //attention span in seconds
    int attention_span;
    int thought;


    public customer(Vector2 worldPos, float speed, int width, int height)
    {
        this.worldPos = worldPos;
        this.speed = speed;
        this.width = width;
        this.height =  height;
        this.attention_span = 10;
    }

    public void spawn(Vector2 jerryPos)
    {
        // Instanz vom Singleton
        ArrayListCollection use = ArrayListCollection.getInstance();

        //get reference to self
       //var self = this;
        //add into customer list
        customer jerry = new customer(jerryPos,1,32,32);
        use.addCustomer(jerry);
    }


}
