package com.example.xieyipeng.handlerdemo;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DownloadTask extends AsyncTask {

    private static TextView textView;
    private static ProgressBar progressBar;

//    public DownloadTask() {
//
//    }


    public DownloadTask(TextView tv, ProgressBar pb) {
        textView = tv;
        progressBar = pb;
    }


    @Override
    protected Object doInBackground(Object[] objects) {
        textView.setTextColor(Color.BLACK);


        for (int i = 0; i <= 100; i++) {
            try {
                publishProgress(i);
                Thread.sleep((int) objects[0]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        return "下载完毕";
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onProgressUpdate(Object[] values) {
//        super.onProgressUpdate(values);
        progressBar.setProgress(Integer.parseInt(values[0].toString()));
        progressBar.setVisibility(View.VISIBLE);
        textView.setText("已完成： " + values[0] + "%");
    }

    @Override
    protected void onPostExecute(Object o) {
//        super.onPostExecute(o);
        textView.setText((String) o);
        textView.setTextColor(Color.RED);
        progressBar.setVisibility(View.INVISIBLE);
    }
}
