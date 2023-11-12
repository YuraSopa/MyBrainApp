package com.example.mybrainapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mybrainapp.R
import com.example.mybrainapp.databinding.FragmentGameFinishBinding

class GameFinishFragment : Fragment() {
    private val args by navArgs<GameFinishFragmentArgs>()

    private var _binding: FragmentGameFinishBinding? = null
    private val binding: FragmentGameFinishBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
        showResults()
    }

    private fun showResults() {

        with(binding) {
            if (args.result.winner) {
                emojiResult.setImageResource(R.drawable.ic_smile)
            } else {
                emojiResult.setImageResource(R.drawable.ic_sad)
            }
            tvRequiredAnswers.text = String.format(
                getString(R.string.required_answers),
                args.result.gameSettings.minCountOfRightAnswers
            )
            tvScoreAnswers.text = String.format(
                getString(R.string.your_score),
                args.result.countOfRightAnswers
            )
            tvRequiredPercentage.text = String.format(
                getString(R.string.required_percentage_answers),
                args.result.gameSettings.minPercentOfRightAnswers
            )
            tvScorePercentage.text = String.format(
                getString(R.string.percent_correct_answers),
                gerPercentOfRightAnswers()
            )
        }
    }

    private fun gerPercentOfRightAnswers() = with(args.result) {
        if (countOfRightAnswers == 0) {
            return 0
        } else {
            ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
        }
    }

    private fun setupClickListeners() {
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }

}