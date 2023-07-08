package com.r4ziel.nycschools.network

import com.r4ziel.nycschools.entitiy.School
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.StringTokenizer


/**
 * Created by Jarvis Charles on 7/7/23.
 */

interface SchoolsApi {


    @Headers("Accept: application/json",
            "X-App-Token: rbccFODNzIdbx1bpvXh1LtbMh")
    @GET("s3k6-pzi2.json")
   fun fetchSchools(
//        @Query(value = "\$\$app_token", encoded = true) appKey: String
   ): Observable<Response<List<School>>>

//    @GET("f9bf-2cp4.json")
//   suspend fun fetchSatResults(): List<Sat>
}