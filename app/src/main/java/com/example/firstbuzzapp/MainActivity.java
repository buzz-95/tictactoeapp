package com.example.firstbuzzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView1,textView2;
    private int player1,player2;
    private int numrounds;
    private Button buttons[][] = new Button[3][3];
    private Button ButtonReset;
    private boolean player1Turn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numrounds = player1 = player2 = 0;
        textView1 = findViewById(R.id.text_view_p1);
        textView2 = findViewById(R.id.text_view_p2);
        player1Turn = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = new String("button_" + Integer.toString(i) + Integer.toString(j));
                int ResourceId = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(ResourceId);
                buttons[i][j].setOnClickListener(this);
            }
        }
        ButtonReset = findViewById(R.id.button_reset);
        ButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoard();
                textView1.setText("Player1 : 0");
                textView2.setText("Player2 : 0");
                player1 = player2 = 0;
                resetBoard();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (((Button) v).getText().toString().equals("")) {
            if (player1Turn) {
                ((Button) v).setText("X");
            } else {
                ((Button) v).setText("O");
            }
            if (!player1Turn) player1Turn = true;
            else player1Turn = false;
            numrounds++;
            whoWins();
        }
    }
    public void whoWins(){
        int a[][] = new int[3][3];
        for(int i = 0;i < 3;i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().toString().equals("X")) a[i][j] = 1;
                else if (buttons[i][j].getText().toString().equals("O")) a[i][j] = -1;
                else a[i][j] = 0;
            }
        }
        int flag = 0;
        for(int i = 0;i < 3;i++){
            int sum = 0;
            for(int j = 0;j < 3;j++){
                sum = sum + a[i][j];
            }
            if(sum == 3){
                flag = 1;
            }
            if(sum == -3){
                flag = -1;
            }
            sum = 0;
            for(int j = 0;j < 3;j++){
                sum = sum + a[j][i];
            }
            if(sum == 3){
                flag = 1;
            }
            if(sum == -3){
                flag = -1;
            }
        }
        int sum = a[0][0] + a[1][1] + a[2][2];
        if(sum == 3){
            flag = 1;
        }
        if(sum == -3){
            flag = -1;
        }
        sum = a[0][2] + a[1][1] + a[2][0];
        if(sum == 3){
            flag = 1;
        }
        if(sum == -3){
            flag = -1;
        }
        int checkvar = flag;
        if(checkvar == 1){
            player1Wins();
        }
        else if(checkvar == -1){
            player2Wins();
        }
        else{
            if(numrounds == 9){
                noOneWins();
            }
        }
    }
    private void noOneWins(){
        Toast.makeText(this,"Draw",Toast.LENGTH_SHORT).show();
        resetBoard();
    }
    private void player1Wins(){
        player1++;
        Toast.makeText(this,"Player1Wins",Toast.LENGTH_SHORT).show();
        resetBoard();
    }
    private void player2Wins(){
        player2++;
        Toast.makeText(this,"Player2Wins",Toast.LENGTH_SHORT).show();
        resetBoard();
    }
    private void resetBoard(){
        updatePoints();
        for(int i = 0;i < 3;i++){
            for(int j = 0;j < 3;j++){
                buttons[i][j].setText("");
            }
        }
        numrounds = 0;
        player1Turn = true;
    }
    private void updatePoints(){
        textView1.setText("Player1 : " + Integer.toString(player1));
        textView2.setText("Player2 : " + Integer.toString(player2));
    }
}
