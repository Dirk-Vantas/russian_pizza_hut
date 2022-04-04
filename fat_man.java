import com.raylib.Jaylib;

public class fat_man extends customer implements Icollision_object{

    int pizzas_served;

    public fat_man(float x,float y, float sitt_x,float sitt_y,float speed, float width, float height, int angerLimit)
    {
        super(x,y,sitt_x,sitt_y,speed,width,height,angerLimit);
        //where will customer spawn
        this.worldPos = new Jaylib.Vector2(x,y);
        //where customer will sit once spawned
        this.sitting_location = new Jaylib.Vector2(sitt_x,sitt_y);
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


    public void serve(Jaylib.Vector2 door_position)
    {
        this.pizzas_served ++;
        if(this.pizzas_served < 3)
        {
            //reset anger if customer is being served but he wants more
            this.anger = 0;
        }
        else
        {
            //customer wants to leave now
            this.customer_state =2;
            this.goal = door_position;
            this.distance_to_cover = pizza_math.getVecDistance(this.worldPos,this.goal);
            this.steps_last_taken = 0;
        }
    }
}
