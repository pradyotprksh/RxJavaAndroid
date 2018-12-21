package com.example.a3embed.rxjavaandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private EditText valueEt;
    private TextView showData;
    private TextView showData2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valueEt = findViewById(R.id.valueEt);
        Button getDataB = findViewById(R.id.getDataB);
        showData = findViewById(R.id.showData);
        showData2 = findViewById(R.id.showData2);

        getDataB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAndShowData();
            }
        });

    }

    private void getAndShowData() {
        String value = valueEt.getText().toString();
        Observable.just(value).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                showData.setText(s);
            }

            @Override
            public void onError(Throwable t) {
                showData.setText(t.getMessage());
            }

            @Override
            public void onComplete() {
                String newVal = showData.getText().toString() + "is the string we got.";
                Observable.just(newVal).subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        showData2.setText(s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        valueEt.setText("");
                    }
                });
            }
        });

    }

}
