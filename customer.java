import com.raylib.Jaylib.Vector2;
import com.raylib.Raylib;
import com.raylib.Raylib.Rectangle;

import java.util.ArrayList;


/**
 * The type Customer.
 */
public class customer implements Icollision_object{

    /**
     * The World pos.
     */
    Vector2 worldPos;
    /**
     * The Collision body.
     */
    Rectangle collision_body;

    private Raylib.Texture2D texture;

    /**
     * The Speed.
     */
    float speed;
    /**
     * The Width.
     */
    float width;
    /**
     * The Height.
     */
    float height;
    /**
     * The Anger.
     */
    int anger;
    /**
     * The Anger limit.
     */
    int angerLimit;
    /**
     * The Goal.
     */
    Vector2 goal;
    /**
     * The Sitting location.
     */
    Vector2 sitting_location;
    /**
     * The Name.
     */
    String name;
    /**
     * The Distance to cover.
     */
    double distance_to_cover;
    /**
     * The Steps last taken.
     */
    double steps_last_taken;
    /**
     * The Customer state.
     */
    int customer_state;
    /**
     * The Chair.
     */
    Tiles chair;


    /**
     * Instantiates a new Customer.
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

    /**
     * Set chair.
     *
     * @param chair the chair
     */
    public void setChair(Tiles chair){this.chair = chair;}

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

    /**
     * Serve.
     *
     * @param door_position the door position
     */
//gets called when customer has been server in time and needs to leave restaurant and be removed from arraylist
    public void serve(Vector2 door_position){
        //customer wants to leave
        this.customer_state = 2;
        this.goal = door_position;
        this.distance_to_cover = pizza_math.getVecDistance(this.worldPos,this.goal);
        this.steps_last_taken = 0;
        //add logic to add points onto the scoreboard
        //get singelton game object add points to the scoreboard property
        // -->
        // -->
    }

    /**
     * Angery leave.
     *
     * @param door_position the door position
     */
//make customer leave when angry
    public void angery_leave(Vector2 door_position){
        //change goal to door but dont add no points to the scoreboard
        this.goal = door_position;
    }

    /**
     * Death.
     *
     * @param p the p
     */
    public void death(customer p)
    {
        //if customer dies he need to be removed from array so he does not get collsion and stops being rendered
        ArrayListCollection use = ArrayListCollection.getInstance();
        use.getCustomerList().remove(p);
    }


    /**
     * Gets world pos.
     *
     * @return the world pos
     */
    public Vector2 getWorldPos()
    {
        return this.worldPos;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public float getWidth()
    {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public float getHeight()
    {
        return this.height;
    }


    /**
     * Gets texture 2 d.
     *
     * @return the texture 2 d
     */
    public Raylib.Texture2D getTexture2D() {
        return this.texture;
    }

    /**
     * Sets texture 2 d.
     *
     * @param texture2D the texture 2 d
     */
    public void setTexture2D(Raylib.Texture2D texture2D) {
        this.texture = texture2D;
    }
}
