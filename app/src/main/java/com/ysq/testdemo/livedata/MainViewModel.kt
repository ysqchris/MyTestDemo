package com.ysq.testdemo.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*


/**
 * 项目名：MyTestDemo
 *
 * 时 间：2020/1/20
 *
 * 包 名：com.ysq.testdemo.livedata
 *
 * 类 名：MainViewModel
 *
 * 作 者：Yusq
 *
 * 简 述：
 */

class MainViewModel : ViewModel() {

    private val _firstContent = MutableLiveData<String>().apply {
        value = "第一个文本"
    }
    val firstContent: LiveData<String> = _firstContent

    private val _secondContent = MutableLiveData<String>().apply {
        value = "第二个文本"
    }
    val secondContent: LiveData<String> = _secondContent

    // 在主线程更新LiveData对象，调用了MutableLiveData的setValue方法，下面会分析
    fun changeFirstContent(text: String) {
        _firstContent.value = text
    }

//    // 在工作线程更新LiveData对象，调用了MutableLiveData的postValue方法，下面会分析
//    fun changeSecondContent(text: String) =
//            viewModelScope.launch {
//                withContext(Dispatchers.Default) {
//                    _secondContent.postValue(text)
//                }
//            }

    fun testKotilin(){
        println("Start")
        GlobalScope.launch(Dispatchers.IO) {
            delay(1000L)
            println("Hello World")
            println(Thread.currentThread().name)
        }
        println("End")
    }

}