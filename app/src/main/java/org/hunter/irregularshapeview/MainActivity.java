package org.hunter.irregularshapeview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button porterDuffBtn = (Button) findViewById(R.id.PorterDuff);
        Button bitmapShaderBtn = (Button) findViewById(R.id.BitmapShader);
        Button clipPath = (Button) findViewById(R.id.ClipPath);
        porterDuffBtn.setOnClickListener(this);
        bitmapShaderBtn.setOnClickListener(this);
        clipPath.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.PorterDuff:
                startActivity(new Intent(this, PorterDuffActivity.class));
                break;
            case R.id.BitmapShader:
                startActivity(new Intent(this, BitmapShaderActivity.class));
                break;
            case R.id.ClipPath:
                startActivity(new Intent(this, ClipPathActivity.class));
                break;
            default:
                break;
        }
    }
}
