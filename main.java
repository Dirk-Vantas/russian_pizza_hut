import static com.raylib.Jaylib.*;
import static com.raylib.Jaylib.Vector2;
import java.util.ArrayList;
//import java.util.Scanner;


//instantiate mouse position



public class main {





    public static void draw(float dudePosX, float dudePosY)
    {
        int posY = GetMouseY();
        int posX = GetMouseX();
        int screenHeight = GetScreenHeight();
        int screenWidth = GetScreenWidth();


        DrawText("y:"+posY+"x:"+posX, 50, 70, 50, GREEN);
        //DrawText("Width:"+screenWidth+"Height:"+screenHeight, 20, 50, 20, GREEN);


        //x is width. y is height
        DrawLine(screenWidth/2,screenHeight/2, Math.round(dudePosX), Math.round(dudePosY), BLACK);
        //second line
        DrawLine( Math.round(dudePosX), Math.round(dudePosY),posX,posY, BLACK);


        ClearBackground(WHITE);
    }

    public static double calcAngle()
    {
       double x = GetScreenWidth()/2;
       double y = GetScreenHeight()/2;

       double mouseX = GetMouseX();
       double mouseY = GetMouseY();

       //double Angle = Math.atan((x-mouseX)/(y-mouseY));
       double Angle = Math.atan2(y-mouseY,x-mouseX) * 180 / Math.PI;;
       if(Angle < 0)
       {
           Angle = Angle + 360;
       }
       //double degrees = (Angle + 360) % 360;
       return Angle;
    }

    public static void moveAlongVector(pizza pie)
    {
        //increase ttl so steps will be taken
        pie.setTtl(0.5f);
        double x = getX(pie.getTtl(), pie.getAngle());
        double y = getY(pie.getTtl(), pie.getAngle());

        double beforeX = pie.getWorldPos().x();
        double beforeY = pie.getWorldPos().y();
        float yF = (float)y;
        float xF = (float)x;



            pie.getWorldPos().y(pie.getWorldPos().y() + yF);




            pie.getWorldPos().x(pie.getWorldPos().x() + xF);
        double afterX = pie.getWorldPos().x();
        double afterY = pie.getWorldPos().y();


    }

    public static double getX(double length_R, double Angle)
    {
        double X = length_R * Math.cos(Math.toRadians(Angle));

        //DrawText("X:"+X,50,50,20,BLACK);
        return X;
    }

    public static double getY(double length_R, double Angle)
    {
        double Y = length_R * Math.sin(Math.toRadians(Angle));

        return Y;
    }

    public static void movePizzas(ArrayList<pizza>  pizzaArray)
    {
        for (pizza p:  pizzaArray){
            /*
            switch (p.getDirection())
            {
                case "up": p.getWorldPos().y(p.getWorldPos().y() - 2.0f);
                    break;
                case "down": p.getWorldPos().y(p.getWorldPos().y() + 2.0f);
                    break;
                case "left": p.getWorldPos().x(p.getWorldPos().x() + 2.0f);
                    break;
                case "right": p.getWorldPos().x(p.getWorldPos().x() - 2.0f);
                    break;

            }
            */
            moveAlongVector(p);
        }
    }

    public static void drawPizzas(ArrayList<pizza>  pizzaArray)
    {
        for (pizza p:  pizzaArray){
            DrawText("A:"+p.getAngle(),Math.round(p.getWorldPos().x()),Math.round(p.getWorldPos().y())-40,20,BLACK);
            DrawCircleV(p.getWorldPos(), p.getSize(), RED);

        }
    }


    public static void main(String args[]) {
        InitWindow(800, 450, "Demo");
        SetTargetFPS(60);

        //create array of pizzas to be shot and drawn
        ArrayList<pizza> pizzaArray = new ArrayList<pizza>();


        float screenHeight = GetScreenHeight();
        float screenWidth = GetScreenWidth();
        String dudeAngle = "up";

        //initialize both dude position and the size of his body
        Vector2 dudePos = new Vector2( screenWidth/2, screenHeight/2);
        Vector2 dudeBody = new Vector2( 50,50);

        //initialize mouse vector
        Vector2 mousePos = new Vector2(GetMouseX(),GetMouseY());

        System.out.println("Text is: "+GetMousePosition());
        while (!WindowShouldClose()) {
            //update dude position


            //get current dude pos
            float Posx = dudePos.x();
            float Posy = dudePos.y();

            //overide mouse position each cycle
            mousePos.x(GetMouseX());
            mousePos.y(GetMouseY());

            if (IsKeyDown(KEY_D)) {
                dudeAngle = "left";
                dudePos.x(Posx + 2f);
            }
            if (IsKeyDown(KEY_A))
            {
                dudeAngle = "right";
                dudePos.x(Posx - 2.0f);
            }

            if (IsKeyDown(KEY_W))
            {
                dudeAngle = "up";
                dudePos.y(Posy - 2.0f);
            }
            if (IsKeyDown(KEY_S))
            {
                dudeAngle = "down";
                dudePos.y(Posy + 2.0f);
            }
            if (IsMouseButtonPressed(MOUSE_LEFT_BUTTON))
            {
                //pizza pizzaObj = new pizza(50,50, GetMouseX(),GetMouseY());
                pizza pizzaObj = new pizza(20,50, dudePos.x(),dudePos.y(), calcAngle());
                pizzaArray.add(pizzaObj);
            }
            if (IsKeyPressed(KEY_T))
            {
                var y = 4;
            }
            DrawText(dudeAngle, 50, 100, 100, GREEN);

            //first move all pizzas
            movePizzas(pizzaArray);

            //then draw all pizzas
            drawPizzas(pizzaArray);

            BeginDrawing();
            //getAngle();
            //draw call with position of the main dude
            draw(dudePos.x(), dudePos.y());
            DrawRectangleV(dudePos,dudeBody, MAGENTA);
            EndDrawing();

        }
        CloseWindow();
    }


}
