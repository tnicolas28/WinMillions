package dev.esgi.quiveutgagnerdesmillions;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btn_start, btn_add, btn_class,btn_about,btn_quit;
    private FirebaseAnalytics firebase;

    public static int score = 0;
    public static ArrayList<Question> questions = new ArrayList<>();
    public static Question q1 = new Question("What is the capital of France","Italy","Lyon",
            "Nantes","Paris");
    public static Question q2 = new Question("ESGI means","Ecole Superieure Gravité Informatique",
            "Ecole Suberbe Genie Informatique","Ecole Superieure Genie Intelectuel",
            "Ecole Superieure Genie Informatique");
    public static Question q3 = new Question("What is the name of this app creator ?","Luis","Pierre",
            "Miyuki","Ivan");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firebase = FirebaseAnalytics.getInstance(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = findViewById(R.id.btn_start);
        btn_add = findViewById(R.id.btn_add);
        btn_class = findViewById(R.id.btn_class);
        btn_about = findViewById(R.id.btn_about);
        btn_quit = findViewById(R.id.btn_quit);

        btn_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(getResources().getString(R.string.pop_title))
                        .setPositiveButton(getResources().getString(R.string.pop_yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.pop_no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setCancelable(true)
                        .show();
            }
        });

        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, About.class);
                startActivity(i);
                finish();
            }
        });


        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questions.clear();
                questions.add(q1);
                questions.add(q2);
                questions.add(q3);
                Intent i = new Intent(MainActivity.this, Game.class);
                startActivity(i);
                finish();
            }
        });



        Bundle itemJeggings = new Bundle();
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_ID, "SKU_123");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_NAME, "jeggings");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "pants");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_VARIANT, "black");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_BRAND, "Google");
        itemJeggings.putDouble(FirebaseAnalytics.Param.PRICE, 9.99);

        Bundle itemBoots = new Bundle();
        itemBoots.putString(FirebaseAnalytics.Param.ITEM_ID, "SKU_456");
        itemBoots.putString(FirebaseAnalytics.Param.ITEM_NAME, "boots");
        itemBoots.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "shoes");
        itemBoots.putString(FirebaseAnalytics.Param.ITEM_VARIANT, "brown");
        itemBoots.putString(FirebaseAnalytics.Param.ITEM_BRAND, "Google");
        itemBoots.putDouble(FirebaseAnalytics.Param.PRICE, 24.99);

        Bundle itemSocks = new Bundle();
        itemSocks.putString(FirebaseAnalytics.Param.ITEM_ID, "SKU_789");
        itemSocks.putString(FirebaseAnalytics.Param.ITEM_NAME, "ankle_socks");
        itemSocks.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "socks");
        itemSocks.putString(FirebaseAnalytics.Param.ITEM_VARIANT, "red");
        itemSocks.putString(FirebaseAnalytics.Param.ITEM_BRAND, "Google");
        itemSocks.putDouble(FirebaseAnalytics.Param.PRICE, 5.99);


        //Enregistrer un événement lorsque la liste est affichée
        Bundle itemJeggingsWithIndex = new Bundle(itemJeggings);
        itemJeggingsWithIndex.putLong(FirebaseAnalytics.Param.INDEX, 1);

        Bundle itemBootsWithIndex = new Bundle(itemBoots);
        itemBootsWithIndex.putLong(FirebaseAnalytics.Param.INDEX, 2);

        Bundle itemSocksWithIndex = new Bundle(itemSocks);
        itemSocksWithIndex.putLong(FirebaseAnalytics.Param.INDEX, 3);

        Bundle viewItemListParams = new Bundle();
        viewItemListParams.putString(FirebaseAnalytics.Param.ITEM_LIST_ID, "L001");
        viewItemListParams.putString(FirebaseAnalytics.Param.ITEM_LIST_NAME, "Related products");
        viewItemListParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                new Parcelable[]{ itemJeggingsWithIndex, itemBootsWithIndex, itemSocksWithIndex });
        firebase.logEvent(FirebaseAnalytics.Event.VIEW_ITEM_LIST, viewItemListParams);

        //Lorsque l'utilisateur sélectionne le produit, enregistre un événement du produit
        Bundle selectItemParams = new Bundle();
        selectItemParams.putString(FirebaseAnalytics.Param.ITEM_LIST_ID, "L001");
        selectItemParams.putString(FirebaseAnalytics.Param.ITEM_LIST_NAME, "Related products");
        selectItemParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                new Parcelable[]{ itemJeggings });
        firebase.logEvent(FirebaseAnalytics.Event.SELECT_ITEM, selectItemParams);


        //Afficher les détails du produit
        Bundle viewItemParams = new Bundle();
        viewItemParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
        viewItemParams.putDouble(FirebaseAnalytics.Param.VALUE, 9.99);
        viewItemParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                new Parcelable[] { itemJeggings });

        firebase.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, viewItemParams);

        //Ajouter un produit au panier
        Bundle itemJeggingsWishlist = new Bundle(itemJeggings);
        itemJeggingsWishlist.putLong(FirebaseAnalytics.Param.QUANTITY, 2);

        Bundle addToWishlistParams = new Bundle();
        addToWishlistParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
        addToWishlistParams.putDouble(FirebaseAnalytics.Param.VALUE, 2 * 9.99);
        addToWishlistParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                new Parcelable[]{ itemJeggingsWishlist });

        firebase.logEvent(FirebaseAnalytics.Event.ADD_TO_WISHLIST, addToWishlistParams);

        Bundle itemJeggingsCart = new Bundle(itemJeggings);
        itemJeggingsCart.putLong(FirebaseAnalytics.Param.QUANTITY, 2);

        Bundle itemBootsCart = new Bundle(itemBoots);
        itemBootsCart.putLong(FirebaseAnalytics.Param.QUANTITY, 1);

        Bundle viewCartParams = new Bundle();
        viewCartParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
        viewCartParams.putDouble(FirebaseAnalytics.Param.VALUE, (2 * 9.99) + (1 * 24.99));
        viewCartParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                new Parcelable[]{ itemJeggingsCart, itemBootsCart });

        firebase.logEvent(FirebaseAnalytics.Event.VIEW_CART, viewCartParams);


        //Supprimer un produit du panier
        Bundle removeCartParams = new Bundle();
        removeCartParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
        removeCartParams.putDouble(FirebaseAnalytics.Param.VALUE, (1 * 24.99));
        removeCartParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                new Parcelable[]{ itemBootsCart });

        firebase.logEvent(FirebaseAnalytics.Event.REMOVE_FROM_CART, removeCartParams);


        //Processus de paiement
        Bundle beginCheckoutParams = new Bundle();
        beginCheckoutParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
        beginCheckoutParams.putDouble(FirebaseAnalytics.Param.VALUE, 14.98);
        beginCheckoutParams.putString(FirebaseAnalytics.Param.COUPON, "SUMMER_FUN");
        beginCheckoutParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                new Parcelable[]{ itemJeggingsCart });

        firebase.logEvent(FirebaseAnalytics.Event.BEGIN_CHECKOUT, beginCheckoutParams);

        Bundle addShippingParams = new Bundle();
        addShippingParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
        addShippingParams.putDouble(FirebaseAnalytics.Param.VALUE, 14.98);
        addShippingParams.putString(FirebaseAnalytics.Param.COUPON, "SUMMER_FUN");
        addShippingParams.putString(FirebaseAnalytics.Param.SHIPPING_TIER, "Ground");
        addShippingParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                new Parcelable[]{ itemJeggingsCart });

        firebase.logEvent(FirebaseAnalytics.Event.ADD_SHIPPING_INFO, addShippingParams);

        Bundle addPaymentParams = new Bundle();
        addPaymentParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
        addPaymentParams.putDouble(FirebaseAnalytics.Param.VALUE, 14.98);
        addPaymentParams.putString(FirebaseAnalytics.Param.COUPON, "SUMMER_FUN");
        addPaymentParams.putString(FirebaseAnalytics.Param.PAYMENT_TYPE, "Visa");
        addPaymentParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                new Parcelable[]{ itemJeggingsCart });

        firebase.logEvent(FirebaseAnalytics.Event.ADD_PAYMENT_INFO, addPaymentParams);


        //Effectuer un achat
        Bundle purchaseParams = new Bundle();
        purchaseParams.putString(FirebaseAnalytics.Param.TRANSACTION_ID, "T12345");
        purchaseParams.putString(FirebaseAnalytics.Param.AFFILIATION, "Google Store");
        purchaseParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
        purchaseParams.putDouble(FirebaseAnalytics.Param.VALUE, 14.98);
        purchaseParams.putDouble(FirebaseAnalytics.Param.TAX, 2.58);
        purchaseParams.putDouble(FirebaseAnalytics.Param.SHIPPING, 5.34);
        purchaseParams.putString(FirebaseAnalytics.Param.COUPON, "SUMMER_FUN");
        purchaseParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                new Parcelable[]{ itemJeggingsCart });

        firebase.logEvent(FirebaseAnalytics.Event.PURCHASE, purchaseParams);


        //Effectuer un remboursement
        Bundle refundParams = new Bundle();
        refundParams.putString(FirebaseAnalytics.Param.TRANSACTION_ID, "T12345");
        refundParams.putString(FirebaseAnalytics.Param.AFFILIATION, "Google Store");
        refundParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
        refundParams.putDouble(FirebaseAnalytics.Param.VALUE, 9.99);

        // (Optional) for partial refunds, define the item ID and quantity of refunded items
        refundParams.putString(FirebaseAnalytics.Param.ITEM_ID, "SKU_123");
        refundParams.putLong(FirebaseAnalytics.Param.QUANTITY, 1);

        refundParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                new Parcelable[]{ itemJeggings });

        firebase.logEvent(FirebaseAnalytics.Event.REFUND, refundParams);

        //Appliquer promotion
        Bundle promoParams = new Bundle();
        promoParams.putString(FirebaseAnalytics.Param.PROMOTION_ID, "SUMMER_FUN");
        promoParams.putString(FirebaseAnalytics.Param.PROMOTION_NAME, "Summer Sale");
        promoParams.putString(FirebaseAnalytics.Param.CREATIVE_NAME, "summer2020_promo.jpg");
        promoParams.putString(FirebaseAnalytics.Param.CREATIVE_SLOT, "featured_app_1");
        promoParams.putString(FirebaseAnalytics.Param.LOCATION_ID, "HERO_BANNER");
        promoParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS,
                new Parcelable[]{ itemJeggings });

        // Promotion displayed
        firebase.logEvent(FirebaseAnalytics.Event.VIEW_PROMOTION, promoParams);

        // Promotion selected
        firebase.logEvent(FirebaseAnalytics.Event.SELECT_PROMOTION, promoParams);
    }

}
