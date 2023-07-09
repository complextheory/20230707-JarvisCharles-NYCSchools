package com.r4ziel.nycschools.modules

import com.r4ziel.nycschools.network.NetworkService
import com.r4ziel.nycschools.network.SchoolsApi
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.dsl.module

/**
 * Created by Jarvis Charles on 7/7/23.
 */
val appModule = module {

    val networkService = NetworkService()
    val retrofit = networkService.getRetrofitInstance()
    val schoolsApi = retrofit.create(SchoolsApi::class.java)

    fun provideSchoolsApi() = schoolsApi

    single { provideSchoolsApi() }

}