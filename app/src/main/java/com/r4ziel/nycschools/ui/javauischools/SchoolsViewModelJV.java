package com.r4ziel.nycschools.ui.javauischools;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.r4ziel.nycschools.entitiy.School;
import com.r4ziel.nycschools.repository.SchoolsRepository;

import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Created by Jarvis Charles on 7/9/23.
 */
public class SchoolsViewModelJV extends ViewModel {
	private SchoolsRepository schoolsRepository;
	public MutableLiveData<List<School>> schoolsLiveData;
	public MutableLiveData<Throwable> errorHandlerLiveData;

	/**
	 * Refresh: Responsible for refresh of schools data from the repository as well as,
	 * initial fetch from onResume
	 */
	public void refreshData() {
		schoolsRepository.getSchools()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(
						it -> {
							try {
								schoolsLiveData.setValue(it);
							} catch (Throwable throwable) {
								errorHandlerLiveData.setValue(throwable);
							}
						},
						it -> {
							errorHandlerLiveData.setValue(it);
							schoolsLiveData.setValue(Collections.emptyList());
						}
				);
	}
}