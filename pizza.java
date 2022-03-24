import com.raylib.Jaylib;
import com.raylib.Jaylib.Vector2;

public class pizza {

    float size;
    float speed;
    Vector2 world_pos;
    int worldspaceX;
    int worldspaceY;

    public pizza(float size, float speed, int worldspaceX, int worldspaceY)
        {
            this.size = size;
            this.speed = speed;
            this.world_pos = new Vector2(worldspaceX,worldspaceY);
            this.worldspaceX = worldspaceX;
            this.worldspaceY = worldspaceY;
        }

        public Vector2 getWorldPos()
        {
            return this.world_pos;
        }
        public float getSize()
        {
            return this.size;
        }


}

