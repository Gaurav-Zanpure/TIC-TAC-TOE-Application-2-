package gaurav.exampleapp.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0: dot    1: cross    2: empty

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int noOfSpaces=0;

    int[][] winningPositions = {{0,1,2} , {3,4,5} , {6,7,8},
                                {0,3,6} , {1,4,7} , {2,5,8},
                                {0,4,8} , {2,4,6}};

    boolean gameActive = true;

    int activePlayer = 0;

    public void dropIn(View view){

        Log.i("Info","Prssed!");
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2  && gameActive ) {

            noOfSpaces++;
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.dot);
                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.cross);
                activePlayer = 0;

            }
            counter.animate().translationYBy(1500).setDuration(200);


            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {
                    //Someone has won

                    gameActive = false;
                    String message = "";

                    if (activePlayer == 1) {

                        message = "Congratulations! Player 1 (ring) has won";

                    } else {

                        message = "Congratulations! Player 2 (cross) has won";

                    }
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show();

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);


                    playAgainButton.setVisibility(View.VISIBLE);

                    winnerTextView.setVisibility(View.VISIBLE);

                }


            }

            if(noOfSpaces==9){
                Toast.makeText(this, "DRAW", Toast.LENGTH_LONG).show();

                Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);


                playAgainButton.setVisibility(View.VISIBLE);

                winnerTextView.setVisibility(View.VISIBLE);
            }

        }

    }




    public void playAgain(View view){

        Log.i("Info","pressed");
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i =0 ; i < gridLayout.getChildCount() ; i++){

            ImageView imageView = (ImageView) gridLayout.getChildAt(i);

            imageView.setImageDrawable(null);

        }

        for(int i =0 ;  i<gameState.length ;i++){
            gameState[i] = 2 ;
        }

        gameActive = true;
        activePlayer = 0;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
