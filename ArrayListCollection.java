import com.raylib.Jaylib;
import com.raylib.Raylib;

import java.util.ArrayList;
import java.util.Vector;

/**
 * The type Array list collection.
 */
public class ArrayListCollection {

    // Singleton Session Pattern begin
    private static ArrayListCollection single_instance = null;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ArrayListCollection getInstance() {
        if (single_instance == null) {
            single_instance = new ArrayListCollection();
        }
        return single_instance;
    }
    // Singleton Session Pattern end

    //create array of pizzas to be shot and drawn
    private ArrayList<pizza> pizzaArray;
    private ArrayList<customer> customerArray;
    private ArrayList<Tiles> TileArray, FurnitureArray,chairArray;

    /**
     * Instantiates a new Array list collection.
     */
    public ArrayListCollection() {
        // Instance
        pizzaArray = new ArrayList<pizza>();
        customerArray = new ArrayList<customer>();
        TileArray = new ArrayList<Tiles>();
        FurnitureArray = new ArrayList<Tiles>();
        chairArray = new ArrayList<Tiles>();
    }

    /**
     * Add chair.
     *
     * @param index the index
     */
//chair register array
    public void addChair(int index){

        //get worldpossition from underlying tile
        var chair = this.getTilesList().get(index).getWorldPos();
        //add chair to seperate array
        chairArray.add(new Tiles(chair.x(),chair.y()));
    }

    /**
     * Gets chair.
     *
     * @return the chair
     */
    public ArrayList<Tiles> getChair()
    {
        return this.chairArray;
    }

    /**
     * Add furniture.
     *
     * @param t the t
     */
// furniture array
    public void addFurniture(Tiles t) {FurnitureArray.add(t);}

    /**
     * Gets furniture array.
     *
     * @return the furniture array
     */
    public ArrayList<Tiles> getFurnitureArray() {return this.FurnitureArray;}

    /**
     * Add pizza.
     *
     * @param p the p
     */
//pizza array
    public void addPizza(pizza p) {pizzaArray.add(p);}

    /**
     * Gets pizza list.
     *
     * @return the pizza list
     */
    public ArrayList<pizza> getPizzaList() {return this.pizzaArray;}

    /**
     * Add customer.
     *
     * @param p the p
     */
//customer array
    public void addCustomer(customer p) {customerArray.add(p);}

    /**
     * Gets customer list.
     *
     * @return the customer list
     */
    public ArrayList<customer> getCustomerList() {return this.customerArray;}

    /**
     * Add tile.
     *
     * @param t the t
     */
//tiles array BACKGROUND
    public  void addTile(Tiles t) { TileArray.add(t); }

    /**
     * Gets tiles list.
     *
     * @return the tiles list
     */
    public ArrayList<Tiles> getTilesList() {return this.TileArray;}

    /**
     * Replace texture.
     *
     * @param index the index
     * @param path  the path
     */
    public void replaceTexture(int index, Raylib.Texture2D path) {
        Tiles tile = TileArray.get(index);
        tile.setTexture(path);
        TileArray.set(index, tile);
    }

    /**
     * Make tile.
     *
     * @param index the index
     * @param path  the path
     */
    public void makeTile(int index, Raylib.Texture2D path) {
        Jaylib.Vector2 vector = TileArray.get(index).getWorldPos();
        Tiles tile = new Tiles(path);
        tile.setWorld_pos(vector);
        FurnitureArray.add(tile);
    }



}
