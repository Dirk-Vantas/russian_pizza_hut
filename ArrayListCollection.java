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
    private ArrayList<Tiles> TileArray;
    private ArrayList<furniture_chair> chairArrayList;
    private ArrayList<furniture_table> tableArrayList;
    private ArrayList<furniture_cashRegister> cashRegisterArrayList;


    public ArrayListCollection() {
        // Instance
        pizzaArray = new ArrayList<pizza>();
        customerArray = new ArrayList<customer>();
        TileArray = new ArrayList<Tiles>();
        chairArrayList = new ArrayList<furniture_chair>();
        cashRegisterArrayList = new ArrayList<furniture_cashRegister>();
        tableArrayList = new ArrayList<furniture_table>();

    }
    //cash register array
    public void addCashRegister(furniture_cashRegister p) {cashRegisterArrayList.add(p);}
    public ArrayList<furniture_cashRegister> getCashRegisterArrayList() {return this.cashRegisterArrayList;}

    //table  array
    public void addTable(furniture_table p) {tableArrayList.add(p);}
    public ArrayList<furniture_table> getTableArrayList() {return this.tableArrayList;}

    //pizza array
    public void addPizza(pizza p) {pizzaArray.add(p);}
    public ArrayList<pizza> getPizzaList() {return this.pizzaArray;}

    //chair array
    public void addChair(furniture_chair p) {chairArrayList.add(p);}
    public ArrayList<furniture_chair> getChairArrayList() {return this.chairArrayList;}

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

        TileArray.add(tile);
    }

}
