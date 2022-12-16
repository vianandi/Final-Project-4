package com.mbkm.project4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class bus_detail extends AppCompatActivity {

    ImageView image1, image2, image3, image4;
    TextView  name, type, desk, price;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_detail);

        Context context = imageV

        image1 = findViewById(R.id.imageView3);
        image2 = findViewById(R.id.imageView8);
        image3 = findViewById(R.id.imageView9);
        image4 = findViewById(R.id.imageView10);
        name = findViewById(R.id.textView6);
        type = findViewById(R.id.textView10);
        desk = findViewById(R.id.textView11);
        price = findViewById(R.id.textView13);

        getIncomingExtra();
    }

    private void getIncomingExtra() {

        if (getIntent().hasExtra("busName") && getIntent().hasExtra("desk")){


            String name  = getIntent().getStringExtra("busName");
            String type  = getIntent().getStringExtra("type");
            String desk = getIntent().getStringExtra("desk");
            String price  = getIntent().getStringExtra("price");
            Drawable image = Drawable.createFromPath(getIntent().getStringExtra("image"));

            setDataActivityBus(image, name, type, desk, price);

        }
    }

    private void setDataActivityBus(Drawable image, String mName, String mType, String mDesk, String mPrice) {
        name.setText(mName);
        type.setText(mType);
        desk.setText(mDesk);
        this.price.setText(mPrice);
        this.image1.setImageDrawable(image);
    }


}