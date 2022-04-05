import com.raylib.Raylib;

import java.util.Random;

import static com.raylib.Raylib.LoadTexture;

public class GameController {

    int round_points;
    int max_npc_count;
    double spawner_cooldown;
    double max_spawn_cooldown;
    private Raylib.Texture2D Fatman, Fatman_behind, Fatman_left, Fatman_right, Karen, Karen_behind, Karen_left, Karen_right, Normalman, Normalman_behind, Normalman_left, Normalman_right;



    public GameController(int max_npc_count)
    {
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

        this.max_npc_count = max_npc_count;
        this.spawner_cooldown = 10;
        this.max_spawn_cooldown = 400;
    }

    public void addPoint()
    {
        this.round_points++;
    }

    public void subtracktPoints()
    {
        this.round_points--;
    }

    public void spawnNPC()
    {
        if(this.spawner_cooldown < 0)
        {

            // Instanz vom Singleton
            ArrayListCollection use = ArrayListCollection.getInstance();
            Random rand = new Random();
            //get random type of npc and add him
            int nextNPC = rand.nextInt(1);

            switch (nextNPC) {
                case 0:
                    karen karen = new karen(30, 30, 300, 300, 1, 32, 32, 300);
                    karen.setTexture2D(this.Karen);
                    use.addCustomer(karen);

                    //reset cooldown
                    this.spawner_cooldown = this.max_spawn_cooldown;
                    break;
                case 1:
                    fat_man fatman = new fat_man(30, 30, 300, 300, 1, 32, 32, 300);
                    fatman.setTexture2D(this.Fatman);
                    use.addCustomer(fatman);
                    //reset cooldown

                    this.spawner_cooldown = this.max_spawn_cooldown;
                    break;
            }


        }
        else
        {
            //if spawner cooldown is active reduce it further
            this.spawner_cooldown--;
        }
    }
}
