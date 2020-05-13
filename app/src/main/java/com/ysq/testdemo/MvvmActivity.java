package com.ysq.testdemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ysq.testdemo.databinding.MvvmDataBinding;
import com.ysq.testdemo.mvvm.Data;
import com.ysq.testdemo.mvvm.DataBindingObserverBean;
import com.ysq.testdemo.mvvm.ImageBean;
import com.ysq.testdemo.mvvm.ImageViewModel;

/**
 * 项目名：MyTestDemo
 * <p>
 * 时 间：2020/5/11
 * <p>
 * 包 名：com.ysq.testdemo.view
 * <p>
 * 类 名：MvvmActivity
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */

public class MvvmActivity  extends AppCompatActivity implements View.OnClickListener {

    private MvvmDataBinding mBinding;
    private ImageViewModel mViewModel;
    private ProgressDialog mProgressDialog;

    private MutableLiveData<String> myOnlyliveData = new MutableLiveData<>();
    private DataBindingObserverBean mBindingObserverBean = new DataBindingObserverBean();

    //测试git回滚

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);
        mViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(ImageViewModel.class);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("加载中");
        mBinding.setObserverBean(mBindingObserverBean);
       // mBinding.setLifecycleOwner(this);
        mViewModel.getImage().observe(this, new androidx.lifecycle.Observer<Data<ImageBean.ImagesBean>>() {
            @Override
            public void onChanged(Data<ImageBean.ImagesBean> imagesBeanData) {
                if (imagesBeanData.getErrorMsg() != null) {
                    Toast.makeText(MvvmActivity.this, imagesBeanData.getErrorMsg(), Toast.LENGTH_SHORT).show();
                    mProgressDialog.dismiss();
                    return;
                }
                // 1. 设置绑定数据
                mBinding.setImagesBean(imagesBeanData.getData());
                mProgressDialog.dismiss();
            }
        });

        findViewById(R.id.btn_next).setOnClickListener(this);
        findViewById(R.id.btn_load).setOnClickListener(this);
        findViewById(R.id.btn_previous).setOnClickListener(this);

        mProgressDialog.show();
        mViewModel.LoadImage();
        addTextWatcher();
    }



    @Override
    protected void onStop() {
        super.onStop();
        setLiveDataText();
        mBindingObserverBean.mObservableField.set("这个DataBinding数据字段添加了观察者");
    }

    private void setLiveDataText() {
        myOnlyliveData.postValue("这是LiveData数据");
        myOnlyliveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String pS) {
                mBinding.setOnlyLiveData(pS);
            }
        });
    }

    @Override
    public void onClick(View v) {
        mProgressDialog.show();
        switch (v.getId()) {
            case R.id.btn_load:
                mViewModel.LoadImage();
                break;
            case R.id.btn_previous:
                mViewModel.previousImage();
                break;
            case R.id.btn_next:
                mViewModel.nextImage();
                break;
            default:
                break;
        }
    }

    private void addTextWatcher() {
        TextView onlyLive = findViewById(R.id.txt_only_live);
        TextView dataBing = findViewById(R.id.txt_obsever);
        onlyLive.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("CHRIS", "这里是仅仅使用LiveData的");
            }
        });
        dataBing.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("CHRIS", "data添加了观察模式的");
            }
        });
    }
}
