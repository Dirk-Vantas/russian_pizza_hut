import com.raylib.Jaylib;

import java.util.ArrayList;
import java.util.Random;

import static com.raylib.Jaylib.BROWN;
import static com.raylib.Jaylib.ORANGE;
import static com.raylib.Raylib.*;


public class AI {

    int max_count;

    // Singleton Session Pattern begin
    private static AI single_instance = null;

    public static AI getInstance() {
        if (single_instance == null) {
            single_instance = new AI();
        }
        return single_instance;
    }
    // Singleton Session Pattern end

    //this class will be used as an AI controller
    public AI() {

    }

    public void customer_ai_tick(customer p)
    {

        DrawText("cstm state:"+p.customer_state ,Math.round(p.worldPos.x()),Math.round(p.worldPos.y())-30,20,ORANGE);
        DrawText("cstm steps taken:"+p.steps_last_taken ,Math.round(p.worldPos.x()),Math.round(p.worldPos.y())-40,20,ORANGE);
        DrawText("cstm way to go"+p.distance_to_cover ,Math.round(p.worldPos.x()),Math.round(p.worldPos.y())-50,20,ORANGE);
        DrawText("distance"+pizza_math.getVecDistance(p.worldPos,p.goal) ,300,300,20,ORANGE);
        System.out.println(pizza_math.getVecDistance(p.worldPos,p.goal));

        //if customer is walking into restaurant
        if(p.customer_state == 0)
        {
            if(pizza_math.getVecDistance(p.worldPos,p.goal)<1)
            {
                //if customer reached goal sitt him down
                p.customer_state = 1;
            }

            //else increase his tangents
            else {
                //increase steps taken so character moves forward
                p.steps_last_taken = p.steps_last_taken + 0.1;
                //get angle from start to goal position
                double angle = pizza_math.calcAngle(p.worldPos, p.goal);

                double x = pizza_math.getX(p.steps_last_taken, angle);
                double y = pizza_math.getY(p.steps_last_taken, angle);

                //convert to float
                float yF = (float) y;
                float xF = (float) x;


                //add the calculated tangents to world positions
                p.getWorldPos().y(p.getWorldPos().y() - yF);
                p.getWorldPos().x(p.getWorldPos().x() - xF);
            }
        }
        //if customer is sitting check if he is getting angry
        if(p.customer_state == 1)
        {
            //cycle once through anger level management
            boolean angery = p.isAngery();
            if(angery)
            {
                //temporary door location is at 30x 30y
                p.angery_leave(new Jaylib.Vector2(30,30));
                //change customer state to leaving 2
                //and erase his last steps taken
                p.customer_state = 2;
                p.steps_last_taken = 0;
            }
        }
        //if customer is leaving restaurant
        if(p.customer_state == 2)
        {

                //increase steps taken so character moves forward
                p.steps_last_taken = p.steps_last_taken + 0.1;
                //get angle from start to goal position
                double angle = pizza_math.calcAngle(p.worldPos, p.goal);

                double x = pizza_math.getX(p.steps_last_taken, angle);
                double y = pizza_math.getY(p.steps_last_taken, angle);

                //convert to float
                float yF = (float) y;
                float xF = (float) x;

            if(pizza_math.getVecDistance(p.worldPos,p.goal)<1)
            {
                //if customer is back at door despawn
                p.customer_state =3;
            }

                //add the calculated tangents to world positions
                p.getWorldPos().y(p.getWorldPos().y() - yF);
                p.getWorldPos().x(p.getWorldPos().x() - xF);
        }

        if(p.customer_state == 3)
        {
            //if customer dies he need to be removed from array so he does not get collsion and stops being rendered
            ArrayListCollection use = ArrayListCollection.getInstance();

            ArrayList<customer> customers = use.getCustomerList();
            //use.getCustomerList().remove(p);
            //use.getPizzaList().remove(use.getCustomerList().indexOf(p));

        }
    }
/*
    public void ai_tick_random(customer p) {

        //select how many options the tick switches between 0 and 3
        int ideas = 4;
        int attention = 30;


            DrawText("thought:"+p.thought ,Math.round(p.worldPos.x()),Math.round(p.worldPos.y())-100,20,ORANGE);
            DrawText("Attention 1:"+p.attention_span ,Math.round(p.worldPos.x()),Math.round(p.worldPos.y())-50,20,ORANGE);
        DrawText(p.name ,Math.round(p.worldPos.x()),Math.round(p.worldPos.y())-30,20,ORANGE);


            //simple collsion detection
            if(p.worldPos.x() < 0)
            {
                p.thought = 1;
                p.attention_span = p.attention_span + 30;
            }

            if(p.worldPos.y() < 0)
            {
                p.thought = 3;
                p.attention_span = p.attention_span + 30;
            }

            if(p.worldPos.x() > GetScreenWidth())
            {
                p.thought = 0;
                p.attention_span = p.attention_span + 30;
            }

            if(p.worldPos.y() > GetScreenHeight())
            {
                p.thought = 2;
                p.attention_span = p.attention_span + 30;
            }

            if (p.attention_span < 0) {
                Random rand = new Random();
                //if attention span has reduced to 0 customer will think up a new idea on where to go
                p.thought = rand.nextInt(ideas);
                //and reset is attention
                p.attention_span = rand.nextInt(attention);

            }
            //if attention span is still going thought will be held and customer will keep walking in the same direction
            if (p.attention_span > 0) {
                switch (p.thought) {
                    //move customer left
                    case 0:
                        p.worldPos.x(p.worldPos.x() - 3f);
                        break;
                    case 1:
                        p.worldPos.x(p.worldPos.x() + 3f);
                        break;
                    case 2:
                        p.worldPos.y(p.worldPos.y() - 3f);
                        break;
                    case 3:
                        p.worldPos.y(p.worldPos.y() + 3f);
                        break;
                }

            }
            p.attention_span = p.attention_span -1;
        }
*/

}
