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
    private lateinit var errorSnackBar: Snackbar

    private val schoolClickListener = object: SchoolClickListener {
        override fun onSchoolClicked(school: School) {
            Toast.makeText(requireContext(), school.schoolName, Toast.LENGTH_SHORT).show()
             val directions = SchoolsListFragmentDirections.actionSchoolsFragmentToDetailsFragment(school.schoolName.toString())
            binding.root.findNavController().navigate(directions)
        }
    }

    private val schoolsListAdapter = SchoolsListAdapter(schoolClickListener = schoolClickListener)

    override fun onClick(p0: View?) {
        errorSnackBar.dismiss()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        snapHelper = LinearSnapHelper()
        binding = FragmentSchoolsBinding.inflate(inflater, container, false)
        binding.rvSchoolList.adapter = schoolsListAdapter
        snapHelper.attachToRecyclerView(binding.rvSchoolList)
        binding.swipeRefreshView.setOnRefreshListener {
            viewModel.refreshData()
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.swipeRefreshView.isRefreshing = true
        viewModel.refreshData()
        observeViewModelLiveData()
    }


    /**
     * ObserveViewModel: Responsible for observing liveData objects from ViewModel
     */
    private fun observeViewModelLiveData() {
        viewModel.schoolsLiveData.observe(viewLifecycleOwner) {schools ->
            binding.swipeRefreshView.isRefreshing = false

            if (schools.isEmpty()){
                errorSnackBar = createSnackBar(binding.parentCoordinatorLayout, getString(R.string.something_went_wrong), this)
                errorSnackBar.show()
            }

            binding.viewNoDataAvailable.isVisible = schools.isEmpty()
            binding.rvSchoolList.isVisible = schools.isNotEmpty()
            schoolsListAdapter.update(schools)
        }

        viewModel.errorHandlerLiveData.observe(viewLifecycleOwner) {throwable ->

            binding.swipeRefreshView.isRefreshing = false
            binding.viewNoDataAvailable.isVisible = true

            when(throwable) {
                is HttpException -> {
                   showErrorSnackBar(throwable.message)
                }
                is IOException -> {
                    showErrorSnackBar(throwable.message)
                }
                else -> {
                    showErrorSnackBar(getString(R.string.something_went_wrong))
                }
            }
        }
    }

    /**
     * ShowSnackBar: Responsible for taking in an error string and displaying the errorSnackbar
     */
    private fun showErrorSnackBar(message: String?) {
        errorSnackBar = createSnackBar(binding.parentCoordinatorLayout, message,this)
        errorSnackBar.show()
    }

}