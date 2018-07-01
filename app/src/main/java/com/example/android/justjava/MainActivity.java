/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;

import java.text.NumberFormat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

/// Figure out if the user wants chocolate topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

//        To add EditTextView
        EditText nameField = (EditText) findViewById(R.id.name_view);
        String name =nameField.getText().toString();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage =createOrderSummary(name,price,hasWhippedCream,hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this

        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }



//This method is called when the plus button is clicked
    public void increment(View view) {

        if (quantity ==100) {

            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity + 1;
        display (quantity);

    }

    //This method is called when the minus button is clicked
    public void decrement(View view) {
        if (quantity ==1) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
            quantity = quantity -1;
        display (quantity);

    }
//Calculates the price of the order
//    @param addWhippedCream is wether or not the user wants whipped cream topping
//    @param addChocolate is wether or not the user wants chocolate topping

//    You can just return the quantity as its a global variable
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int baseprice = 5;
        if (addWhippedCream) {
            baseprice = baseprice + 1;
        }
        if (addChocolate) {
            baseprice = baseprice + 2;
        }
//        Caculates total order price by multiplying by quantity
        return quantity * baseprice;
    }
//Creates summary of  the  order....@param price of the order...@return text summary
//    add whipped cream...wether the customer want whipped cream or not
    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate){
        String priceMessage ="Name:" +name;
        priceMessage=priceMessage + "\nAdd Whipped Cream? " + addWhippedCream;
        priceMessage=priceMessage + "\nAdd Chocolate? " + addChocolate;
        priceMessage=priceMessage +"\nQuantity: " + quantity;
        priceMessage=priceMessage + "\nTotal: $" + price;
        priceMessage=priceMessage + "\nThank You!";
        return priceMessage;
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }



}