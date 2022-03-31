import java.util.Random;

import static com.raylib.Jaylib.BROWN;
import static com.raylib.Jaylib.ORANGE;
import static com.raylib.Raylib.*;


public class AI {

    int max_count;

    // Singleton Session Pattern begin
    private static AI single_instance = null;

    public static AI getInstance() {
        if (single_instance == null) {
            single_instance = new AI();
        }
        return single_instance;
    }
    // Singleton Session Pattern end

    //this class will be used as an AI controller
    public AI() {

    }

    public void ai_tick_random() {
        ArrayListCollection use = ArrayListCollection.getInstance();

        Random rand = new Random();
        //select how many options the tick switches between 0 and 3
        int ideas = 4;
        int attention = 30;


        for (customer p : use.getCustomerList()) {

            DrawText("thought:"+p.thought ,300,300,20,ORANGE);
            DrawText("Attention:"+p.attention_span ,300,340,20,ORANGE);
            //simple collsion detection
            if(p.worldPos.x() < 0)
            {
                p.thought = 1;
                p.attention_span = p.attention_span + 30;
            }

            if(p.worldPos.y() < 0)
            {
                p.thought = 3;
                p.attention_span = p.attention_span + 30;
            }

            if(p.worldPos.x() > GetScreenWidth())
            {
                p.thought = 0;
                p.attention_span = p.attention_span + 30;
            }

            if(p.worldPos.y() > GetScreenHeight())
            {
                p.thought = 2;
                p.attention_span = p.attention_span + 30;
            }

            if (p.attention_span < 0) {
                //if attention span has reduced to 0 customer will think up a new idea on where to go
                p.thought = rand.nextInt(ideas);
                //and reset is attention
                p.attention_span = rand.nextInt(attention);

            }
            //if attention span is still going thought will be held and customer will keep walking in the same direction
            if (p.attention_span > 0) {
                switch (p.thought) {
                    //move customer left
                    case 0:
                        p.worldPos.x(p.worldPos.x() - 3f);
                        break;
                    case 1:
                        p.worldPos.x(p.worldPos.x() + 3f);
                        break;
                    case 2:
                        p.worldPos.y(p.worldPos.y() - 3f);
                        break;
                    case 3:
                        p.worldPos.y(p.worldPos.y() + 3f);
                        break;
                }

            }
            p.attention_span = p.attention_span -1;
        }
    }
}