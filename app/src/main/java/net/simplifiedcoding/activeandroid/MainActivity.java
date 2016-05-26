package net.simplifiedcoding.activeandroid;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Creating Views
    private EditText editTextInventoryName;
    private Button buttonSaveInventory;
    private ListView listViewInventories;

    //Array list for storing all the inventoryItems
    private ArrayList<String> inventoryItems;

    //Adapter for listview
    private ArrayAdapter inventoryItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing views
        editTextInventoryName = (EditText) findViewById(R.id.editTextInventoryName);
        buttonSaveInventory = (Button) findViewById(R.id.buttonSaveInventory);
        listViewInventories = (ListView) findViewById(R.id.listViewInventories);

        //Initializing arraylist
        inventoryItems = new ArrayList<>();

        //Adding listener to button
        buttonSaveInventory.setOnClickListener(this);

        //calling method to display the inventory list
        showInventoryList();
    }

    private List<Inventory> getAll() {
        //Getting all items stored in Inventory table
        return new Select()
                .from(Inventory.class)
                .orderBy("Name ASC")
                .execute();
    }



    private void showInventoryList() {
        //Creating a list and getting all inventories from the method
        List<Inventory> inventories = getAll();

        //Adding all the items of the inventories to arraylist
        for (int i = 0; i < inventories.size(); i++) {
            Inventory inventory = inventories.get(i);
            inventoryItems.add(inventory.name);
        }

        //Creating our adapter
        inventoryItemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, inventoryItems);

        //Adding adapter to listview
        listViewInventories.setAdapter(inventoryItemsAdapter);

        //Updating the inventory list
        updateInventoryList();
    }

    private void saveInventory() {
        //Getting name from editText
        String name = editTextInventoryName.getText().toString().trim();

        //Checking if name is blank
        if (name.equalsIgnoreCase("")) {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
            return;
        }

        //If name is not blank creating a new Inventory object
        Inventory inventory = new Inventory();
        //Adding the given name to inventory name
        inventory.name = name;
        //Saving name to sqlite database
        inventory.save();
        inventoryItems.add(name);

        Toast.makeText(this, "Inventory Saved Successfully", Toast.LENGTH_LONG).show();
    }


    private void updateInventoryList() {
        inventoryItemsAdapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {
        saveInventory();
    }


}
