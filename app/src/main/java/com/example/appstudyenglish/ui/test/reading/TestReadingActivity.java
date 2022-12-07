package com.example.appstudyenglish.ui.test.reading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityTestReadingBinding;
import com.example.appstudyenglish.model.CauHoi;
import com.example.appstudyenglish.model.CauTraLoi;

import java.util.ArrayList;
import java.util.List;

public class TestReadingActivity extends AppCompatActivity {

    private ActivityTestReadingBinding binding;
    private List<CauHoi> cauHoiList;
    private List<CauTraLoi> cauTraLoiList;
    private int dem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_test_reading);
        cauHoiList = new ArrayList<>();
        cauTraLoiList = new ArrayList<>();
        getData();
        setData();
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
    }

    private void setData() {
        CauHoi cauHoi = cauHoiList.get(dem);
        binding.txtThuTuCauHoi.setText(dem+1+  "/" +cauHoiList.size());
        binding.txtQuestion.setText(cauHoi.getTitle());
        binding.rdDapAnA.setText(cauTraLoiList.get(0).getTitle());
        binding.rdDapAnB.setText(cauTraLoiList.get(1).getTitle());
        binding.rdDapAnC.setText(cauTraLoiList.get(2).getTitle());
        binding.rdDapAnD.setText(cauTraLoiList.get(3).getTitle());
        if(dem == 1 ){
            binding.imgQuestion.setVisibility(View.GONE);
        }
    }

    private void onClickBackQuestion() {
        if(dem == 0){
            Toast.makeText(this, "Hết câu hỏi cmnr", Toast.LENGTH_SHORT).show();
        }else {
            dem--;
            setData();
            setRadioButton();
        }
    }

    private void onClickNextQuestion() {
        if(dem == 9){
            Toast.makeText(this, "Next Activity", Toast.LENGTH_SHORT).show();
        }else {
            dem++;
            setData();
            setRadioButton();
        }
    }

    private void setRadioButton(){
        binding.rdDapAnA.setChecked(false);
        binding.rdDapAnB.setChecked(false);
        binding.rdDapAnC.setChecked(false);
        binding.rdDapAnD.setChecked(false);
    }

    private void getData() {
        cauTraLoiList.add(new CauTraLoi("Câu trả lời số 1",0));
        cauTraLoiList.add(new CauTraLoi("Câu trả lời số 2",0));
        cauTraLoiList.add(new CauTraLoi("Câu trả lời số 3",1));
        cauTraLoiList.add(new CauTraLoi("Câu trả lời số 4",0));

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
    }
}