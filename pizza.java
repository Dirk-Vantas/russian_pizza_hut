import com.raylib.Jaylib;
import com.raylib.Jaylib.Vector2;

/**
 * The type Pizza.
 */
public class pizza {

    private float size;
    private float speed;
    private Vector2 world_pos;
    private float worldspaceX;
    private float worldspaceY;
    private boolean exists;

    private double ttl;
    private double angle;

    /**
     * Instantiates a new Pizza.
     *
     * @param size        the size
     * @param speed       the speed
     * @param worldspaceX the worldspace x
     * @param worldspaceY the worldspace y
     * @param angle       the angle
     */
    public pizza(float size, float speed, float worldspaceX, float worldspaceY, double angle) {
        this.size = size;
        this.world_pos = new Vector2(worldspaceX, worldspaceY);
        this.worldspaceX = worldspaceX;
        this.worldspaceY = worldspaceY;
        this.angle = angle;
        this.ttl = 0;
        this.exists=true;
    }

    /**
     * Gets world pos.
     *
     * @return the world pos
     */
    public Vector2 getWorldPos() {
        return this.world_pos;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public float getSize() {
        return this.size;
    }

    /**
     * Sets world pos.
     *
     * @param x the x
     * @param y the y
     */
    public void setWorld_pos(float x, float y) {
        this.world_pos = new Vector2(x, y);
    }

    /**
     * Sets ttl.
     *
     * @param time the time
     */
    public void setTtl(float time) {
        this.ttl = this.ttl + time;
    }

    /**
     * Gets ttl.
     *
     * @return the ttl
     */
    public double getTtl() {
        return this.ttl;
    }

    /**
     * Gets angle.
     *
     * @return the angle
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Set visibility.
     *
     * @param tag the tag
     */
    public void setVisibility(boolean tag){ this.exists = tag;}

    /**
     * Get visibility boolean.
     *
     * @return the boolean
     */
    public boolean getVisibility(){return this.exists;}

}

