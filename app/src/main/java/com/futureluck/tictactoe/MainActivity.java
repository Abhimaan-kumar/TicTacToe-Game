package com.futureluck.tictactoe;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void Play(View view) {
        MediaPlayer ready;
        EditText p1 = findViewById(R.id.player1);
        EditText p2 = findViewById(R.id.player2);
        String play1 = p1.getText().toString();
        String play2 = p2.getText().toString();

        Intent intent= new Intent(this, game.class);
        intent.putExtra("player1", play1 );
        intent.putExtra("player2", play2 );
        ready= MediaPlayer.create(this,R.raw.getready);
        ready.start();
        startActivity(intent);
    }
}