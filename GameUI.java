import com.raylib.Jaylib.Vector2;
import java.util.ArrayList;
import java.util.Random;

import static com.raylib.Jaylib.*;
import static com.raylib.Raylib.CloseWindow;
import static com.raylib.Raylib.EndDrawing;

public class GameUI {

    private Vector2 dudePos, dudeBody;
    private String dudeAngle;
    Texture2D player, pizza;
    float screenHeight, screenWidth;

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
            moveAlongVector(p);
        }
    }

    private void moveCustomerAlong(customer p)
    {

    }


    private void moveCustomer(ArrayList<customer> customerArrayList,AI controller)
    {
        for(customer p : customerArrayList)
        {
            //manipulate walking direction controlled by AI class
            controller.ai_tick_random(p);
        }
    }

    private void drawPizzas(ArrayList<pizza>  pizzaArray)
    {
        for (pizza p:  pizzaArray){
            DrawText("A:"+p.getAngle(),Math.round(p.getWorldPos().x()),Math.round(p.getWorldPos().y())-40,20,BLACK);
            //DrawCircleV(p.getWorldPos(), p.getSize(), RED);
            DrawTextureV(pizza, p.getWorldPos(), WHITE);
        }
    }

    private void drawCustomer(ArrayList<customer> customerArrayList)
    {
        //get instance of all arraylists
        ArrayListCollection use = ArrayListCollection.getInstance();
        for(customer p: customerArrayList)
        {

            DrawRectangleV(p.getWorldPos(),new Vector2(p.height,p.width),BROWN);
            //DrawTextureV(pizza, p.getWorldPos(), WHITE);
        }
    }

    private void CreateWindow()
    {
        InitWindow(800, 600, "Demo");
        SetTargetFPS(60);
        //ToggleFullscreen();
    }

    private void DrawEverything() {
        BeginDrawing();
        DrawText(dudeAngle, 50, 100, 100, GREEN);
        DrawText("Angle:"+calcAngle(dudePos),20,300,300,ORANGE);

        ArrayListCollection use = ArrayListCollection.getInstance();
        AI jerrybrain = AI.getInstance();


        //first move all pizzas
        movePizzas(use.getPizzaList());

        //then draw all pizzas
        drawPizzas(use.getPizzaList());

        //pass customer list and AI controller obj into move customer method
        moveCustomer(use.getCustomerList(),jerrybrain);
        //draw all customers currently spawned
        drawCustomer(use.getCustomerList());


        //getAngle();
        //draw call with position of the main dude
        draw(dudePos.x(), dudePos.y());
        //DrawRectangleV(dudePos,dudeBody, MAGENTA);

        //Draw the Character
        DrawTextureV(player, dudePos, WHITE);

        //Ends the Drawing
        EndDrawing();
    }

    public GameUI() {

        // Main setup for Window
        CreateWindow();

        //create array of pizzas to be shot and drawn
        //ArrayList<pizza> pizzaArray = new ArrayList<>();


        screenHeight = GetScreenHeight();
        screenWidth = GetScreenWidth();
        dudeAngle = "up";

        //initialize both dude position and the size of his body
        dudePos = new Vector2( screenWidth/2, screenHeight/2);
        dudeBody = new Vector2( 50,50);

        Vector2 jerryPos= new Vector2(50,50);

        // Load Textures
        player = LoadTexture("Bilder/Charakter/Charakter.png");
        pizza = LoadTexture("Bilder/Projectile/Projectile_pizza.png");

        // preload for behind back left right
        Texture2D front = LoadTexture("Bilder/Charakter/Charakter.png");
        Texture2D behind = LoadTexture("Bilder/Charakter/Charakter_behind.png");

        //initialize mouse vector
        Vector2 mousePos = new Vector2(GetMouseX(),GetMouseY());




        // Instanz vom Singleton
        ArrayListCollection use = ArrayListCollection.getInstance();

        //initilize test customer
        /*
        for (int step = 0; step < 1; step++) {
            customer jerry = new customer(jerryPos,1,32,32);
            use.addCustomer(jerry);
        }
        */
        /*
        customer jerry = new customer(jerryPos,1,32,32,2,"jerry");
        use.addCustomer(jerry);

        customer berry = new customer(jerryPos,1,32,32,3,"berry");
        use.addCustomer(berry);
*/




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
                player = behind;
                dudePos.y(Posy - 2.0f);
            }
            if (IsKeyDown(KEY_S))
            {
                dudeAngle = "down";
                player = front;
                dudePos.y(Posy + 2.0f);
            }
            if (IsMouseButtonPressed(MOUSE_LEFT_BUTTON))
            {
                // Instanz vom Singleton
                use = ArrayListCollection.getInstance();

                //pizza pizzaObj = new pizza(50,50, GetMouseX(),GetMouseY());
                pizza pizzaObj = new pizza(20,50, dudePos.x(),dudePos.y(), calcAngle(dudePos));


                customer berry = new customer(mousePos.x(),mousePos.y(),1,32,32,3,"berry");
                use.addCustomer(berry);



                use.addPizza(pizzaObj);
            }
            if (IsKeyPressed(KEY_T))
            {
                int y = 4;
            }

            DrawEverything();

        }
        CloseWindow();
    }
}
