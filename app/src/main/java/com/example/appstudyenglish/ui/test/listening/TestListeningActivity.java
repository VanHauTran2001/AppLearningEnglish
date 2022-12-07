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
import android.widget.SeekBar;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityTestListeningBinding;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class TestListeningActivity extends AppCompatActivity {
    private ActivityTestListeningBinding binding;
    private MediaPlayer mediaPlayer;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_test_listening);
        mediaPlayer = MediaPlayer.create(TestListeningActivity.this,R.raw.filelistening);
        binding.seekBar.setMax(mediaPlayer.getDuration());
        handler = new Handler();
        onClickListener();
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
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                startActivity(new Intent(TestListeningActivity.this,ListeningActivity.class));
            }
        });
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b){
                    mediaPlayer.seekTo(i);
                    binding.seekBar.setProgress(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(binding.seekBar.getProgress());
            }
        });
//        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
//            @Override
//            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
//                double ratio = i/100.0;
//                int buff = (int) (mediaPlayer.getDuration() * ratio);
//                binding.seekBar.setSecondaryProgress(buff);
//            }
//        });

    }
    public class UpdateSeekbar implements Runnable{

        @Override
        public void run() {
            binding.seekBar.setProgress(mediaPlayer.getCurrentPosition());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
            binding.timeMP3.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
            handler.postDelayed(this,100);
        }
    }

}