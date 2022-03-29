import com.raylib.Jaylib;
import com.raylib.Jaylib.Vector2;

public class pizza {

    float size;
    float speed;
    Vector2 world_pos;
    float worldspaceX;
    float worldspaceY;

    double ttl;
    double angle;

    public pizza(float size, float speed, float worldspaceX, float worldspaceY, double angle)
        {
            this.size = size;
            this.speed = speed;
            this.world_pos = new Vector2(worldspaceX,worldspaceY);
            this.worldspaceX = worldspaceX;
            this.worldspaceY = worldspaceY;
            this.angle = angle;
            this.ttl =0;
        }

        public Vector2 getWorldPos()
        {
            return this.world_pos;
        }
        public float getSize()
        {
            return this.size;
        }
        public void setWorld_pos(float x,float y) {this.world_pos = new Vector2(x,y);}

        public void setTtl(float time){ this.ttl = this.ttl + time;}
        public double getTtl(){return this.ttl;}

        public double getAngle() {return angle;}


}

