import com.raylib.Jaylib;

/**
 * The type Karen.
 */
public class karen extends customer implements Icollision_object{

    /**
     * Instantiates a new Karen.
     *
     * @param x          the x
     * @param y          the y
     * @param sitt_x     the sitt x
     * @param sitt_y     the sitt y
     * @param speed      the speed
     * @param width      the width
     * @param height     the height
     * @param angerLimit the anger limit
     */
    public karen(float x,float y, float sitt_x,float sitt_y,float speed, float width, float height, int angerLimit)
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

    public boolean isAngery()
    {
        boolean isangery = Boolean.parseBoolean(null);
        //if anger is below anger limit increase
        if(this.angerLimit > this.anger)
        {
            //karens get angrier faster
            this.anger =this.anger+3;
            isangery = false;
        }
        //if anger level is reached
        if(this.angerLimit == this.anger)
        {
            isangery = true;
        }
        return isangery;
    }
}
