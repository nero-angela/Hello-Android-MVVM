package kr.co.devstory.mvvm.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<T: ViewDataBinding, U: ViewModel>(private val layoutId: Int): AppCompatActivity() {

    protected lateinit var viewDataBinding: T

    abstract val viewModel: U

    abstract fun initStartView()

    abstract fun initClickListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        initStartView()
        initClickListener()
    }
}