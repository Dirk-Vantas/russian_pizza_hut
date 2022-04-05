import com.raylib.Jaylib;
import com.raylib.Raylib;

import java.util.ArrayList;
import java.util.Vector;

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
    private ArrayList<Tiles> TileArray, FurnitureArray,chairArray;

    public ArrayListCollection() {
        // Instance
        pizzaArray = new ArrayList<pizza>();
        customerArray = new ArrayList<customer>();
        TileArray = new ArrayList<Tiles>();
        FurnitureArray = new ArrayList<Tiles>();
        chairArray = new ArrayList<Tiles>();
    }
    //chair register array
    public void addChair(int index){

        //get worldpossition from underlying tile
        var chair = this.getTilesList().get(index).getWorldPos();
        //add chair to seperate array
        chairArray.add(new Tiles(chair.x(),chair.y()));
    }

    public ArrayList<Tiles> getChair()
    {
        return this.chairArray;
    }
    // furniture array
    public void addFurniture(Tiles t) {FurnitureArray.add(t);}
    public ArrayList<Tiles> getFurnitureArray() {return this.FurnitureArray;}

    //pizza array
    public void addPizza(pizza p) {pizzaArray.add(p);}
    public ArrayList<pizza> getPizzaList() {return this.pizzaArray;}

    //customer array
    public void addCustomer(customer p) {customerArray.add(p);}
    public ArrayList<customer> getCustomerList() {return this.customerArray;}

    //tiles array BACKGROUND
    public  void addTile(Tiles t) { TileArray.add(t); }
    public ArrayList<Tiles> getTilesList() {return this.TileArray;}

    public void replaceTexture(int index, Raylib.Texture2D path) {
        Tiles tile = TileArray.get(index);
        tile.setTexture(path);
        TileArray.set(index, tile);
    }

    public void makeTile(int index, Raylib.Texture2D path) {
        Jaylib.Vector2 vector = TileArray.get(index).getWorldPos();
        Tiles tile = new Tiles(path);
        tile.setWorld_pos(vector);
        FurnitureArray.add(tile);
    }



}
