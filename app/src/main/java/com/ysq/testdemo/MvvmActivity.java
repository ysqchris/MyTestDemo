package com.ysq.testdemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.ysq.testdemo.databinding.MvvmDataBinding;
import com.ysq.testdemo.mvvm.Data;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);
        mViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(ImageViewModel.class);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("加载中");
        mViewModel.getImage().observe(this, new androidx.lifecycle.Observer<Data<ImageBean.ImagesBean>>() {
            @Override
            public void onChanged(Data<ImageBean.ImagesBean> imagesBeanData) {
                if (imagesBeanData.getErrorMsg() != null) {
                    Toast.makeText(MvvmActivity.this, imagesBeanData.getErrorMsg(), Toast.LENGTH_SHORT).show();
                    mProgressDialog.dismiss();
                    return;
                }
                mBinding.setImagesBean(imagesBeanData.getData());
                setTitle(imagesBeanData.getData().getCopyright());
                mProgressDialog.dismiss();
            }
        });

        findViewById(R.id.btn_next).setOnClickListener(this);

        mProgressDialog.show();
        mViewModel.LoadImage();
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
}
