package com.r4ziel.nycschools.ui.schools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.google.android.material.snackbar.Snackbar
import com.r4ziel.a20230707_jarvischarles_nycschools.R
import com.r4ziel.a20230707_jarvischarles_nycschools.databinding.FragmentSchoolsBinding
import com.r4ziel.nycschools.entitiy.School
import com.r4ziel.nycschools.utilities.SchoolClickListener
import com.r4ziel.nycschools.utilities.SnackBarHelper
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.HttpException
import java.io.IOException


/**
 * Created by Jarvis Charles on 7/7/23.
 */
class SchoolsListFragment: Fragment(), SnackBarHelper, View.OnClickListener {

    private val viewModel: SchoolsViewModel by viewModel()
    private lateinit var binding: FragmentSchoolsBinding
    private lateinit var snapHelper: SnapHelper
    private lateinit var snackBar: Snackbar


    private val schoolClickListener = object: SchoolClickListener {
        override fun onSchoolClicked(school: School) {
            Toast.makeText(requireContext(), school.schoolName, Toast.LENGTH_SHORT).show()
             val directions = SchoolsListFragmentDirections.actionSchoolsFragmentToDetailsFragment(school.schoolName)
            binding.root.findNavController().navigate(directions)
        }
    }

    private val schoolsListAdapter = SchoolsListAdapter(schoolClickListener = schoolClickListener)

    override fun onClick(p0: View?) {
        snackBar.dismiss()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        snapHelper = LinearSnapHelper()
        binding = FragmentSchoolsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.rvSchoolList.adapter = schoolsListAdapter
        snapHelper.attachToRecyclerView(binding.rvSchoolList)
        binding.swipRefreshView.setOnRefreshListener {
            viewModel.refresh()
        }

//        lifecycleScope.launch {
//            // repeatOnLifecycle launches the block in a new coroutine every time the
//            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                // Trigger the flow and start listening for values.
//                // Note that this happens when lifecycle is STARTED and stops
//                // collecting when the lifecycle is STOPPED
//
//                viewModel.uiState.collect{ uiState ->
//
//                    when(uiState) {
//                        is SchoolsViewModel.SchoolsUIState.Loading -> {
//                            Log.wtf("Fragment", "UI State Loading")
//                            binding.swipRefreshView.isRefreshing = true
//
//                        }
//                        is SchoolsViewModel.SchoolsUIState.Success -> {
//                            Log.wtf("Fragment", "UI State Success")
//
//                            binding.swipRefreshView.isRefreshing = false
//                            binding.rvSchoolList.isVisible = true
//                            binding.viewNoDataAvailable.isVisible = false
//                            uiState.schools?.let { schoolsListAdapter.update(it) }
//
//                        }
//                        is SchoolsViewModel.SchoolsUIState.Error -> {
//                            Log.wtf("Fragment", "UI State Error")
//
//                            binding.swipRefreshView.isRefreshing = false
//                            showSnackbar(uiState.exception.message)
//                            binding.viewNoDataAvailable.isVisible = true
//                            binding.rvSchoolList.isVisible = false
//                        }
//                    }
//                }
//            }
//        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.swipRefreshView.isRefreshing = true
        viewModel.refresh()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.schoolsLiveData.observe(viewLifecycleOwner) {schools ->  
            binding.swipRefreshView.isRefreshing = false

            if (schools.isEmpty()){
                snackBar = createSnackBar(binding.parentCoordinatorLayout, getString(R.string.something_went_wrong), this)
                snackBar.show()
            }

            binding.viewNoDataAvailable.isVisible = schools.isEmpty()
            binding.rvSchoolList.isVisible = schools.isNotEmpty()
            schoolsListAdapter.update(schools)
        }

        viewModel.errorHandlerLiveData.observe(viewLifecycleOwner) {throwable ->

            binding.swipRefreshView.isRefreshing = false
            binding.viewNoDataAvailable.isVisible = true

            when(throwable) {
                is HttpException -> {
                   showSnackbar(throwable.message)
                }
                is IOException -> {
                    showSnackbar(throwable.message)
                }
                else -> {
                    showSnackbar(getString(R.string.something_went_wrong))
                }
            }
        }
    }

    fun showSnackbar(message: String?) {
        snackBar = createSnackBar(binding.parentCoordinatorLayout, message,this)
        snackBar.show()
    }

}