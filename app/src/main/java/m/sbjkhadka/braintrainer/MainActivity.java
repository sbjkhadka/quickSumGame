package m.sbjkhadka.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView textViewTimer,textViewQuestion,textViewScore,textViewResult;
    Button button0,button1,button2,button3,buttonStart;
    GridLayout gridButtons;
    int score=0,attempt=0;
    int a,b,result,correctAnsPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewTimer=(TextView)findViewById(R.id.textViewTimer);
        textViewQuestion=(TextView)findViewById(R.id.textViewQuestion);
        textViewScore=(TextView)findViewById(R.id.textViewScore);
        textViewResult=(TextView)findViewById(R.id.textViewResult);
        button0=(Button)findViewById(R.id.button0);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        buttonStart=(Button)findViewById(R.id.buttonStart);
        gridButtons=(GridLayout)findViewById(R.id.gridButtons);

       init();
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button0.setEnabled(true);
                button1.setEnabled(true);
                button2.setEnabled(true);
                button3.setEnabled(true);
                buttonStart.setVisibility(View.INVISIBLE);
                gridButtons.setVisibility(View.VISIBLE);
                start();
            }
        });
    }

    public void init(){
        textViewTimer.setText(Integer.toString(30));
        textViewQuestion.setText("");
        textViewScore.setText("0/0");
        if(attempt>0){
            textViewResult.setText("Your Score: "+score+"/"+attempt);
            buttonStart.setText("Replay");

        }else{
            //textViewResult.setText("");
        }

        button0.setEnabled(false);
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);

        button0.setText("");
        button1.setText("");
        button2.setText("");
        button3.setText("");
        gridButtons.setVisibility(View.INVISIBLE);
        buttonStart.setVisibility(View.VISIBLE);


    }

    public void start(){

        prepare();

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                textViewTimer.setText(Long.toString((int)(millisUntilFinished/1000)));
            }

            @Override
            public void onFinish() {
                textViewTimer.setText("0");
                textViewResult.setText("Your Score: "+score+"/"+attempt);
                score=0;
                attempt=0;
                init();
            }
        }.start();

    }



    public void checkAnswer(View view){
        attempt=attempt+1;
        String userAnswer=view.getTag().toString();
        if (userAnswer.equals(Integer.toString(correctAnsPos))){
            textViewResult.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
            textViewResult.setText("Correct");
            score=score+1;

        }else {
            textViewResult.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
            textViewResult.setText("Wrong");
        }
        textViewScore.setText((score+"/"+attempt));
        prepare();
    }

    public void prepare(){
        Random random=new Random();
        button0.setText(Integer.toString(random.nextInt(40)));
        button1.setText(Integer.toString(random.nextInt(40)));
        button2.setText(Integer.toString(random.nextInt(40)));
        button3.setText(Integer.toString(random.nextInt(40)));
        int a=random.nextInt(21);
        int b=random.nextInt(21);
        textViewQuestion.setText(Integer.toString(a)+"+"+Integer.toString(b));
        int result=a+b;
        correctAnsPos=random.nextInt(4);
        switch (correctAnsPos){
            case 0:
                button0.setText(Integer.toString(result));
                break;
            case 1:
                button1.setText(Integer.toString(result));
                break;
            case 2:
                button2.setText(Integer.toString(result));
                break;
            case 3:
                button3.setText(Integer.toString(result));
                break;
            default:
                break;
        }
    }
}
