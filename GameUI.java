import com.raylib.Jaylib.*;
import com.raylib.Jaylib.Vector2;

import java.util.ArrayList;

import static com.raylib.Jaylib.*;
import static com.raylib.Raylib.*;
import static com.raylib.Raylib.CloseWindow;
import static com.raylib.Raylib.DrawRectangleV;
import static com.raylib.Raylib.EndDrawing;

public class GameUI {

    private Vector2 dudePos, dudeBody;
    private String dudeAngle;

    private void draw(float dudePosX, float dudePosY)
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

    private double calcAngle(Vector2 dudePos)
    {
        double x = dudePos.x();
        double y = dudePos.y();

        double mouseX = GetMouseX();
        double mouseY = GetMouseY();

        //calculate in what angle player is looking
        double Angle = Math.atan2(y-mouseY,x-mouseX) * 180 / Math.PI;
        if(Angle < 0)
        {
            //if angle lower than 0 add 360 to fix for full circle solution
            Angle = Angle + 360;
        }
        //double degrees = (Angle + 360) % 360;
        return Angle;
    }

    private void moveAlongVector(pizza pie)
    {
        //increase ttl so steps will be taken -- higher value = faster movement
        pie.setTtl(0.5f);
        double x = getX(pie.getTtl(), pie.getAngle());
        double y = getY(pie.getTtl(), pie.getAngle());

        //convert to float
        float yF = (float)y;
        float xF = (float)x;

        //add the calculated tangents to world positions
        pie.getWorldPos().y(pie.getWorldPos().y() - yF);
        pie.getWorldPos().x(pie.getWorldPos().x() - xF);


    }

    private static double getX(double length_R, double Angle)
    {
        return length_R * Math.cos(Math.toRadians(Angle));
    }

    private double getY(double length_R, double Angle)
    {
        return length_R * Math.sin(Math.toRadians(Angle));
    }

    private void movePizzas(ArrayList<pizza> pizzaArray)
    {
        for (pizza p:  pizzaArray){
            /*
            //static horizontal and vertical movement
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

    private static void CreateWindow()
    {
        InitWindow(1920, 1080, "Demo");
        SetTargetFPS(60);
        ToggleFullscreen();
    }

    private void DrawEverything() {
        DrawText(dudeAngle, 50, 100, 100, GREEN);
        DrawText("Angle:"+calcAngle(dudePos),20,300,300,ORANGE);

        ArrayListCollection use = ArrayListCollection.getInstance();
        //first move all pizzas
        movePizzas(use.getPizzaList());

        //then draw all pizzas
        drawPizzas(use.getPizzaList());

        BeginDrawing();
        //getAngle();
        //draw call with position of the main dude
        draw(dudePos.x(), dudePos.y());
        DrawRectangleV(dudePos,dudeBody, MAGENTA);
        EndDrawing();
    }

    public GameUI() {

        // Main setup for Window
        CreateWindow();

        //create array of pizzas to be shot and drawn
        //ArrayList<pizza> pizzaArray = new ArrayList<>();


        float screenHeight = GetScreenHeight();
        float screenWidth = GetScreenWidth();
        dudeAngle = "up";

        //initialize both dude position and the size of his body
        dudePos = new Vector2( screenWidth/2, screenHeight/2);
        dudeBody = new Vector2( 50,50);

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
                // Instanz vom Singleton
                ArrayListCollection use = ArrayListCollection.getInstance();

                //pizza pizzaObj = new pizza(50,50, GetMouseX(),GetMouseY());
                pizza pizzaObj = new pizza(20,50, dudePos.x(),dudePos.y(), calcAngle(dudePos));


                use.addPizza(pizzaObj);
            }
            if (IsKeyPressed(KEY_T))
            {
                int y = 4;
            }

            DrawEverything();

            //DrawText(dudeAngle, 50, 100, 100, GREEN);
            //DrawText("Angle:"+calcAngle(dudePos),20,300,300,ORANGE);

            //ArrayListCollection use = ArrayListCollection.getInstance();
            //first move all pizzas
            //movePizzas(use.getPizzaList());

            //then draw all pizzas
            //drawPizzas(use.getPizzaList());

            //BeginDrawing();
            //getAngle();
            //draw call with position of the main dude
            //draw(dudePos.x(), dudePos.y());
            //DrawRectangleV(dudePos,dudeBody, MAGENTA);
            //EndDrawing();

        }
        CloseWindow();
    }
}
