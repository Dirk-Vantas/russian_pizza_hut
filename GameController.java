import java.util.Random;

public class GameController {

    int round_points;
    int max_npc_count;
    double spawner_cooldown;
    double max_spawn_cooldown;



    public GameController(int max_npc_count)
    {
        this.max_npc_count = max_npc_count;
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
            int nextNPC = rand.nextInt(3);

            switch (nextNPC) {
                case 0:
                    use.addCustomer(new karen(30, 30, 300, 300, 1, 32, 32, 300));
                    //reset cooldown
                    this.spawner_cooldown = this.max_spawn_cooldown;
                    break;
                case 1:
                    use.addCustomer(new fat_man(30, 30, 300, 300, 1, 32, 32, 300));
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
