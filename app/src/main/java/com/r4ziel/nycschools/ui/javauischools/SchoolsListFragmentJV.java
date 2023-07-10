package com.r4ziel.nycschools.ui.javauischools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import com.google.android.material.snackbar.Snackbar;
import com.r4ziel.a20230707_jarvischarles_nycschools.R;
import com.r4ziel.a20230707_jarvischarles_nycschools.databinding.FragmentSchoolsBinding;
import com.r4ziel.nycschools.ui.schools.SchoolsListAdapter;
import com.r4ziel.nycschools.utilities.SchoolClickListener;
import com.r4ziel.nycschools.utilities.SnackBarHelper;

import java.io.IOException;

import retrofit2.HttpException;

/**
 * Created by Jarvis Charles on 7/9/23.
 */
public class SchoolsListFragmentJV extends Fragment implements SnackBarHelper, View.OnClickListener {
	private SchoolsViewModelJV viewModel;
	private FragmentSchoolsBinding binding;
	private SnapHelper snapHelper;
	private Snackbar errorSnackBar;
	private final SchoolClickListener schoolClickListener = school -> {
		Toast.makeText(requireContext(), school.getSchoolName(), Toast.LENGTH_SHORT).show();
//			SchoolsListFragmentDirections.ActionSchoolsFragmentToDetailsFragment directions = SchoolsListFragmentDirections.actionSchoolsFragmentToDetailsFragment(school.getSchoolName().toString());
//			Navigation.findNavController(binding.getRoot()).navigate(directions);
	};
	private final SchoolsListAdapter schoolsListAdapter = new SchoolsListAdapter(schoolClickListener);

	@Override
	public void onClick(View p0) {
		errorSnackBar.dismiss();
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		snapHelper = new LinearSnapHelper ();
		binding = FragmentSchoolsBinding.inflate(inflater, container, false);
		binding.rvSchoolList.setAdapter(schoolsListAdapter);
		viewModel = new ViewModelProvider(this).get(SchoolsViewModelJV.class);
		snapHelper.attachToRecyclerView(binding.rvSchoolList);
		binding.swipeRefreshView.setOnRefreshListener(() -> viewModel.refreshData());
		return binding.getRoot();
	}

	@Override
	public void onResume() {
		super.onResume();
		binding.swipeRefreshView.setRefreshing (true);
		viewModel.refreshData();
		observeViewModelLiveData();
	}

	@NonNull
	@Override
	public Snackbar createSnackBar (@NonNull View view, @Nullable String message, @NonNull View.OnClickListener clickListener) {
		return createSnackBar (view, message, clickListener);
	}

	/**
	 * ObserveViewModel: Responsible for observing liveData objects from ViewModel
	 */
	private void observeViewModelLiveData() {
		viewModel.schoolsLiveData.observe(getViewLifecycleOwner(), schools -> {
			binding.swipeRefreshView.setRefreshing(false);
			if (schools.isEmpty()) {
				errorSnackBar = createSnackBar(binding.parentCoordinatorLayout, getString(R.string.something_went_wrong), this);
				errorSnackBar.show();
			}
			binding.viewNoDataAvailable.setIsVisible (schools.isEmpty());

			if (schools.isEmpty ())
				binding.rvSchoolList.setVisibility (View.INVISIBLE);
			else
				binding.rvSchoolList.setVisibility (View.VISIBLE);


			schoolsListAdapter.update(schools);
		});
		viewModel.errorHandlerLiveData.observe(getViewLifecycleOwner(), throwable -> {
			binding.swipeRefreshView.setRefreshing(false);
			binding.viewNoDataAvailable.setIsVisible (true);
			if (throwable instanceof HttpException) {
				showErrorSnackBar(((HttpException) throwable).getMessage());
			} else if (throwable instanceof IOException) {
				showErrorSnackBar(((IOException) throwable).getMessage());
			} else {
				showErrorSnackBar(getString(R.string.something_went_wrong));
			}
		});
	}

	/**
	 * ShowSnackBar: Responsible for taking in an error string and displaying the errorSnackbar
	 */
	private void showErrorSnackBar(String message) {
		errorSnackBar = createSnackBar (binding.parentCoordinatorLayout, message, this);
	}
}