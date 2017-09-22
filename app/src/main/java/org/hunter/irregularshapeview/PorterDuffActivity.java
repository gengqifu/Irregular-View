package org.hunter.irregularshapeview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PorterDuffActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porter_duff);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, PorterDuffActivity.class);
        context.startActivity(starter);
    }
}
