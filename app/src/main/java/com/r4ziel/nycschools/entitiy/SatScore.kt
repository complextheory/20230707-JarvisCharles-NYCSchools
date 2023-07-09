package com.r4ziel.nycschools.entitiy

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/**
 * Created by Jarvis Charles on 7/9/23.
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class SatScore (
    @Json(name = "dbn")
    val dbn: String,

    @Json(name ="school_name")
    val schoolName: String,

    @Json(name = "num_of_sat_test_takers")
    val numOfTestTakers: String,

    @Json(name = "sat_critical_reading_avg_score")
    val readingScores: String,

    @Json(name = "sat_math_avg_score")
    val mathScores: String,

    @Json(name = "sat_writing_avg_score")
    val writingScores: String
): Parcelable