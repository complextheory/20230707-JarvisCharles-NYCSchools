package com.r4ziel.nycschools.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.r4ziel.a20230707_jarvischarles_nycschools.databinding.FragmentDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Jarvis Charles on 7/9/23.
 */
class DetailsFragment: Fragment() {

    private val viewModel: DetailsViewModel by viewModel()

    private lateinit var binding: FragmentDetailsBinding

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }
    
    override fun onResume() {
        super.onResume()
        viewModel.updateSchoolName(args.schoolName)
        viewModel.fetchSatScores()
        observeViewModelLiveData()
    }

    /**
     * ObserveViewModel: Responsible for observing liveData objects from ViewModel
     */
    private fun observeViewModelLiveData(){
        viewModel.satList.observe(viewLifecycleOwner) { satScore ->

            if (satScore.schoolName == "" )
                binding.viewNoDataAvailable.isVisible = true

            binding.tvMathScores.text = satScore.mathScores
            binding.tvReadingScores.text = satScore.readingScores
            binding.tvWritingScores.text = satScore.writingScores
        }
    }
}