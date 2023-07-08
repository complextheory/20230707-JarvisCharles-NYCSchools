package com.r4ziel.nycschools.network

import com.r4ziel.nycschools.entitiy.School
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response


/**
 * Created by Jarvis Charles on 7/7/23.
 */
class SchoolsRemoteDataSource(
    private val schoolsApi: SchoolsApi
){

    private val refreshIntervalMs: Long = 5000

    val schools: Flow<Response<List<School>>> = flow {
        while (true) {
            val response = schoolsApi.fetchSchools("rbccFODNzIdbx1bpvXh1LtbMh")

//            emit(response) // Emits the result of the request to the flow
            delay(refreshIntervalMs) // Suspends the coroutine for some time
        }
    }
}