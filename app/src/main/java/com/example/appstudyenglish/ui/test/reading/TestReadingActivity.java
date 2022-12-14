package com.example.appstudyenglish.ui.test.reading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityTestReadingBinding;
import com.example.appstudyenglish.model.CauHoi;
import com.example.appstudyenglish.model.CauTraLoi;
import com.example.appstudyenglish.model.KhoaHoc;
import com.example.appstudyenglish.sqlite.SQLiteHelper;
import com.example.appstudyenglish.ui.test.listening.TestListeningActivity;
import com.example.appstudyenglish.ui.test.speaking.SpeakingActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TestReadingActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityTestReadingBinding binding;
    private List<CauHoi> cauHoiList;
    private List<CauTraLoi> cauTraLoiList;
    private int dem = 0;
    private static int point = 0;
    private boolean checkPoint;
    private long mTimeInMillis = 300000;
    private CauHoi mQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_test_reading);
        cauHoiList = getData();
        cauTraLoiList = new ArrayList<>();
        if (cauHoiList.isEmpty()){
            return;
        }
        setData(cauHoiList.get(dem));
        binding.imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickNextQuestion();
            }
        });
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickBackQuestion();
            }
        });
        onSetTimeDown();
    }
    private void onSetTimeDown() {
        new CountDownTimer(mTimeInMillis, 1000) {
            public void onTick(long millisUntilFinished) {
                mTimeInMillis = millisUntilFinished;
                int minutes = (int) (mTimeInMillis/1000)/60;
                int seconds = (int) (mTimeInMillis/1000)%60;
                binding.txtTimeRead.setText(String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds));
            }
            public void onFinish() {
                Toast.makeText(TestReadingActivity.this,"Bạn đã hết thời gian",Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    private void setData(CauHoi cauHoi) {
        mQuestion = cauHoi;
        binding.txtThuTuCauHoi.setText(dem+1+  "/" +cauHoiList.size());
        binding.txtQuestion.setText(cauHoi.getTitle());
        binding.rdDapAn1.setText(cauHoi.getCauTraLoiList().get(0).getTitleAnswer());
        binding.rdDapAn2.setText(cauHoi.getCauTraLoiList().get(1).getTitleAnswer());
        binding.rdDapAn3.setText(cauHoi.getCauTraLoiList().get(2).getTitleAnswer());
        binding.rdDapAn4.setText(cauHoi.getCauTraLoiList().get(3).getTitleAnswer());
        binding.rdDapAn1.setOnClickListener(this);
        binding.rdDapAn2.setOnClickListener(this);
        binding.rdDapAn3.setOnClickListener(this);
        binding.rdDapAn4.setOnClickListener(this);
        if(cauHoi.getDapAnChon() == 1){
            binding.rdDapAn1.setChecked(true);
        }else if(cauHoi.getDapAnChon() == 2){
            binding.rdDapAn2.setChecked(true);
        }else if(cauHoi.getDapAnChon() == 3){
            binding.rdDapAn3.setChecked(true);
        } else if(cauHoi.getDapAnChon() == 4){
            binding.rdDapAn4.setChecked(true);
        }
    }

    private void onClickBackQuestion() {
        setRadioChecked();
        if(dem == 0){
            startActivity(new Intent(TestReadingActivity.this,ReadingActivity.class));
        }else {
            dem--;
            setData(cauHoiList.get(dem));
            if(cauHoiList.get(dem-1).getDapAnChon() == 0){
                setRadioButton();
                Toast.makeText(this, ""+dem, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void onClickNextQuestion() {
        setRadioChecked();
        if(dem == 9){
            if (checkPoint){
                point += 5;
            }
            savePointAndNextActivity();
        }else {
            if (checkPoint){
                point += 5;
            }
            dem++;
            setData(cauHoiList.get(dem));
            if(cauHoiList.get(dem).getDapAnChon() == 0){
                setRadioButton();
            }
        }
    }

    private void savePointAndNextActivity() {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getApplicationContext(), "Data.sqlite", null, 5);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userID = firebaseUser.getUid();
        startActivity(new Intent(TestReadingActivity.this, SpeakingActivity.class));
        sqLiteHelper.QueryData("UPDATE BaiTest SET DiemDoc='"+point+"' WHERE UserId='"+userID+"'");
    }

    private void setRadioChecked(){
        if(binding.rdDapAn1.isChecked()){
             cauHoiList.get(dem).setDapAnChon(1);
        }else if(binding.rdDapAn2.isChecked()){
            cauHoiList.get(dem).setDapAnChon(2);
        } else if(binding.rdDapAn3.isChecked()){
            cauHoiList.get(dem).setDapAnChon(3);
        } else if(binding.rdDapAn4.isChecked()){
            cauHoiList.get(dem).setDapAnChon(4);
        }
    }

    private void setRadioButton(){
        binding.rdDapAn1.setChecked(false);
        binding.rdDapAn2.setChecked(false);
        binding.rdDapAn3.setChecked(false);
        binding.rdDapAn4.setChecked(false);
    }

    private List<CauHoi> getData() {
        cauTraLoiList = new ArrayList<>();

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

        cauHoiList = new ArrayList<>();
        cauHoiList.add(new CauHoi(1,"What is your name ?",answerListenList1,0));
        cauHoiList.add(new CauHoi(2,"How often do you go to the office ?",answerListenList2,0));
        cauHoiList.add(new CauHoi(3,"How many people are there do you have ?",answerListenList3,0));
        cauHoiList.add(new CauHoi(4,"What is your name ?",answerListenList4,0));
        cauHoiList.add(new CauHoi(5,"How often do you go to the office ?",answerListenList5,0));
        cauHoiList.add(new CauHoi(6,"How many people are there do you have ?",answerListenList6,0));
        cauHoiList.add(new CauHoi(7,"What is your name ?",answerListenList7,0));
        cauHoiList.add(new CauHoi(8,"How often do you go to the office ?",answerListenList8,0));
        cauHoiList.add(new CauHoi(9,"How many people are there do you have ?",answerListenList9,0));
        cauHoiList.add(new CauHoi(10,"How many people are there do you have ?",answerListenList10,0));

        return cauHoiList;
    }

    @Override
    public void onClick(View view) {
        if (binding.rdDapAn1.isChecked()){
            checkAnswer(mQuestion.getCauTraLoiList().get(0));
        }else if (binding.rdDapAn2.isChecked()){
            checkAnswer(mQuestion.getCauTraLoiList().get(1));

        }else if (binding.rdDapAn3.isChecked()){
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
}