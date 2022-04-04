import com.raylib.Jaylib;
import com.raylib.Jaylib.Vector2;
import com.raylib.Raylib;

public class Tiles {

    private Raylib.Texture2D texture;
    private Jaylib.Vector2 world_pos;

    public Tiles(Raylib.Texture2D texture, float worldspaceX, float worldspaceY) {
        this.texture = texture;
        this.setWorld_pos(new Vector2(worldspaceX, worldspaceY));
    }

    public Tiles(Raylib.Texture2D texture) {
        this.texture = texture;
    }

    public Jaylib.Vector2 getWorldPos() {
        return this.world_pos;
    }

    public Raylib.Texture2D getTexture() {
        return this.texture;
    }

    public void setTexture(Raylib.Texture2D texture) {
        this.texture = texture;
    }

    public void setWorld_pos(Vector2 world_pos) {
        this.world_pos = world_pos;
    }
}
