package com.example.threadandthreadpool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button Thread
        Button btnThread = findViewById(R.id.btnThread);
        btnThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tạo và khởi chạy một luồng
                Thread thread1 = new WorkerThread("Luồng 1");
                thread1.start();

                //Tạo và khởi chạy một luồng khác
                Thread thread2 = new WorkerThread("Luồng 2");
                thread2.start();

                System.out.println("Main Thread kết thúc.");
            }
        });

        //Button ThreadPool
        Button btnThreadPool = findViewById(R.id.btnThreadPool);
        btnThreadPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tạo một ThreadPool với kích thước cố định là 5
                ExecutorService executor = Executors.newFixedThreadPool(5);

                //Tạo các tác vụ tính toán và gửi chúng vào ThreadPool để thực thi
                for (int i = 0; i < 10; i++){
                    Runnable worker = new WorkerThreadPool("task " + i);
                    executor.execute(worker);
                }
                //Đóng ThreadPool sau khi thực thi xong
                executor.shutdown();
                while (!executor.isTerminated()){
                    //Đợi tất cả các tác vụ hoàn thành
                }
                System.out.println("Tất cả các tác vụ đã hoàn thành");
            }
        });
    }
}