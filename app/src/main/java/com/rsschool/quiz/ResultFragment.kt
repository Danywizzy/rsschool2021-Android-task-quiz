package com.rsschool.quiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.ResultBinding

class ResultFragment: Fragment(){
    private var list: ArrayList<Int> = arrayListOf()
    private lateinit var listener: QuestionFragment.OnQuestionFragmentListener

    private var _binding: ResultBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as QuestionFragment.OnQuestionFragmentListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            list = it.getIntegerArrayList(ARG_RESULT) as ArrayList<Int>
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ResultBinding.inflate(inflater, container, false)

        binding.result.text = getPercent(list)

        binding.shareButton.setOnClickListener {
            sendResult()
        }

        binding.backButton.setOnClickListener {
            listener.setPage(1)
            listener.showFragment(QuestionFragment(), true)
        }

        binding.exitButton.setOnClickListener {
            requireActivity().finish()
        }

        return binding.root
    }

    private fun getPercent(list: ArrayList<Int>): String {
        var res = 0f
        list.forEachIndexed { index, answer ->
            when (answer) {
                questions[index].correct -> res++
            }
        }
        return resources.getString(R.string.result) + " ${(res.div(5) * 100).toInt()}%"
    }

    private fun getResponse(): String {
        var result = ""
        result += getPercent(list) + "\n"
        for (i in questions.indices) {
            result += questions[i].text + "\n"+ "Your answer: " + questions[i].answers[list[i]] + "\n"
        }
        return result
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun sendResult() {
        val message = getResponse()
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, message)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(intent, "Send to your friend!")
        startActivity(shareIntent)
    }

    companion object {
        const val MAX_QUESTIONS = 5
        private const val ARG_RESULT = "ARG_RESULT"

        @JvmStatic
        fun newInstance(list: ArrayList<Int>) =
            ResultFragment().apply {
                arguments = bundleOf(ARG_RESULT to list)
            }
    }
}