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

    lateinit var binding: FragmentDetailsBinding

    private val args: DetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        viewModel.schoolName = args.schoolName
        return binding.root
    }


    override fun onResume() {
        super.onResume()
        viewModel.fetchSatScores()
        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.satList.observe(viewLifecycleOwner) { satScores ->

            binding.tvMathScores.text = satScores[0].mathScores
            binding.tvReadingScores.text = satScores[0].readingScores
            binding.tvWritingScores.text = satScores[0].writingScores
        }
    }
}