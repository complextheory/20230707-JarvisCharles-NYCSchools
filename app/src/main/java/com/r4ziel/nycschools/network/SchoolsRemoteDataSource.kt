package com.r4ziel.nycschools.network

import com.r4ziel.nycschools.entitiy.School
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.Response


/**
 * Created by Jarvis Charles on 7/7/23.
 */
class SchoolsRemoteDataSource(
    private val schoolsApi: SchoolsApi
){

    private val refreshIntervalMs: Long = 5000

    val schools: Flow<Observable<List<School>>> = flow {
//        while (true) {
            val response = schoolsApi.fetchSchools("rbccFODNzIdbx1bpvXh1LtbMh")

//            emit(response) // Emits the result of the request to the flow
//            delay(refreshIntervalMs) // Suspends the coroutine for some time
//        }
    }
}