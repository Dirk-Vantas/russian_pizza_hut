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
        DrawText("Width:"+screenWidth+"Height:"+screenHeight, 20, 50, 20, GREEN);


        //x is width. y is height
        DrawLine(screenWidth/2,screenHeight/2, Math.round(dudePosX), Math.round(dudePosY), BLACK);
        //second line
        DrawLine( Math.round(dudePosX), Math.round(dudePosY),posX,posY, BLACK);


        ClearBackground(WHITE);
    }

    public static void getAngle()
    {

        /*
        dot = x1*x2 + y1*y2      # dot product
        det = x1*y2 - y1*x2      # determinant
        angle = atan2(det, dot)  # atan2(y, x) or atan2(sin, cos)
         */
        float screenHeight = GetScreenHeight();
        float screenWidth = GetScreenWidth();
        //get base vector that is just pos Y pointing straight up
        Vector2 PositivY_Vector = new Vector2(screenWidth/2, 0);
        Vector2 screenCenter = new Vector2(screenWidth/2, screenHeight/2);
        DrawLineV(screenCenter,PositivY_Vector, BLACK);

        float posY = GetMouseY();
        float posX = GetMouseX();

        Vector2 mousePointer = new Vector2(posX,posY);
        DrawLineV(screenCenter,mousePointer, BLACK);

        //ultra dot math :)))

        //var dot = PositivY_Vector.x();
        //Math.atan2();
    }



    public static void movePizzas()
    {

    }

    public void drawPizzas(ArrayList<pizza>  pizzaArray)
    {


        for (pizza p:  pizzaArray){
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

            if (IsKeyDown(KEY_D)) dudePos.x(Posx + 2f);
            if (IsKeyDown(KEY_A)) dudePos.x(Posx - 2.0f);
            if (IsKeyDown(KEY_W)) dudePos.y(Posy - 2.0f);
            if (IsKeyDown(KEY_S)) dudePos.y(Posy + 2.0f);
            if (IsMouseButtonPressed(MOUSE_LEFT_BUTTON))
            {
                pizza pizzaObj = new pizza(50,50, GetMouseX(),GetMouseY());
                pizzaArray.add(pizzaObj);
            }
            for (pizza p:  pizzaArray){
                Vector2 POS = p.getWorldPos();
                float y = POS.y();
                float x = POS.x();
                float fefty;
                DrawCircleV(p.getWorldPos(), p.getSize(), RED);

            }
            //drawn all placed pizzas
            pizzaArray.forEach((n) -> DrawCircleV(n.getWorldPos(), n.getSize(), RED));

            if (IsKeyPressed(KEY_T))
            {
                var y = 4;
            }
            movePizzas();
            BeginDrawing();
            getAngle();
            //draw call with position of the main dude
            draw(dudePos.x(), dudePos.y());
            DrawRectangleV(dudePos,dudeBody, MAGENTA);
            EndDrawing();

        }
        CloseWindow();
    }


}
