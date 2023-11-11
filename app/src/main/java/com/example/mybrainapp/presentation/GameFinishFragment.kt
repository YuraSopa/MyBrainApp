package com.example.mybrainapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mybrainapp.databinding.FragmentGameFinishBinding
import com.example.mybrainapp.domain.entity.GameResult

class GameFinishFragment : Fragment() {
    private lateinit var result: GameResult

    private var _binding: FragmentGameFinishBinding? = null
    private val binding: FragmentGameFinishBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishBinding = null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                retryGame()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, callback
        )
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun retryGame() {
        requireActivity().supportFragmentManager
            .popBackStack(GameFragment.NAME, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    private fun parseArgs() {
        requireArguments().getParcelable<GameResult>(KEY_RESULT)?.let {
            result = it
        }
    }

    companion object {
        private const val KEY_RESULT = "result"
        fun newInstance(gameResult: GameResult): GameFinishFragment {
            return GameFinishFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_RESULT, gameResult)
                }
            }
        }
    }
}