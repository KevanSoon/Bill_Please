package sg.edu.rp.c346.id19013989.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

     EditText amt;
     EditText pax;
     ToggleButton svs;
     ToggleButton gst;
     EditText discount;
     TextView total_bill;
     TextView each_pays;
     ToggleButton split;
     Button reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amt = findViewById(R.id.editTextAmount);
        pax = findViewById(R.id.editTextPax);
        svs = findViewById(R.id.toggleButtonSVS);
        gst = findViewById(R.id.toggleButtonGST);
        discount = findViewById(R.id.editTextDiscount);
        total_bill = findViewById(R.id.textViewTotal_bill);
        each_pays = findViewById(R.id.textViewEachPerson);
        split = findViewById(R.id.toggleButtonSplit);
        reset = findViewById(R.id.buttonReset);




        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for the action
                if (amt.getText().toString().trim().length()!=0 && pax.getText().toString().trim().length()!=0) {
                    double newAmt = 0.0;
                    if (!svs.isChecked() && !gst.isChecked()) {
                        newAmt = Double.parseDouble(amt.getText().toString());
                    }
                    else if (svs.isChecked() && !gst.isChecked()) {
                        newAmt = Double.parseDouble(amt.getText().toString()) * 1.1;
                    }
                    else if (!svs.isChecked() && gst.isChecked()) {
                        newAmt = Double.parseDouble(amt.getText().toString()) * 1.07;
                    }
                    else {
                        newAmt = Double.parseDouble(amt.getText().toString()) * 1.17;

                    }
                    //Discount
                    if (discount.getText().toString().trim().length() != 0) {
                        newAmt *=  1 - Double.parseDouble(discount.getText().toString()) / 100;
                    }

                    total_bill.setText("Total Bill: $" + String.format("%.2f",newAmt));
                    int numPerson = Integer.parseInt(pax.getText().toString());

                    if(numPerson != 1) {
                        each_pays.setText("Each pays: $" + String.format("%.2f",newAmt / numPerson));
                    }
                    else {
                        each_pays.setText("Each pays: $" + newAmt);
                    }

                }


            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for the action
                total_bill.setText(" ");
                each_pays.setText(" ");
                discount.setText(" ");
                amt.setText(" ");
                pax.setText(" ");

            }
        });








    }
}
