package com.r4ziel.nycschools.modules

import com.r4ziel.nycschools.ui.schools.SchoolsViewModel
import com.r4ziel.nycschools.ui.details.DetailsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * Created by Jarvis Charles on 7/7/23.
 */
val viewModelModule = module {
    singleOf(::SchoolsViewModel)
    singleOf(::DetailsViewModel)
}