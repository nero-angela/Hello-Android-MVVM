package kr.co.devstory.mvvm.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.co.devstory.mvvm.adapter.MainAdapter
import kr.co.devstory.mvvm.base.BaseViewModel
import kr.co.devstory.mvvm.constant.RANDOM_USER_URL
import kr.co.devstory.mvvm.model.api.GithubApi
import kr.co.devstory.mvvm.util.networkCommunication

class MainViewModel(
    private val api: GithubApi,
    private val mainAdapter: MainAdapter
) : BaseViewModel() {

    val progressView: LiveData<Int> get() = _progressView
    val adapter: LiveData<MainAdapter> get() = _adapter

    private val _progressView = MutableLiveData<Int>()
    private val _adapter = MutableLiveData<MainAdapter>().apply { value = mainAdapter }

    fun loadData() {
        addDisposable(
            api.getUserList(RANDOM_USER_URL)
                .networkCommunication()
                .doOnSubscribe {
                    showProgress()
                }
                .subscribe({ userResponse ->
                    hideProgress()
                    adapter.value?.setItems(userResponse.userList!!)
                }, { error ->
                    Log.e("error", error.message)
                })
        )
    }

    private fun showProgress() {
        _progressView.value = View.VISIBLE
    }

    private fun hideProgress() {
        _progressView.value = View.INVISIBLE
    }
}