import com.raylib.Jaylib.Vector2;
import com.raylib.Raylib;
import com.raylib.Raylib.Rectangle;

import java.util.ArrayList;


public class customer implements Icollision_object{

    Vector2 worldPos;
    Rectangle collision_body;
    float speed;
    float width;
    float height;
    int angerLimit;
    //attention span in seconds
    int attention_span;
    int thought;
    String name;


    public customer(float x,float y, float speed, float width, float height, int thought,String name)
    {
        this.worldPos = new Vector2(x,y);
        this.speed = speed;
        this.width = width;
        this.height =  height;
        this.attention_span = 10;
        this.thought = thought;
        this.name= name;
    }

    public Vector2 getWorldPos()
    {
        return this.worldPos;
    }

    public float getWidth()
    {
        return this.width;
    }
    public float getHeight()
    {
        return this.height;
    }



}
