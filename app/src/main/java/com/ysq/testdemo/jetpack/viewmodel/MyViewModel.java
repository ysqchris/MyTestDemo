package com.ysq.testdemo.jetpack.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ysq.testdemo.jetpack.livedata.User;

import java.util.List;

/**
 * 项目名：MyTestDemo
 * <p>
 * 时 间：2020/3/21
 * <p>
 * 包 名：com.ysq.testdemo.jetpack.viewmodel
 * <p>
 * 类 名：MyViewModel
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class MyViewModel extends ViewModel {

    private MutableLiveData<List<User>> userList;

    public LiveData<List<User>> getUsers(){
        if(userList == null){
            userList = new MutableLiveData<>();
            loadUsersList();
        }
        return userList;
    }

    /**
     * 加载图片
     */
    private void loadUsersList() {

    }

}
