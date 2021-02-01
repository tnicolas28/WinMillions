package dev.esgi.quiveutgagnerdesmillions;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;

public class Store extends AppCompatActivity {
    private Button btn_back, btn_buy_life;
    private FirebaseAnalytics firebase;
    private final ArrayList<String> itemswishlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        firebase = FirebaseAnalytics.getInstance(this);


        btn_buy_life = findViewById(R.id.btn_buy_life);
        btn_back = findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Store.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        btn_buy_life.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Davido kun loves suckin on those toes");
                Bundle itemLife = new Bundle();
                itemLife.putString(FirebaseAnalytics.Param.ITEM_ID, "life_123");
                itemLife.putString(FirebaseAnalytics.Param.ITEM_NAME, "life");
                itemLife.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "achat");
                itemLife.putString(FirebaseAnalytics.Param.ITEM_VARIANT, "black");
                itemLife.putString(FirebaseAnalytics.Param.ITEM_BRAND, "BITECOUYCHATTE");
                itemLife.putDouble(FirebaseAnalytics.Param.PRICE, 9.99);

                Bundle itemLifeWishlist = new Bundle(itemLife);
                itemLifeWishlist.putLong(FirebaseAnalytics.Param.QUANTITY, 2);

                Bundle addToWishlistParams = new Bundle();
                addToWishlistParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
                addToWishlistParams.putDouble(FirebaseAnalytics.Param.VALUE, 2 * 9.99);
                addToWishlistParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                        new Parcelable[]{itemLifeWishlist});
                firebase.logEvent(FirebaseAnalytics.Event.ADD_TO_WISHLIST, addToWishlistParams);
                itemswishlist.add("life");
                //simulation d'achat du contenu de la wish list
                buyAllWishList();
            }
        });
    }

    public void buyAllWishList() {
        for (String itemName : itemswishlist) {
            System.out.println("Buy "+itemName);
            Bundle itemLife = new Bundle();
            itemLife.putString(FirebaseAnalytics.Param.ITEM_ID, "life_123");
            itemLife.putString(FirebaseAnalytics.Param.ITEM_NAME, itemName);
            itemLife.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "achat");
            itemLife.putString(FirebaseAnalytics.Param.ITEM_BRAND, "BITECOUYCHATTE");
            itemLife.putDouble(FirebaseAnalytics.Param.PRICE, 9.99);

            Bundle itemLifeBuy = new Bundle(itemLife);
            itemLifeBuy.putLong(FirebaseAnalytics.Param.QUANTITY, 2);

            Bundle addToBuyParams = new Bundle();
            addToBuyParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
            addToBuyParams.putDouble(FirebaseAnalytics.Param.VALUE, 2 * 9.99);
            addToBuyParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                    new Parcelable[]{itemLifeBuy});
            firebase.logEvent(FirebaseAnalytics.Event.ADD_PAYMENT_INFO, addToBuyParams);
        }
        itemswishlist.clear();
    }
}
