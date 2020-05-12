package com.ysq.testdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.ysq.testdemo.jetpack.lifecycle.MyObServer;
import com.ysq.testdemo.jetpack.livedata.MutableLiveData;
import com.ysq.testdemo.jetpack.livedata.User;
import com.ysq.testdemo.jetpack.viewmodel.MyViewModel;

import java.util.List;

/**
 * 项目名：MyTestDemo
 * <p>
 * 时 间：2019/11/11
 * <p>
 * 包 名：com.ysq.testdemo
 * <p>
 * 类 名：OtherActivity
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class OtherActivity extends AppCompatActivity implements View.OnClickListener {

    MyObServer mMyObServer;
    private MutableLiveData<Integer> mIntegerMutableLiveData;
    int mInt = 0;

    TextView mNumText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        mMyObServer = new MyObServer(this);
        getLifecycle().addObserver(mMyObServer);

        findViewById(R.id.add_btn).setOnClickListener(this);
        mNumText =  findViewById(R.id.num_txt);

        mIntegerMutableLiveData = new MutableLiveData<>();

//        mIntegerMutableLiveData.observe(this, new Observer<Integer>() {
//            @Override
//            public void onChanged(Integer pInteger) {
//                Toast.makeText(OtherActivity.this, "数字"+pInteger, Toast.LENGTH_SHORT).show();
//                Log.e("OtherActivity", "onChanged: " + pInteger);
//                mNumText.setText(String.valueOf(pInteger));
//            }
//        });

        // 转换数据类型
        tansformationDataType();


        Transformations.switchMap(mIntegerMutableLiveData, new Function<Integer, LiveData<User>>() {
            @Override
            public LiveData<User> apply(Integer useId) {
                return getUser(useId);
            }
        });


        MyViewModel myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        //myViewModel.getUsers().observe(this, );


    }

    private LiveData<User> getUser(Integer pUseId) {
        return  new LiveData<User>() {
            @Override
            public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super User> observer) {
                super.observe(owner, observer);
            }
        };
    }

    private void tansformationDataType() {
        Transformations.map(mIntegerMutableLiveData, new Function<Integer, String>() {

            @Override
            public String apply(Integer input) {
                return null;
            }
        }).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String pIntegerStr) {
                Toast.makeText(OtherActivity.this, "数字"+pIntegerStr, Toast.LENGTH_SHORT).show();
                Log.e("OtherActivity", "onChanged: " + pIntegerStr);
                mNumText.setText(String.valueOf(pIntegerStr));
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.add_btn){
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    while (mInt < 3){
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException pE) {
                            pE.printStackTrace();
                        }
                        mIntegerMutableLiveData.postValue(mInt);
                        mInt ++ ;
                    }
                }
            }.start();
        }
    }
}
