package com.example.appstudyenglish.ui.test.listening;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityTestListeningBinding;
import com.example.appstudyenglish.model.CauHoi;
import com.example.appstudyenglish.model.CauTraLoi;
import com.example.appstudyenglish.ui.test.reading.ReadingActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TestListeningActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityTestListeningBinding binding;
    private MediaPlayer mediaPlayer;
    Handler handler;
    private List<CauHoi> mListQuestion;
    private int currentQuestion = 0;
    private CauHoi mQuestion;
    private static int point = 0;
    private boolean checkPoint;
    private long mTimeInMillis = 300000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_test_listening);
        mediaPlayer = MediaPlayer.create(TestListeningActivity.this,R.raw.filelistening);
        binding.seekBar.setMax(mediaPlayer.getDuration());
        handler = new Handler();
        onClickListener();
        mListQuestion = getListQuestion();
        if (mListQuestion.isEmpty()){
            return;
        }
        setDataQuestionListen(mListQuestion.get(currentQuestion));
        onSetTimeDown();
    }

    private void onSetTimeDown() {
        new CountDownTimer(mTimeInMillis, 1000) {

            public void onTick(long millisUntilFinished) {
                mTimeInMillis = millisUntilFinished;
                int minutes = (int) (mTimeInMillis/1000)/60;
                int seconds = (int) (mTimeInMillis/1000)%60;
                binding.txtTimeListen.setText(String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds));
            }

            public void onFinish() {
                startActivity(new Intent(TestListeningActivity.this,ReadingActivity.class));
            }

        }.start();
    }


    private void setDataQuestionListen(CauHoi questionListening) {
        if (questionListening==null){
            return;
        }
        mQuestion = questionListening;

        binding.txtNumberListen.setText(String.valueOf(questionListening.getStt()));
        binding.txtQuestionListen.setText(questionListening.getTitle());
        binding.radioAnswer1.setText(questionListening.getCauTraLoiList().get(0).getTitleAnswer());
        binding.radioAnswer2.setText(questionListening.getCauTraLoiList().get(1).getTitleAnswer());
        binding.radioAnswer3.setText(questionListening.getCauTraLoiList().get(2).getTitleAnswer());
        binding.radioAnswer4.setText(questionListening.getCauTraLoiList().get(3).getTitleAnswer());

        binding.radioAnswer1.setOnClickListener(this);
        binding.radioAnswer2.setOnClickListener(this);
        binding.radioAnswer3.setOnClickListener(this);
        binding.radioAnswer4.setOnClickListener(this);

        if(questionListening.getDapAnChon() == 1){
            binding.radioAnswer1.setChecked(true);
        }else if(questionListening.getDapAnChon() == 2){
            binding.radioAnswer2.setChecked(true);
        }else if(questionListening.getDapAnChon() == 3){
            binding.radioAnswer3.setChecked(true);
        } else if(questionListening.getDapAnChon() == 4){
            binding.radioAnswer4.setChecked(true);
        }
//        Toast.makeText(this, ""+questionListening.getDapAnChon(), Toast.LENGTH_SHORT).show();
    }


    private void onClickListener() {
        binding.imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer!=null){
                    if (mediaPlayer.isPlaying()){
                        mediaPlayer.pause();
                        binding.imgPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                    }else {
                        binding.imgPlay.setImageResource(R.drawable.ic_baseline_pause_24);
                        mediaPlayer.start();
                        UpdateSeekbar updateSeekbar = new UpdateSeekbar();
                        handler.post(updateSeekbar);
                    }
                }
            }
        });
        binding.imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextQuestion();
            }
        });
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backQuestion();
            }
        });
    }

    @Override
    public void onClick(View view) {
       if (binding.radioAnswer1.isChecked()){
           checkAnswer(mQuestion.getCauTraLoiList().get(0));
       }else if (binding.radioAnswer2.isChecked()){
           checkAnswer(mQuestion.getCauTraLoiList().get(1));
       }else if (binding.radioAnswer3.isChecked()){
           checkAnswer(mQuestion.getCauTraLoiList().get(2));
       }else {
           checkAnswer(mQuestion.getCauTraLoiList().get(3));
       }
    }
    private void checkAnswer(CauTraLoi answer){
        if (answer.isAnswer()){
            checkPoint = true;
        }else {
            checkPoint = false;
        }
    }

    private void backQuestion() {
        setRadioChecked();
        if (currentQuestion == 0){
            mediaPlayer.pause();
            startActivity(new Intent(TestListeningActivity.this,ListeningActivity.class));
        }else {
            currentQuestion--;
            setDataQuestionListen(mListQuestion.get(currentQuestion));
            if(mListQuestion.get(currentQuestion).getDapAnChon() == 0){
                setRadioButton();
            }
        }
    }

    private void nextQuestion() {
        setRadioChecked();
        if (currentQuestion == mListQuestion.size()-1){
            mediaPlayer.pause();
            if (checkPoint){
                point += 1;
                checkPoint = false;
            }
     //       Toast.makeText(TestListeningActivity.this,"Point : " + point,Toast.LENGTH_SHORT).show();
            startActivity(new Intent(TestListeningActivity.this,ReadingActivity.class));
        }else {
            if (checkPoint){
                point += 1;
            }
     //       Toast.makeText(TestListeningActivity.this,"Point : " + point,Toast.LENGTH_SHORT).show();
            currentQuestion++;
            setDataQuestionListen(mListQuestion.get(currentQuestion));
            if(mListQuestion.get(currentQuestion).getDapAnChon() == 0){
                setRadioButton();
            }
        }
    }

    private void setRadioButton(){
        binding.radioAnswer1.setChecked(false);
        binding.radioAnswer2.setChecked(false);
        binding.radioAnswer3.setChecked(false);
        binding.radioAnswer4.setChecked(false);
    }

    private void setRadioChecked(){
        if(binding.radioAnswer1.isChecked()){
            mListQuestion.get(currentQuestion).setDapAnChon(1);
        }else if(binding.radioAnswer2.isChecked()){
            mListQuestion.get(currentQuestion).setDapAnChon(2);
        } else if(binding.radioAnswer3.isChecked()){
            mListQuestion.get(currentQuestion).setDapAnChon(3);
        } else if(binding.radioAnswer4.isChecked()){
            mListQuestion.get(currentQuestion).setDapAnChon(4);
        }
    }

    public class UpdateSeekbar implements Runnable{

        @Override
        public void run() {
            binding.seekBar.setProgress(mediaPlayer.getCurrentPosition()*3);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
            binding.timeMP3.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
            handler.postDelayed(this,100);
        }
    }

    private List<CauHoi> getListQuestion(){
        List<CauHoi> questionList = new ArrayList<>();
        //cau1
        List<CauTraLoi> answerListenList1 = new ArrayList<>();
        answerListenList1.add(new CauTraLoi("Student",false));
        answerListenList1.add(new CauTraLoi("Teacher",false));
        answerListenList1.add(new CauTraLoi("School",true));
        answerListenList1.add(new CauTraLoi("Children",false));
        //cau2
        List<CauTraLoi> answerListenList2 = new ArrayList<>();
        answerListenList2.add(new CauTraLoi("Car",true));
        answerListenList2.add(new CauTraLoi("Chair",false));
        answerListenList2.add(new CauTraLoi("Desk",false));
        answerListenList2.add(new CauTraLoi("Table",false));
        //cau3
        List<CauTraLoi> answerListenList3 = new ArrayList<>();
        answerListenList3.add(new CauTraLoi("12",false));
        answerListenList3.add(new CauTraLoi("2",false));
        answerListenList3.add(new CauTraLoi("4",false));
        answerListenList3.add(new CauTraLoi("6",true));

        //cau4
        List<CauTraLoi> answerListenList4= new ArrayList<>();
        answerListenList4.add(new CauTraLoi("Finish",false));
        answerListenList4.add(new CauTraLoi("Cat",false));
        answerListenList4.add(new CauTraLoi("Dog",true));
        answerListenList4.add(new CauTraLoi("Birth",false));
        //cau5
        List<CauTraLoi> answerListenList5 = new ArrayList<>();
        answerListenList5.add(new CauTraLoi("Summer",true));
        answerListenList5.add(new CauTraLoi("Spring",false));
        answerListenList5.add(new CauTraLoi("Auturm",false));
        answerListenList5.add(new CauTraLoi("Winter",false));
        //cau6
        List<CauTraLoi> answerListenList6 = new ArrayList<>();
        answerListenList6.add(new CauTraLoi("12",false));
        answerListenList6.add(new CauTraLoi("2",false));
        answerListenList6.add(new CauTraLoi("4",false));
        answerListenList6.add(new CauTraLoi("6",true));

        //cau7
        List<CauTraLoi> answerListenList7 = new ArrayList<>();
        answerListenList7.add(new CauTraLoi("Information Technology",false));
        answerListenList7.add(new CauTraLoi("Data Sceince",false));
        answerListenList7.add(new CauTraLoi("Big Data",true));
        answerListenList7.add(new CauTraLoi("Block Chain",false));
        //cau8
        List<CauTraLoi> answerListenList8 = new ArrayList<>();
        answerListenList8.add(new CauTraLoi("Android",true));
        answerListenList8.add(new CauTraLoi("IOS",false));
        answerListenList8.add(new CauTraLoi("React Native",false));
        answerListenList8.add(new CauTraLoi("Flutter",false));
        //cau9
        List<CauTraLoi> answerListenList9 = new ArrayList<>();
        answerListenList9.add(new CauTraLoi("Book",false));
        answerListenList9.add(new CauTraLoi("Pen",false));
        answerListenList9.add(new CauTraLoi("Door",false));
        answerListenList9.add(new CauTraLoi("Color",true));

        //cau10
        List<CauTraLoi> answerListenList10 = new ArrayList<>();
        answerListenList10.add(new CauTraLoi("Dentis",false));
        answerListenList10.add(new CauTraLoi("Lock",false));
        answerListenList10.add(new CauTraLoi("Jonh",false));
        answerListenList10.add(new CauTraLoi("Swim",true));

        questionList.add(new CauHoi(1,"What is your name ?",answerListenList1,0));
        questionList.add(new CauHoi(2,"How often do you go to the office ?",answerListenList2,0));
        questionList.add(new CauHoi(3,"How many people are there do you have ?",answerListenList3,0));
        questionList.add(new CauHoi(4,"What is your name ?",answerListenList4,0));
        questionList.add(new CauHoi(5,"How often do you go to the office ?",answerListenList5,0));
        questionList.add(new CauHoi(6,"How many people are there do you have ?",answerListenList6,0));
        questionList.add(new CauHoi(7,"What is your name ?",answerListenList7,0));
        questionList.add(new CauHoi(8,"How often do you go to the office ?",answerListenList8,0));
        questionList.add(new CauHoi(9,"How many people are there do you have ?",answerListenList9,0));
        questionList.add(new CauHoi(10,"How many people are there do you have ?",answerListenList10,0));
        return questionList;
    }

}