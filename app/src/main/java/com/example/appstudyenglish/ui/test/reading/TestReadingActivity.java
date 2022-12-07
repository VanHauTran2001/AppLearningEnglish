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
import com.example.appstudyenglish.ui.test.listening.TestListeningActivity;
import com.example.appstudyenglish.ui.test.speaking.SpeakingActivity;

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
                startActivity(new Intent(TestReadingActivity.this, SpeakingActivity.class));
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
        if(dem == 1 ){
            binding.imgQuestion.setVisibility(View.GONE);
        }
        binding.rdDapAn1.setOnClickListener(this);
        binding.rdDapAn2.setOnClickListener(this);
        binding.rdDapAn3.setOnClickListener(this);
        binding.rdDapAn4.setOnClickListener(this);
    }

    private void onClickBackQuestion() {
        if(dem == 0){
            startActivity(new Intent(TestReadingActivity.this,ReadingActivity.class));
        }else {
            dem--;
            setData(cauHoiList.get(dem));
            setRadioButton();
        }
    }

    private void onClickNextQuestion() {
        if(dem == 9){
            if (checkPoint){
                point += 1;
            }
            Toast.makeText(TestReadingActivity.this,"Point : " + point,Toast.LENGTH_SHORT).show();
            startActivity(new Intent(TestReadingActivity.this, SpeakingActivity.class));
        }else {
            if (checkPoint){
                point += 1;
            }
            Toast.makeText(TestReadingActivity.this,"Point : " + point,Toast.LENGTH_SHORT).show();
            dem++;
            setData(cauHoiList.get(dem));
            setRadioButton();
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
        cauTraLoiList.add(new CauTraLoi("Câu trả lời số 1",false));
        cauTraLoiList.add(new CauTraLoi("Câu trả lời số 2",false));
        cauTraLoiList.add(new CauTraLoi("Câu trả lời số 3",true));
        cauTraLoiList.add(new CauTraLoi("Câu trả lời số 4",false));

        cauHoiList = new ArrayList<>();
        cauHoiList.add(new CauHoi(1,"Đây là câu hỏi số 1 : Look at the picture and choose the statement that best describes what you see in the picture ",cauTraLoiList));
        cauHoiList.add(new CauHoi(2,"Đây là câu hỏi số 2 : Look at the picture and choose the statement that best describes what you see in the  ",cauTraLoiList));
        cauHoiList.add(new CauHoi(3,"Đây là câu hỏi số 3 : Look at the picture and choose the statement that best describes what you see in  ",cauTraLoiList));
        cauHoiList.add(new CauHoi(4,"Đây là câu hỏi số 4 : Look at the picture and choose the statement that best describes what you see  ",cauTraLoiList));
        cauHoiList.add(new CauHoi(5,"Đây là câu hỏi số 5 : Look at the picture and choose the statement that best describes what you  ",cauTraLoiList));
        cauHoiList.add(new CauHoi(6,"Đây là câu hỏi số 6 : Look at the picture and choose the statement that best describes what  ",cauTraLoiList));
        cauHoiList.add(new CauHoi(7,"Đây là câu hỏi số 7 : Look at the picture and choose the statement that best describes  ",cauTraLoiList));
        cauHoiList.add(new CauHoi(8,"Đây là câu hỏi số 8 : Look at the picture and choose the statement that best  ",cauTraLoiList));
        cauHoiList.add(new CauHoi(9,"Đây là câu hỏi số 9 : Look at the picture and choose the statement that  ",cauTraLoiList));
        cauHoiList.add(new CauHoi(10,"Đây là câu hỏi số 10 : Look at the picture and choose the statement  ",cauTraLoiList));

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