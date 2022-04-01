import static com.raylib.Jaylib.*;
import static com.raylib.Raylib.Rectangle;
import com.raylib.Jaylib.Vector2;
import java.util.ArrayList;
import java.util.Random;
import static com.raylib.Jaylib.*;
import static com.raylib.Raylib.CloseWindow;
import static com.raylib.Raylib.EndDrawing;


public class collision_manager {
    //this class will manage all collission detection logic

    public customer getCollision()
    {
        // Instanz vom Singleton
        ArrayListCollection use = ArrayListCollection.getInstance();

        //initialize customer obj to be returned as null
        customer hit =null;

        for(customer customer : use.getCustomerList())
        {
            for(pizza pie: use.getPizzaList())
            {
                Rectangle rect = new Rectangle();
                rect.height(customer.getHeight());
                rect.width(customer.getWidth());
                rect.x(customer.getWorldPos().x());
                rect.y(customer.getWorldPos().y());

                if(CheckCollisionCircleRec(pie.getWorldPos(), pie.getSize(), rect) == true)
                {
                    hit = customer;
                    //if collision is found break out of the for loop
                    break;
                }
                else
                {
                    hit = null;
                }
            }
            if(hit != null)
            {
                //if collsion was found earlier break out of nested for loop aswell
                break;
            }
        }
        return hit;
    }





}
