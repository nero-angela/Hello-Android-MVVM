package kr.co.devstory.mvvm.view

import kr.co.devstory.mvvm.R
import kr.co.devstory.mvvm.base.BaseActivity
import kr.co.devstory.mvvm.databinding.ActivityMainBinding
import kr.co.devstory.mvvm.viewModel.MainViewModel
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel: MainViewModel by inject()

    override fun initStartView() {
        viewDataBinding.model = viewModel
        viewDataBinding.lifecycleOwner = this

        viewModel.loadData()
    }

    override fun initClickListener() {
        viewDataBinding.btnTest.setOnClickListener {
            viewModel.loadData()
        }
    }
}
