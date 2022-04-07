import com.raylib.Jaylib;
import com.raylib.Jaylib.Vector2;
import com.raylib.Raylib;

/**
 * The type Tiles.
 */
public class Tiles {

    private Raylib.Texture2D texture;
    private Jaylib.Vector2 world_pos;
    private boolean isOccupied;
    /**
     * The Occupiedby.
     */
    customer occupiedby;

    /**
     * Instantiates a new Tiles.
     *
     * @param texture     the texture
     * @param worldspaceX the worldspace x
     * @param worldspaceY the worldspace y
     */
    public Tiles(Raylib.Texture2D texture, float worldspaceX, float worldspaceY) {
        this.texture = texture;
        this.setWorld_pos(new Vector2(worldspaceX, worldspaceY));
        this.isOccupied = false;
    }

    /**
     * Instantiates a new Tiles.
     *
     * @param worldspaceX the worldspace x
     * @param worldspaceY the worldspace y
     */
    public Tiles(float worldspaceX, float worldspaceY)
    {
        this.setWorld_pos(new Vector2(worldspaceX, worldspaceY));
        this.isOccupied = false;
    }

    /**
     * Instantiates a new Tiles.
     *
     * @param texture the texture
     */
    public Tiles(Raylib.Texture2D texture) {
        this.texture = texture;
    }

    /**
     * Gets world pos.
     *
     * @return the world pos
     */
    public Jaylib.Vector2 getWorldPos() {
        return this.world_pos;
    }

    /**
     * Gets texture.
     *
     * @return the texture
     */
    public Raylib.Texture2D getTexture() {
        return this.texture;
    }

    /**
     * Sets texture.
     *
     * @param texture the texture
     */
    public void setTexture(Raylib.Texture2D texture) {
        this.texture = texture;
    }

    /**
     * Sets world pos.
     *
     * @param world_pos the world pos
     */
    public void setWorld_pos(Vector2 world_pos) {
        this.world_pos = world_pos;
    }

    /**
     * Set occupied.
     *
     * @param set the set
     */
    public void setOccupied(boolean set){this.isOccupied = set;}

    /**
     * Get occupied boolean.
     *
     * @return the boolean
     */
    public boolean getOccupied(){return this.isOccupied;}

    /**
     * Set occupant.
     *
     * @param o the o
     */
    public void setOccupant(customer o){this.occupiedby = o;}
}
