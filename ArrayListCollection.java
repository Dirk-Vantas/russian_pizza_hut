import com.raylib.Raylib;

import java.util.ArrayList;

public class ArrayListCollection {

    // Singleton Session Pattern begin
    private static ArrayListCollection single_instance = null;

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
    private ArrayList<Tiles> TileArray;


    public ArrayListCollection() {
        // Instance
        pizzaArray = new ArrayList<pizza>();
        customerArray = new ArrayList<customer>();
        TileArray = new ArrayList<Tiles>();

    }

    public void addPizza(pizza p) {
        pizzaArray.add(p);
    }
    public ArrayList<pizza> getPizzaList() {
        return this.pizzaArray;
    }

    //create array of customers to be rendered and moved

    public void addCustomer(customer p) {
        customerArray.add(p);
    }
    public ArrayList<customer> getCustomerList() {
        return this.customerArray;
    }

    public  void addTile(Tiles t) { TileArray.add(t); }
    public ArrayList<Tiles> getTilesList() {
        return this.TileArray;
    }

    public void replaceTexture(int index, Raylib.Texture2D path) {
        Tiles tile = TileArray.get(index);
        tile.setTexture(path);
        TileArray.set(index, tile);
    }

}
