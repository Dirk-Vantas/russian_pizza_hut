import com.raylib.Jaylib.Vector2;
import java.util.ArrayList;
import static com.raylib.Jaylib.*;
import static com.raylib.Raylib.CloseWindow;
import static com.raylib.Raylib.EndDrawing;

public class GameUI {

    private Vector2 dudePos, dudeBody;
    private String dudeAngle;
    private Texture2D player, pizza, Wall, Wall_doorentrance, Wall_edge, Wall_edgekitdoorleft, Wall_edgekitdoorright, Wall_windowbottom, Wall_kitdoorleft, Wall_kitdoorright, Wall_kitdoortop, Wall_windowtop, Floor, Floor_dirty1, Floor_dirty2, Floor_doorkitbottom;
    private Texture2D Cashregister_bottomleft,Cashregister_bottommiddle,Cashregister_bottomright,Cashregister_extrabottom,Cashregister_extratop,Cashregister_topleft,Cashregister_topmiddle,Cashregister_topright;
    float screenHeight, screenWidth;
    GameController gameObj;

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
            //if customer is not dead
            if(p.customer_state != 3) {
                //manipulate walking direction controlled by AI class
                controller.customer_ai_tick(p);
            }
        }
    }

    private void drawPizzas(ArrayList<pizza>  pizzaArray)
    {
        for (pizza p:  pizzaArray){
            //DrawText("A:"+p.getAngle(),Math.round(p.getWorldPos().x()),Math.round(p.getWorldPos().y())-40,20,BLACK);
            //DrawCircleV(p.getWorldPos(), p.getSize(), RED);
            DrawTextureV(pizza, p.getWorldPos(), WHITE);
        }
    }

    private void drawCustomer(ArrayList<customer> customerArrayList)
    {
        //get instance of all arraylists
        for(customer p: customerArrayList)
        {
            if(p.customer_state != 3) {
                DrawRectangleV(p.getWorldPos(), new Vector2(p.height, p.width), BROWN);
                //DrawTextureV(pizza, p.getWorldPos(), WHITE);
            }
        }
    }

    private void drawMap(ArrayList<Tiles> TilesArrayList) {
        for(Tiles t: TilesArrayList)
        {
            DrawTextureV(t.getTexture(), t.getWorldPos(), WHITE);
        }
    }

    private void drawUI()
    {
        DrawText("POINTS:"+gameObj.round_points ,10,10,30,BLUE);
    }


    private void CreateWindow()
    {
        InitWindow(640, 480, "Demo");
        SetTargetFPS(60);
        ToggleFullscreen();
    }

    private void DrawEverything() {
        BeginDrawing();




        //DrawText(dudeAngle, 50, 100, 100, GREEN);
        //DrawText("Angle:"+calcAngle(dudePos),20,300,300,ORANGE);

        ArrayListCollection use = ArrayListCollection.getInstance();
        AI jerrybrain = AI.getInstance();

        //check collisions before moving objects
        //initialize collision manager obj
        collision_manager collision = new collision_manager();

        //check all coliders
        if(collision.getCollision(gameObj) != null)
        {
            System.out.println(collision.getCollision(gameObj).name+"/");
        }
        else
        {
            //System.out.println("nothing hit");
        }

        drawMap(use.getTilesList());

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

        //draw UI on top
        drawUI();
        //Ends the Drawing
        EndDrawing();
    }

    private void FillTileArray() {
        ArrayListCollection use = ArrayListCollection.getInstance();
        Texture2D texture = Wall;

        // Place tiles (Floor and Wall in automation)
        for (float i = 0; i < 480; i=i+32) {
            if (i == 32) {
                texture = Wall_edge;
            }

            if (i == 64) {
                texture = Floor;
            }

            for (float j = 0; j < 640; j=j+32) {
                Tiles tile = new Tiles(texture, j, i);
                use.addTile(tile);
            }
        }

        // customizeable Tiles (Kitchen Door)
        use.replaceTexture(9, Wall_kitdoorleft);
        use.replaceTexture(10, Wall_kitdoortop);
        use.replaceTexture(11, Wall_kitdoorright);
        use.replaceTexture(29, Wall_edgekitdoorleft);
        use.replaceTexture(30, Wall_doorentrance);
        use.replaceTexture(31, Wall_edgekitdoorright);
        use.replaceTexture(50, Floor_doorkitbottom);

        // wall window
        use.replaceTexture(15, Wall_windowtop);
        use.replaceTexture(35, Wall_windowbottom);


        // furniture
        use.makeTile(54, );

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
        Texture2D left = LoadTexture("Bilder/Charakter/Charakter_left.png");
        Texture2D right = LoadTexture("Bilder/Charakter/Charakter_right.png");

        // preload for wall
        Wall = LoadTexture("Bilder/Wall/Wall.png");
        Wall_doorentrance = LoadTexture("Bilder/Wall/Wall_doorentrance.png");
        Wall_edge = LoadTexture("Bilder/Wall/Wall_edge.png");
        Wall_edgekitdoorleft = LoadTexture("Bilder/Wall/Wall_edgekitdoorleft.png");
        Wall_edgekitdoorright = LoadTexture("Bilder/Wall/Wall_edgekitdoorright.png");
        Wall_windowbottom = LoadTexture("Bilder/Wall/Wall_edgewindowbottom.png");
        Wall_kitdoorleft = LoadTexture("Bilder/Wall/Wall_kitdoorleft.png");
        Wall_kitdoorright = LoadTexture("Bilder/Wall/Wall_kitdoorright.png");
        Wall_kitdoortop = LoadTexture("Bilder/Wall/Wall_kitdoortop.png");
        Wall_windowtop = LoadTexture("Bilder/Wall/Wall_windowtop.png");

        //preload cash register
        Cashregister_bottomleft = LoadTexture("Bilder/Furniture/Cashregister_bottomleft.png");
        Cashregister_bottommiddle = LoadTexture("Bilder/Furniture/Cashregister_bottommiddle.png");
        Cashregister_bottomright = LoadTexture("Bilder/Furniture/Cashregister_bottomright.png");
        Cashregister_extrabottom = LoadTexture("Bilder/Furniture/Cashregister_extrabottom.png");
        Cashregister_extratop = LoadTexture("Bilder/Furniture/Cashregister_extratop.png");
        Cashregister_topleft = LoadTexture("Bilder/Furniture/Cashregister_topleft.png");
        Cashregister_topmiddle = LoadTexture("Bilder/Furniture/Cashregister_topmiddle.png");
        Cashregister_topright = LoadTexture("Bilder/Furniture/Cashregister_topright.png");

        // preload for floor
        Floor = LoadTexture("Bilder/Floor/Floor.png");
        Floor_dirty1 = LoadTexture("Bilder/Floor/Floor_dirty1.png");
        Floor_dirty2 = LoadTexture("Bilder/Floor/Floor_dirty2.png");
        Floor_doorkitbottom = LoadTexture("Bilder/Floor/Floor_doorkitbottom.png");


        //initialize mouse vector
        Vector2 mousePos = new Vector2(GetMouseX(),GetMouseY());


        // Instanz vom Singleton
        ArrayListCollection use = ArrayListCollection.getInstance();

        gameObj = new GameController(20);

        customer jerry = new customer(30, 30,300,300, 1,32,32,300);
        use.addCustomer(jerry);

        customer brenda = new karen(30, 30,300,300, 1,32,32,300);
        use.addCustomer(brenda);




        FillTileArray();




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
                player = right;
                dudePos.x(Posx + 2f);
            }
            if (IsKeyDown(KEY_A))
            {
                dudeAngle = "right";
                player = left;
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


                //customer berry = new customer(mousePos.x(),mousePos.y(),1,32,32,3,"berry");
                //use.addCustomer(berry);



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
