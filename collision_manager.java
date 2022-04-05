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

    public customer getCollision(GameController game)
    {
        // Instanz vom Singleton
        ArrayListCollection use = ArrayListCollection.getInstance();

        //initialize customer obj to be returned as null
        customer hit =null;

        for(customer customer : use.getCustomerList()) {
            if (customer.customer_state != 3) {
                for (pizza pie : use.getPizzaList()) {
                    if(pie.getVisibility() == true) {
                        Rectangle rect = new Rectangle();
                        rect.height(customer.getHeight());
                        rect.width(customer.getWidth());
                        rect.x(customer.getWorldPos().x());
                        rect.y(customer.getWorldPos().y());

                        if (CheckCollisionCircleRec(pie.getWorldPos(), pie.getSize(), rect) == true) {
                            hit = customer;
                            //after pizza is hit remove from renderer
                            pie.setVisibility(false);
                            if (customer.customer_state == 1) {
                                //if customer is sitting down and waiting for an order he will be serverd
                                customer.serve(new Vector2(30, 30));
                                game.addPoint();

                            } else {
                                //if customer was hit and no pizza was requested Ie still walking or was already serverd deduct 1 point
                                game.subtracktPoints();
                            }
                            //if collision is found break out of the for loop
                            break;
                        } else {
                            hit = null;
                        }
                    }
                }
                if (hit != null) {
                    //if collsion was found earlier break out of nested for loop aswell
                    break;
                }
            }
        }
        return hit;
    }





}
