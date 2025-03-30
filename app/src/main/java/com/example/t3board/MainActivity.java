package com.example.t3board;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public int buttonvalue[]=new int[9],count=0;
    public boolean turnX=true;
    public ImageView cell;


    public void boardReset(){
        for(int i=0;i<9;++i)
            buttonvalue[i]=0;
        count=0;
        turnX=true;
        cell=(ImageView)findViewById(R.id.bi1);
        cell.setImageResource(0);
        cell.setClickable(true);
        cell=(ImageView)findViewById(R.id.bi2);
        cell.setImageResource(0);
        cell.setClickable(true);
        cell=(ImageView)findViewById(R.id.bi3);
        cell.setImageResource(0);
        cell.setClickable(true);
        cell=(ImageView)findViewById(R.id.bi4);
        cell.setImageResource(0);
        cell.setClickable(true);
        cell=(ImageView)findViewById(R.id.bi5);
        cell.setImageResource(0);
        cell.setClickable(true);
        cell=(ImageView)findViewById(R.id.bi6);
        cell.setImageResource(0);
        cell.setClickable(true);
        cell=(ImageView)findViewById(R.id.bi7);
        cell.setImageResource(0);
        cell.setClickable(true);
        cell=(ImageView)findViewById(R.id.bi8);
        cell.setImageResource(0);
        cell.setClickable(true);
        cell=(ImageView)findViewById(R.id.bi9);
        cell.setImageResource(0);
        cell.setClickable(true);
        cell=(ImageView)findViewById(R.id.turni);
        cell.setImageResource(R.drawable.x);

    }

    public void buttonClicked(View view)
    {
         cell=(ImageView) findViewById(view.getId());
         int buttonValueIndex=-1;
         switch (view.getId())
         {
             case R.id.bi1: buttonValueIndex=0;break;
             case R.id.bi2: buttonValueIndex=1;break;
             case R.id.bi3: buttonValueIndex=2;break;
             case R.id.bi4: buttonValueIndex=3;break;
             case R.id.bi5: buttonValueIndex=4;break;
             case R.id.bi6: buttonValueIndex=5;break;
             case R.id.bi7: buttonValueIndex=6;break;
             case R.id.bi8: buttonValueIndex=7;break;
             case R.id.bi9: buttonValueIndex=8;break;
         }
         if(turnX) {
             cell.setImageResource(R.drawable.x);
             cell.setClickable(false);
             buttonvalue[buttonValueIndex]=1;
         }
         else
         {
             cell.setImageResource(R.drawable.o);
             cell.setClickable(false);
             buttonvalue[buttonValueIndex]=-1;
         }
         ++count;
         if(count>=5)
            winEvaluator();
         turnX=!turnX;
         cell=(ImageView) findViewById(R.id.turni);
         cell.setImageResource((turnX?R.drawable.x:R.drawable.o));

    }

    public void winEvaluator(){
        int sum=turnX?3:-3;
        if ((buttonvalue[0]+buttonvalue[1]+buttonvalue[2]==sum)||(buttonvalue[3]+buttonvalue[4]+buttonvalue[5]==sum)||(buttonvalue[6]+buttonvalue[7]+buttonvalue[8]==sum)||(buttonvalue[0]+buttonvalue[4]+buttonvalue[8]==sum)||(buttonvalue[2]+buttonvalue[4]+buttonvalue[6]==sum)||(buttonvalue[0]+buttonvalue[3]+buttonvalue[6]==sum)||(buttonvalue[1]+buttonvalue[4]+buttonvalue[7]==sum)||(buttonvalue[2]+buttonvalue[5]+buttonvalue[8]==sum))
        {
            Toast.makeText(getApplicationContext(),"Player "+ (turnX?"X":"O")+ " wins",Toast.LENGTH_LONG).show();
            TextView score=(TextView)(turnX?findViewById(R.id.scoreX):findViewById(R.id.scoreO));
            score.setText(Integer.toString(Integer.parseInt(score.getText().toString())+1));
            sum=0;

            //Dissabling all buttons
            cell=(ImageView)findViewById(R.id.bi1);
            cell.setClickable(false);
            cell=(ImageView)findViewById(R.id.bi2);
            cell.setClickable(false);
            cell=(ImageView)findViewById(R.id.bi3);
            cell.setClickable(false);
            cell=(ImageView)findViewById(R.id.bi4);
            cell.setClickable(false);
            cell=(ImageView)findViewById(R.id.bi5);
            cell.setClickable(false);
            cell=(ImageView)findViewById(R.id.bi6);
            cell.setClickable(false);
            cell=(ImageView)findViewById(R.id.bi7);
            cell.setClickable(false);
            cell=(ImageView)findViewById(R.id.bi8);
            cell.setClickable(false);
            cell=(ImageView)findViewById(R.id.bi9);
            cell.setClickable(false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    boardReset();
                }
            },1300);

        }
        if(count==9&&sum!=0)
        {
            Toast.makeText(getApplicationContext(),"Draw Match",Toast.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    boardReset();
                }
            },1300);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cell=(ImageView)findViewById(R.id.resetbutton);
        cell.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                boardReset();
                TextView score= (TextView)(findViewById(R.id.scoreX));
                score.setText("0");
                score= (TextView)(findViewById(R.id.scoreO));
                score.setText("0");
            }
        });
    }
}