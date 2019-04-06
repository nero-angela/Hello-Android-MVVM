package kr.co.devstory.mvvm.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.co.devstory.mvvm.base.BaseViewModel
import kr.co.devstory.mvvm.constant.RANDOM_USER_URL
import kr.co.devstory.mvvm.model.api.GithubApi
import kr.co.devstory.mvvm.model.data.UserResponse

class MainViewModel(private val api: GithubApi) : BaseViewModel() {

    val userList: LiveData<List<UserResponse.User>> get() = _userList
    val progressView: LiveData<Int> get() = _progressView
    val name: LiveData<String> get() = _name

    private val _userList = MutableLiveData<List<UserResponse.User>>()
    private val _progressView = MutableLiveData<Int>()
    private val _name = MutableLiveData<String>()

    fun loadData() {
        addDisposable(
                api.getUserList(RANDOM_USER_URL)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe {
                            showProgress()
                        }
                        .doOnTerminate {
                            hideProgress()
                        }
                        .subscribe({ userResponse ->
                            System.out.println("terminate!!!333")
                            System.out.println(userResponse.toString())
                            _userList.value = userResponse.userList
                            _name.value = userResponse.userList!![0].fullName
                        }, { error ->
                            Log.e("blackJin", error.message)
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