import static com.raylib.Jaylib.*;


//instanciate mouse position



public class main {

    public static void draw()
    {
        int posY = GetMouseY();
        int posX = GetMouseX();
        int screenHeight = GetScreenHeight();
        int screenWidth = GetScreenWidth();
        DrawText("y:"+posY+"x:"+posX, 50, 70, 50, GREEN);
        DrawText("Width:"+screenWidth+"Height:"+screenHeight, 20, 50, 20, GREEN);
        //x is width. y is height
        DrawLine(screenWidth/2,screenHeight/2, posX, posY, BLACK);
        ClearBackground(WHITE);
    }


    public static void main(String args[]) {
        InitWindow(800, 450, "Demo");
        SetTargetFPS(60);

        //get mouse pos
        //Vector mousepos = GetMousePosition();
        System.out.println("Text is: "+GetMousePosition());
        while (!WindowShouldClose()) {
            BeginDrawing();
            draw();
            EndDrawing();
            //System.out.println("Text is: "+GetMousePosition());
        }
        CloseWindow();
    }


}
