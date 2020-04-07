package com.example.islamiclovers;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Collections;

public class QuizActivity extends AppCompatActivity {
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirmNext;

    private final Handler handler = new Handler();
    private ColorStateList buttonLableColor;


    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;

    private ArrayList<Questions> questionList;
    private int questionCounter;
    private int questionTotalCount;
    private Questions currentQuestions;
    private boolean answerd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setupUI();


        fetchDB();
        buttonLableColor=rb1.getTextColors();
    }

    private void setupUI() {
        textViewQuestion = findViewById(R.id.txtQuestionContainer);

        textViewScore = findViewById(R.id.txtScore);
        textViewQuestionCount = findViewById(R.id.txtTotalQuestion);
        textViewCountDown = findViewById(R.id.txtViewTimer);


        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4 = findViewById(R.id.radio_button4);
        buttonConfirmNext = findViewById(R.id.button);
    }

    public void fetchDB() {
        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        startQuiz();


    }

    private void startQuiz() {
        questionTotalCount = questionList.size();
        Collections.shuffle(questionList);
        showQuestions();
        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {//colour change of selected option
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_button1:
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.when_option_selected));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttondesign));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttondesign));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttondesign));
                        break;

                    case R.id.radio_button2:
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.when_option_selected));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttondesign));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttondesign));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttondesign));
                        break;

                    case R.id.radio_button3:
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.when_option_selected));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttondesign));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttondesign));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttondesign));
                        break;

                    case R.id.radio_button4:
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.when_option_selected));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttondesign));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttondesign));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttondesign));
                        break;


                }

            }
        });


        buttonConfirmNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answerd) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                        quizOperations();
                    } else {
                        Toast.makeText(QuizActivity.this, "please select one option", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void quizOperations() {
        answerd =true;
        RadioButton rbselected=findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr=rbGroup.indexOfChild(rbselected) +1;
        checkSolution(answerNr,rbselected);

    }

    private void checkSolution(int answerNr, RadioButton rbselected) {
        switch (currentQuestions.getAnswerNr()){
            case 1:
                if(currentQuestions.getAnswerNr()==answerNr){
                    rb1.setBackground(ContextCompat.getDrawable(this,R.drawable.when_answercorrect_));

                    handler.postDelayed(new Runnable() {//we use handler here to show corect and wrong background colour
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    },1000);

                }else{
                    changetoIncorrectColour(rbselected);
                    handler.postDelayed(new Runnable() {//we use handler here to show corect and wrong background colour
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    },1000);
                }
                break;
            case 2:
                if(currentQuestions.getAnswerNr()==answerNr){
                    rb2.setBackground(ContextCompat.getDrawable(this,R.drawable.when_answercorrect_));

                    handler.postDelayed(new Runnable() {//we use handler here to show corect and wrong background colour
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    },1000);
                }else{
                    changetoIncorrectColour(rbselected);
                    handler.postDelayed(new Runnable() {//we use handler here to show corect and wrong background colour
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    },1000);


                }
                break;
            case 3:
                if(currentQuestions.getAnswerNr()==answerNr){
                    rb3.setBackground(ContextCompat.getDrawable(this,R.drawable.when_answercorrect_));

                    handler.postDelayed(new Runnable() {//we use handler here to show corect and wrong background colour
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    },1000);
                }else{
                    changetoIncorrectColour(rbselected);
                    handler.postDelayed(new Runnable() {//we use handler here to show corect and wrong background colour
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    },1000);


                }
                break;
            case 4:
                if(currentQuestions.getAnswerNr()==answerNr){
                    rb4.setBackground(ContextCompat.getDrawable(this,R.drawable.when_answercorrect_));

                    handler.postDelayed(new Runnable() {//we use handler here to show corect and wrong background colour
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    },1000);
                }else{
                    changetoIncorrectColour(rbselected);
                    handler.postDelayed(new Runnable() {//we use handler here to show corect and wrong background colour
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    },1000);

                }
                break;


        }
        //end of switch
        if (questionCounter ==questionTotalCount) {
            buttonConfirmNext.setText("Confirm and finish");
        }
    }

    private void changetoIncorrectColour(RadioButton rbselected) {
        rbselected.setBackground(ContextCompat.getDrawable(this,R.drawable.when_answer_wrong));


    }
    private void updateCountdown(){

    }

    private void showQuestions(){//show 1st page of questions
        rbGroup.clearCheck();
        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttondesign));
        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttondesign));
        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttondesign));
        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttondesign));


        if(questionCounter < questionTotalCount){
            currentQuestions= questionList.get(questionCounter);
            textViewQuestion.setText(currentQuestions.getQuestion());
            rb1.setText(currentQuestions.getOption1());
            rb2.setText(currentQuestions.getOption2());
            rb3.setText(currentQuestions.getOption3());
            rb4.setText(currentQuestions.getOption4());
            questionCounter++;
            answerd=false;
            buttonConfirmNext.setText("Confirm");
            textViewQuestionCount.setText("Questions:" +questionCounter + "/" + questionTotalCount);

        }else{
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i=new Intent(getApplicationContext(),QuizActivity.class);
                    startActivity(i);
                }
            },1000);
        }
    }
}
