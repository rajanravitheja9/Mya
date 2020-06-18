package com.example.myapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int noc;
    int noc1;

    private TextView noc3;
    static final String noc2="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noc3=findViewById(R.id.quantity_text_view);

        if (savedInstanceState != null) {
            noc=savedInstanceState.getInt(noc2);

            display(noc);
        }
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText name=(EditText) findViewById(R.id.editText1);
        String val=name.getText().toString();
        CheckBox wcb = (CheckBox) findViewById(R.id.checkBox1);
        CheckBox cb = (CheckBox) findViewById(R.id.checkBox2);
        if (wcb.isChecked() && cb.isChecked()) {
            noc1 = noc *20;
            displayMessage("Name:"+val+"\nWhipped cream  and chocolate included"+"\nTotal: $" + noc1 + "\nThankYou");
        }
        else if(cb.isChecked())
            {
            noc1 = noc *15;
            displayMessage("Name:"+val+"\nChocolate included"+"\nTotal: $" + noc1 + "\nThankYou");
        }
        else if(wcb.isChecked()){
            noc1 = noc *10;
            displayMessage("Name:"+val+"\nWhipped cream included"+"\nTotal: $" + noc1 + "\nThankYou");
        }
        else
        {
            noc1 = noc * 5;
            displayMessage("Name:"+val+"\nPlain Coffee"+"\nTotal: $" + noc1 + "\nThankYou");
        }
    }
    public void increment(View view) {
        noc++;
        display(noc);
    }
    public void decrement(View view) {
        noc--;
        display(noc);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        // Change the label of the menu based on the state of the app.
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Check if the correct item was clicked.
        if (item.getItemId() == R.id.night_mode) {
            // Get the night mode state of the app.
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            // Set the theme mode for the restarted activity.
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_YES);
            }
            // Recreate the activity for the theme change to take effect
        }
        return true;
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        outState.putInt(noc2,noc);
        super.onSaveInstanceState(outState);
    }
}
