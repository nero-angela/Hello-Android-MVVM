package kr.co.devstory.mvvm.di

import kr.co.devstory.mvvm.adapter.MainAdapter
import kr.co.devstory.mvvm.constant.BASE_URL
import kr.co.devstory.mvvm.model.api.GithubApi
import kr.co.devstory.mvvm.viewModel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val apiModule = module {

    //GithubAPI
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(GithubApi::class.java)

    }

    // OkHttpClient
    single {
        OkHttpClient.Builder().addInterceptor(get() as HttpLoggingInterceptor).build()

    }

    // HttpLoggingInterceptor
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    }
}

var viewModelModule = module {
    viewModel {
        MainViewModel(get(), get())
    }
}

var adapterModule = module {
    factory {
        MainAdapter()
    }
}

var diModule = listOf(apiModule, viewModelModule, adapterModule)