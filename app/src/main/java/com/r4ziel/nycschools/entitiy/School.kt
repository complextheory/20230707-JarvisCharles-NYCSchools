package com.r4ziel.nycschools.entitiy

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/**
 * Created by Jarvis Charles on 7/7/23.
 */

@Parcelize
@JsonClass(generateAdapter = true)
data class School (
    @Json(name = "dbn")
    val dbn: String?,
    @Json(name = "school_name")
    val schoolName: String?,
    @Json(name = "boro")
    val boro: String?,
    @Json(name = "overview_paragraph")
    val overviewParagraph: String?,
    @Json(name = "school_10th_seats")
    val school10thSeats: String?,
    @Json(name = "academicopportunities1")
    val academicOpportunities1: String?,
    @Json(name = "academicopportunities2")
    val academicOpportunities2: String?,
    @Json(name = "academicopportunities3")
    val academicOpportunities3: String?,
    @Json(name = "academicopportunities4")
    val academicOpportunities4: String?,
    @Json(name = "academicopportunities5")
    val academicOpportunities5: String?,
    @Json(name = "ell_programs")
    val ellPrograms: String?,
    @Json(name = "language_classes")
    val languageClasses: String?,
    @Json(name = "diplomaendorsements")
    val diplomaEndorsements: String?,
    @Json(name = "neighborhood")
    val neighborhood: String?,
    @Json(name = "shared_space")
    val sharedSpace: String?,
    @Json(name = "campus_name")
    val campusName: String?,
    @Json(name = "building_code")
    val buildingCode: String?,
    @Json(name = "location")
    val location: String?,
    @Json(name = "phone_number")
    val phoneNumber: String?,
    @Json(name = "fax_number")
    val faxNumber: String?,
    @Json(name = "school_email")
    val schoolEmail: String?,
    @Json(name = "website")
    val website: String?,
    @Json(name = "subway")
    val subway: String?,
    @Json(name = "bus")
    val bus: String?,
    @Json(name = "grades2018")
    val grades2018: String?,
    @Json(name = "finalgrades")
    val finalGrades: String?,
    @Json(name = "total_students")
    val totalStudents: String?,
    @Json(name = "start_time")
    val startTime: String?,
    @Json(name = "end_time")
    val endTime: String?,
    @Json(name= "addtl_info1")
    val additionalInfo: String?,
    @Json(name = "extracurricular_activities")
    val extraCurricularActivities: String?,
    @Json(name = "psal_sports_boys")
    val psalSportsBoys: String?,
    @Json(name = "psal_sports_girls")
    val psalSportsGirls: String?,
    @Json(name = "psal_sports_coed")
    val psalSportsCoed: String?,
    @Json(name = "school_sports")
    val schoolSports: String?,
    @Json(name = "graduation_rate")
    val graduationRate: String?,
    @Json(name = "attendance_rate")
    val attendanceRate: String?,
    @Json(name = "pct_stu_enough_variety")
    val pctStuEnoughVariety: String?,
    @Json(name = "college_career_rate")
    val collegeCareerRate: String?,
    @Json(name = "pct_stu_safe")
    val pctStuSafe: String?,
    @Json(name = "pbat")
    val pbat: String?,
    @Json(name = "international")
    val international: String?,
    @Json(name = "school_accessibility_description")
    val schoolAccessibilityDescription: String?,
    @Json(name = "program1")
    val program1: String?,
    @Json(name = "code1")
    val code1: String?,
    @Json(name = "interest1")
    val interest1: String?,
    @Json(name = "method1")
    val method1: String?,
    @Json(name = "seats9ge1")
    val seats9e1: String?,
    @Json(name = "grade9gefilledflag1")
    val grade9GeFilledFlag1: String?,
    @Json(name = "grade9geapplicants1")
    val grade9GeApplicants1: String?,
    @Json(name = "seats9swd1")
    val seats9Swd1: String?,
    @Json(name = "grade9swdfilledflag1")
    val seats9SwdFilledFlag1: String?,
    @Json(name = "grade9swdapplicants1")
    val seats9SwdApplicants1: String?,
    @Json(name = "seats101")
    val seats101: String?,
    @Json(name = "eligibility1")
    val eligibility1: String?,
    @Json(name = "grade9geapplicantsperseat1")
    val grade9GeApplicantsPerSeat1: String?,
    @Json(name = "grade9swdapplicantsperseat1")
    val grade9SwdApplicantsPerSeat1: String?,
    @Json(name = "primary_address_line_1")
    val primaryAddressLine1: String?,
    @Json(name = "city")
    val city: String?,
    @Json(name = "zip")
    val zip: String?,
    @Json(name = "state_code")
    val statCode: String?,
    @Json(name = "latitude")
    val latitude: String?,
    @Json(name = "longitude")
    val longitude: String?,
    @Json(name = "community_board")
    val communityBoard: String?,
    @Json(name = "council_district")
    val councilDistrict: String?,
    @Json(name = "census_tract")
    val censusTract: String?,
    @Json(name = "bin")
    val bin: String?,
    @Json(name = "bbl")
    val bbl: String?,
    @Json(name = "nta")
    val nta: String?,
    @Json(name = "borough")
    val borough: String?,
    ): Parcelable
