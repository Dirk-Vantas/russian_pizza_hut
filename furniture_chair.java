import com.raylib.Jaylib;
import com.raylib.Raylib;

public class furniture_chair extends Tiles{

    public furniture_chair(Raylib.Texture2D texture, float worldspaceX, float worldspaceY)
    {
        //does this super alredy add the values to the object when it is called via super()?
        super(texture,worldspaceX,worldspaceY);

    }
}
