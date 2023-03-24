package com.example.movieapp.views.trivia


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentTriviaMoviesBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TriviaMoviesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TriviaMoviesFragment : Fragment() {

    private lateinit var binding: FragmentTriviaMoviesBinding

    private lateinit var viewModel: TriviaMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_trivia_movies, container, false)

        viewModel = ViewModelProvider(this)[TriviaMoviesViewModel::class.java]

        binding.gameViewModel = viewModel

        binding.btnSendAnswer.setOnClickListener {
            val checkedId = binding.groupAnswers.checkedRadioButtonId

            if (-1 != checkedId) {
                binding.groupAnswers.clearCheck()
                var answerIndex = 0
                when (checkedId) {
                    R.id.answerTwo -> answerIndex = 1
                    R.id.answerThree -> answerIndex = 2
                    R.id.answerFour -> answerIndex = 3
                }
                if (answerIndex == viewModel.currentQuestion.correctOne) {
                    setScore(true, it)
                } else {
                    setScore(false, it)
                }
            }
        }
        return binding.root
    }

    private fun setScore(correctOne: Boolean, view: View){
        viewModel.setScore(correctOne)
        if (viewModel.questionIndex < viewModel.numQuestions) {
            setQuestion()
            binding.invalidateAll()
        } else {
            val action = TriviaMoviesFragmentDirections.actionTriviaMoviesFragmentToScoreFragment(viewModel.score.value.toString())
            view.findNavController().navigate(action)
        }
    }

    private fun setQuestion() {
        viewModel.setQuestion()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_movies_trivia_question,
            viewModel.questionIndex+1, viewModel.numQuestions)
    }
}