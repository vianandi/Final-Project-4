package com.mbkm.project4;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class details_activity extends AppCompatActivity {

    ImageView image;
    TextView name, desk, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        image = findViewById(R.id.imageView3);
        name = findViewById(R.id.textView6);
        desk = findViewById(R.id.textView10);
        price = findViewById(R.id.textView11);

        getIncomingExtra();
    }

    private void getIncomingExtra() {

        if (getIntent().hasExtra("placeName") && getIntent().hasExtra("countryName")){


            String name  = getIntent().getStringExtra("placeName");
            String desk = getIntent().getStringExtra("countryName");
            String price  = getIntent().getStringExtra("price");
            Drawable image = Drawable.createFromPath(getIntent().getStringExtra("place_image"));

            setDataActivityBus(image, name, desk, price);
        }

    }

    private void setDataActivityBus(Drawable image, String mName, String mDesk, String mPrice) {
        name.setText(mName);
        desk.setText(mDesk);
        this.price.setText(mPrice);
        this.image.setImageDrawable(image);
    }


}