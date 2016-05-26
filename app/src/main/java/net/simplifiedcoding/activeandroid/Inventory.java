package net.simplifiedcoding.activeandroid;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by Belal on 5/26/2016.
 */

//This is our table name
@Table(name = "Inventory")

public class Inventory extends Model {

    //The table consist only one field name
    @Column(name = "name")
    public String name;
}
