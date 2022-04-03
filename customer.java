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
    int anger;
    int angerLimit;
    Vector2 goal;
    Vector2 sitting_location;
    String name;
    double distance_to_cover;
    double steps_last_taken;
    int customer_state;


    public customer(float x,float y, float sitt_x,float sitt_y,float speed, float width, float height, int angerLimit)
    {
        //where will customer spawn
        this.worldPos = new Vector2(x,y);
        //where customer will sit once spawned
        this.sitting_location = new Vector2(sitt_x,sitt_y);
        //how fast customer can move
        this.speed = speed;
        //how long until customer gets angry and leaves
        this.angerLimit = angerLimit;
        this.width = width;
        this.height = height;
        //give random name may be removed not important
        this.name= name;
        //number of steps last taken before switching goal location on hwere to move next, used for vector math
        this.steps_last_taken = 0;
        //customer state indicates current statue of customer, if he is walking or sitting atm
        //o is walking into restaurant
        //1 is sitting
        //2 is leaving restaurant
        this.customer_state =0;

        //if this object gets created spawn it
        spawn();
    }
    public boolean isAngery()
    {
        boolean isangery = Boolean.parseBoolean(null);
        //if anger is below anger limit increase
        if(this.angerLimit > this.anger)
        {
            this.anger ++;
            isangery = false;
        }
        //if anger level is reached
        if(this.angerLimit == this.anger)
        {
            isangery = true;
        }
        return isangery;
    }




    //needs to be called when customer is spawned into retaurant by game controller
    public void spawn(){
        //after being spawned customer wants to walk towards his place to sit
        this.goal = sitting_location;
        this.distance_to_cover = pizza_math.getVecDistance(this.worldPos,this.sitting_location);
    }

    //gets called when customer has been server in time and needs to leave restaurant and be removed from arraylist
    public void serve(Vector2 door_position){
        this.goal = door_position;
        this.distance_to_cover = pizza_math.getVecDistance(this.worldPos,this.goal);
        //add logic to add points onto the scoreboard
        //get singelton game object add points to the scoreboard property
        // -->
        // -->
    }

    //make customer leave when angry
    public void angery_leave(Vector2 door_position){
        //change goal to door but dont add no points to the scoreboard
        this.goal = door_position;
    }

    public void death(customer p)
    {
        //if customer dies he need to be removed from array so he does not get collsion and stops being rendered
        ArrayListCollection use = ArrayListCollection.getInstance();
        use.getCustomerList().remove(p);
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
