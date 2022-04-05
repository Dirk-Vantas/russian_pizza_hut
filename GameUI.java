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
    private Texture2D Chair_left, Chair_right, Table_begin, Table_middle, Table_end;
    private Texture2D Floor_door, Wall_doormiddle, Wall_doortop, Wall_entrance, Wall_entrancetop;
    private Texture2D Fatman, Fatman_behind, Fatman_left, Fatman_right, Karen, Karen_behind, Karen_left, Karen_right, Normalman, Normalman_behind, Normalman_left, Normalman_right;
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
            //if angle lower than 0 add 360 to fix for full circle soGaalution
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
            if(p.getVisibility() == true) {
                //DrawText("A:"+p.getAngle(),Math.round(p.getWorldPos().x()),Math.round(p.getWorldPos().y())-40,20,BLACK);
                //DrawCircleV(p.getWorldPos(), p.getSize(), RED);
                DrawTextureV(pizza, p.getWorldPos(), WHITE);
            }
        }
    }

    private void drawCustomer(ArrayList<customer> customerArrayList)
    {
        //get instance of all arraylists
        for(customer p: customerArrayList)
        {
            if(p.customer_state != 3) {

                if (p.customer_state == 2) {
                    p.setTexture2D();
                }
                DrawTextureV(p.getTexture2D(), new Vector2(p.height, p.width), WHITE);
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
        //ToggleFullscreen();
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
            //System.out.println(collision.getCollision(gameObj).name+"/");
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

        gameObj.spawnNPC();

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

        DrawFurniture(use.getFurnitureArray());

        //draw UI on top
        drawUI();
        //Ends the Drawing
        EndDrawing();
    }

    private void DrawFurniture(ArrayList<Tiles> tiles) {
        for (Tiles f : tiles) {
            DrawTextureV(f.getTexture(), f.getWorldPos(), WHITE);
        }
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

        // replace some floors
        use.replaceTexture(42 ,Floor_dirty1);
        use.replaceTexture(93 ,Floor_dirty2);
        use.replaceTexture(299 ,Floor_dirty1);
        use.replaceTexture( 147,Floor_dirty2);
        use.replaceTexture( 262,Floor_dirty1);
        use.replaceTexture( 289,Floor_dirty2);

        // customizeable Tiles (Kitchen Door)
        use.replaceTexture(9, Wall_kitdoorleft);
        use.replaceTexture(10, Wall_kitdoortop);
        use.replaceTexture(11, Wall_kitdoorright);
        use.replaceTexture(29, Wall_edgekitdoorleft);
        use.replaceTexture(30, Wall_doorentrance);
        use.replaceTexture(31, Wall_edgekitdoorright);
        use.replaceTexture(50, Floor_doorkitbottom);

        // customizeable Tiles (Entrance)
        use.replaceTexture(5, Wall_entrancetop);
        use.replaceTexture(6, Wall_doortop);
        use.replaceTexture(25, Wall_entrance);
        use.replaceTexture(26, Wall_doormiddle);
        use.replaceTexture(45, Floor_doorkitbottom);
        use.replaceTexture(46, Floor_door);

        // wall window
        use.replaceTexture(15, Wall_windowtop);
        use.replaceTexture(35, Wall_windowbottom);


        // furniture ( chair and tables )
        use.makeTile(54, Cashregister_topleft);
        use.makeTile(55, Cashregister_extratop);
        use.makeTile(56, Cashregister_topmiddle);
        use.makeTile(57, Cashregister_topmiddle);
        use.makeTile(58, Cashregister_topmiddle);
        use.makeTile(59, Cashregister_topright);

        use.makeTile(74, Cashregister_bottomleft);
        use.makeTile(75, Cashregister_extrabottom);
        use.makeTile(76, Cashregister_bottommiddle);
        use.makeTile(77, Cashregister_bottommiddle);
        use.makeTile(78, Cashregister_bottommiddle);
        use.makeTile(79, Cashregister_bottomright);

        use.makeTile(102, Chair_left);
        use.addChair(102);
        use.makeTile(103, Table_end);
        use.makeTile(104, Chair_right);
        use.addChair(104);
        use.makeTile(123, Table_middle);

        use.makeTile(142, Chair_left);
        use.addChair(142);
        use.makeTile(143, Table_begin);
        use.makeTile(144, Chair_right);
        use.addChair(144);

        use.makeTile(202, Chair_left);
        use.addChair(202);
        use.makeTile(203, Table_end);
        use.makeTile(204, Chair_right);
        use.addChair(204);
        use.makeTile(223, Table_middle);
        use.makeTile(242, Chair_left);
        use.addChair(242);
        use.makeTile(243, Table_begin);
        use.makeTile(244, Chair_right);
        use.addChair(244);

        use.makeTile(209, Chair_left);
        use.addChair(209);
        use.makeTile(210, Table_end);
        use.makeTile(211, Chair_right);
        use.addChair(211);
        use.makeTile(230, Table_middle);
        use.makeTile(249, Chair_left);
        
        use.makeTile(250, Table_begin);
        use.makeTile(251, Chair_right);

        use.makeTile(115, Chair_left);
        use.makeTile(116, Table_end);
        use.makeTile(117, Chair_right);
        use.makeTile(136, Table_middle);
        use.makeTile(155, Chair_left);
        use.makeTile(156, Table_begin);
        use.makeTile(157, Chair_right);

        use.makeTile(215, Chair_left);
        use.makeTile(216, Table_end);
        use.makeTile(217, Chair_right);
        use.makeTile(236, Table_middle);
        use.makeTile(255, Chair_left);
        use.makeTile(256, Table_begin);
        use.makeTile(257, Chair_right);


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
        Wall_doormiddle = LoadTexture("Bilder/Wall/Wall_doormiddle.png");
        Wall_doortop = LoadTexture("Bilder/Wall/Wall_doortop.png");
        Wall_entrance = LoadTexture("Bilder/Wall/Wall_entrance.png");
        Wall_entrancetop = LoadTexture("Bilder/Wall/Wall_entrancetop.png");

        //preload cash register
        Cashregister_bottomleft = LoadTexture("Bilder/Furniture/Cashregister_bottomleft.png");
        Cashregister_bottommiddle = LoadTexture("Bilder/Furniture/Cashregister_bottommiddle.png");
        Cashregister_bottomright = LoadTexture("Bilder/Furniture/Cashregister_bottomright.png");
        Cashregister_extrabottom = LoadTexture("Bilder/Furniture/Cashregister_extrabottom.png");
        Cashregister_extratop = LoadTexture("Bilder/Furniture/Cashregister_extratop.png");
        Cashregister_topleft = LoadTexture("Bilder/Furniture/Cashregister_topleft.png");
        Cashregister_topmiddle = LoadTexture("Bilder/Furniture/Cashregister_topmiddle.png");
        Cashregister_topright = LoadTexture("Bilder/Furniture/Cashregister_topright.png");

        //preload Chairs and Table

        Chair_left = LoadTexture("Bilder/Furniture/Chair_left.png");
        Chair_right = LoadTexture("Bilder/Furniture/Chair_right.png");
        Table_begin= LoadTexture("Bilder/Furniture/Table_begin.png");
        Table_middle = LoadTexture("Bilder/Furniture/Table_middle.png");
        Table_end = LoadTexture("Bilder/Furniture/Table_end.png");

        // preload for floor
        Floor = LoadTexture("Bilder/Floor/Floor.png");
        Floor_dirty1 = LoadTexture("Bilder/Floor/Floor_dirty1.png");
        Floor_dirty2 = LoadTexture("Bilder/Floor/Floor_dirty2.png");
        Floor_doorkitbottom = LoadTexture("Bilder/Floor/Floor_doorkitbottom.png");
        Floor_door = LoadTexture("Bilder/Floor/Floor_door.png");

        // preload npcs
        Fatman = LoadTexture("Bilder/NPCS/Fatman.png");
        Fatman_behind = LoadTexture("Bilder/NPCS/Fatman_behind.png");
        Fatman_right = LoadTexture("Bilder/NPCS/Fatman_right.png");
        Fatman_left = LoadTexture("Bilder/NPCS/Fatman_left.png");

        Karen = LoadTexture("Bilder/NPCS/Karen.png");
        Karen_behind = LoadTexture("Bilder/NPCS/Karen_behind.png");
        Karen_left = LoadTexture("Bilder/NPCS/Karen_left.png");
        Karen_right = LoadTexture("Bilder/NPCS/Karen_right.png");

        Normalman = LoadTexture("Bilder/NPCS/Normalman.png");
        Normalman_behind = LoadTexture("Bilder/NPCS/Normalman_behind.png");
        Normalman_left = LoadTexture("Bilder/NPCS/Normalman_left.png");
        Normalman_right = LoadTexture("Bilder/NPCS/Normalman_right.png");



        //initialize mouse vector
        Vector2 mousePos = new Vector2(GetMouseX(),GetMouseY());


        // Instanz vom Singleton
        ArrayListCollection use = ArrayListCollection.getInstance();

        gameObj = new GameController(20);

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
