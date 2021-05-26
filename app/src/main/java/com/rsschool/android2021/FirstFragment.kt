package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class FirstFragment : Fragment(R.layout.fragment_first) {

    interface GenerateClickListener {
        fun onGenerateButtonClicked(min: Int, max: Int)
    }

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var generateClickListener: GenerateClickListener? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = getString(R.string.previous_result, result.toString())

        val min = 0
        val max = 255

        generateButton?.setOnClickListener {
            generateClickListener?.onGenerateButtonClicked(min, max)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is GenerateClickListener) {
            generateClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        generateClickListener = null
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}