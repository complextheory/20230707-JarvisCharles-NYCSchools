package com.r4ziel.nycschools.ui.details

import android.os.Bundle
import android.util.Log
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

    lateinit var binding: FragmentDetailsBinding

    private val args: DetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        Log.wtf("Fragment", "School arg name is: ${args.schoolName}")
        return binding.root
    }
    
    override fun onResume() {
        super.onResume()
        viewModel.updateSchoolName(args.schoolName)
        viewModel.fetchSatScores()
        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.satList.observe(viewLifecycleOwner) { satScore ->

            Log.wtf("Details Fragment", "School Name is: ${satScore.schoolName}")
            binding.tvMathScores.text = satScore.mathScores
            binding.tvReadingScores.text = satScore.readingScores
            binding.tvWritingScores.text = satScore.writingScores
        }
    }
}