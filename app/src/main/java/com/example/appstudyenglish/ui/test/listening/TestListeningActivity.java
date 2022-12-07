package com.example.appstudyenglish.ui.test.listening;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityTestListeningBinding;
import com.example.appstudyenglish.model.AnswerListen;
import com.example.appstudyenglish.model.QuestionListening;
import com.example.appstudyenglish.ui.test.reading.ReadingActivity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestListeningActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityTestListeningBinding binding;
    private MediaPlayer mediaPlayer;
    Handler handler;
    private List<QuestionListening> mListQuestion;
    private int currentQuestion = 0;
    private QuestionListening mQuestion;
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
    }


    private void setDataQuestionListen(QuestionListening questionListening) {
        if (questionListening==null){
            return;
        }
        mQuestion = questionListening;

        binding.txtNumberListen.setText(String.valueOf(questionListening.getNumberListen()));
        binding.txtQuestionListen.setText(questionListening.getQuestionListen());
        binding.radioAnswer1.setText(questionListening.getAnswerListenList().get(0).getAnswerListen());
        binding.radioAnswer2.setText(questionListening.getAnswerListenList().get(1).getAnswerListen());
        binding.radioAnswer3.setText(questionListening.getAnswerListenList().get(2).getAnswerListen());
        binding.radioAnswer4.setText(questionListening.getAnswerListenList().get(3).getAnswerListen());

        binding.radioAnswer1.setOnClickListener(this);
        binding.radioAnswer2.setOnClickListener(this);
        binding.radioAnswer3.setOnClickListener(this);
        binding.radioAnswer4.setOnClickListener(this);
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
//        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                if (b){
//                    mediaPlayer.seekTo(i);
//                    binding.seekBar.setProgress(i);
//                }
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                mediaPlayer.seekTo(binding.seekBar.getProgress());
//            }
//        });

    }

    @Override
    public void onClick(View view) {
       if (binding.radioAnswer1.isChecked()){
           checkAnswer(mQuestion,mQuestion.getAnswerListenList().get(0));

       }else if (binding.radioAnswer2.isChecked()){
           checkAnswer(mQuestion,mQuestion.getAnswerListenList().get(1));

       }else if (binding.radioAnswer3.isChecked()){
           checkAnswer(mQuestion,mQuestion.getAnswerListenList().get(2));

       }else {
           checkAnswer(mQuestion,mQuestion.getAnswerListenList().get(3));

       }
    }
    private void checkAnswer(QuestionListening question , AnswerListen answer){
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

    private void backQuestion() {
        if (currentQuestion == 0){
            mediaPlayer.pause();
            startActivity(new Intent(TestListeningActivity.this,ListeningActivity.class));
        }else {
            currentQuestion--;
            setDataQuestionListen(mListQuestion.get(currentQuestion));
        }
    }

    private void nextQuestion() {
        binding.radioAnswer1.setChecked(false);
        binding.radioAnswer2.setChecked(false);
        binding.radioAnswer3.setChecked(false);
        binding.radioAnswer4.setChecked(false);
        if (currentQuestion == mListQuestion.size()-1){
            mediaPlayer.pause();
            startActivity(new Intent(TestListeningActivity.this,ReadingActivity.class));
        }else {
            currentQuestion++;
            setDataQuestionListen(mListQuestion.get(currentQuestion));
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

    private List<QuestionListening> getListQuestion(){
        List<QuestionListening> questionList = new ArrayList<>();

        List<AnswerListen> answerListenList1 = new ArrayList<>();
        answerListenList1.add(new AnswerListen("Student",false));
        answerListenList1.add(new AnswerListen("Teacher",false));
        answerListenList1.add(new AnswerListen("School",true));
        answerListenList1.add(new AnswerListen("Children",false));

        List<AnswerListen> answerListenList2 = new ArrayList<>();
        answerListenList2.add(new AnswerListen("Car",true));
        answerListenList2.add(new AnswerListen("Chair",false));
        answerListenList2.add(new AnswerListen("Desk",false));
        answerListenList2.add(new AnswerListen("Table",false));

        List<AnswerListen> answerListenList3 = new ArrayList<>();
        answerListenList3.add(new AnswerListen("12",false));
        answerListenList3.add(new AnswerListen("2",false));
        answerListenList3.add(new AnswerListen("4",false));
        answerListenList3.add(new AnswerListen("6",true));

        questionList.add(new QuestionListening(1,"What is your name ?",answerListenList1));
        questionList.add(new QuestionListening(2,"How often do you go to the office ?",answerListenList2));
        questionList.add(new QuestionListening(3,"How many people are there do you have ?",answerListenList3));
        return questionList;
    }

}