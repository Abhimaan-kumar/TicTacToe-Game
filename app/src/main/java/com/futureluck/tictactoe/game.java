package com.futureluck.tictactoe;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class game extends AppCompatActivity {



    boolean isActive= true;
    int activePlayer=0;
    int [] gameState = {2,2,2,2,2,2,2,2,2}; //0=x, 1=o, 2=null
    int [][] winPositions ={{0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}};




    public void tap(View view) {
        ImageView img = (ImageView) view;
        int tapedImage = Integer.parseInt(img.getTag().toString());
        String player1 = getIntent().getStringExtra("player1");
        String player2 = getIntent().getStringExtra("player2");

        //to print x & o
        if (gameState[tapedImage]==2 && isActive) {
            gameState[tapedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                TextView status = findViewById(R.id.guide);
                status.setText(player2+"'s Turn (O-Turn)");
                activePlayer = 1;
            } else {
                img.setImageResource(R.drawable.o);
                TextView status = findViewById(R.id.guide);
                status.setText(player1+"'s Turn (X-Turn)");
                activePlayer = 0;
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }

        //to check winning
        for (int[] winPosition:winPositions){
            if (gameState[winPosition[0]]==gameState[winPosition[1]] &&
                    gameState[winPosition[1]]==gameState[winPosition[2]] &&
                    gameState[winPosition[0]]!=2){
                //someone has won
                TextView p1,p2;
                p1=findViewById(R.id.player1);
                p2=findViewById(R.id.player2);
                int[] score ={0,0};
                MediaPlayer win;
                win=MediaPlayer.create(this,R.raw.blast);
                win.start();
                isActive=false;
                TextView status = findViewById(R.id.guide);
                status.setTextSize(35f);
                String winner;
                if (gameState[winPosition[0]]==0){
                    winner= "Winner is "+ player1+" (X)";
                    score[0]+=1;
                    p1.setText(player1+" : "+score[0]);
                    p1.setText(player1+" : 1");
                }
                else {
                    score[1]+=1;
                    p2.setText(player2+" : "+score[1]);
                    winner="Winner is "+player2+" (O)";
                }

                status.setText(winner);

            }
        }


    }

    public void restart(View view) {
        MediaPlayer ready;
        ready= MediaPlayer.create(this,R.raw.getready);
        ready.start();
        isActive=true;
        activePlayer=0;
        gameState= new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2};
        TextView status = findViewById(R.id.guide);
        String player1 = getIntent().getStringExtra("player1");
        status.setText(player1+"'s Turn (x-Turn)");
        status.setTextSize(20f);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView status = findViewById(R.id.guide);
        String player1 = getIntent().getStringExtra("player1");
        status.setText(player1+"'s Turn (x-Turn)");
    }
}