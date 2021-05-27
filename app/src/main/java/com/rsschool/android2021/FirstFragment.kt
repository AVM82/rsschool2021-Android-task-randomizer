package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment


class FirstFragment : Fragment(R.layout.fragment_first) {

    interface GenerateButtonClickListener {
        fun onGenerateButtonClicked(min: String, max: String)
    }

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var editTextMin: EditText? = null
    private var editTextMax: EditText? = null
    private var generateButtonClickListener: GenerateButtonClickListener? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        editTextMin = view.findViewById(R.id.min_value)
        editTextMax = view.findViewById(R.id.max_value)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = getString(R.string.previous_result, result.toString())

        generateButton?.setOnClickListener {
            when {
                editTextMin?.text.toString().isBlank() -> {
                    editTextMin?.error = getString(R.string.min_not_specified)
                }
                editTextMax?.text.toString().isBlank() -> {
                    editTextMax?.error = getString(R.string.max_not_specified)
                }
                else -> {
                    generateButtonClickListener?.onGenerateButtonClicked(
                        editTextMin?.text.toString(),
                        editTextMax?.text.toString()
                    )
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is GenerateButtonClickListener) {
            generateButtonClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        generateButtonClickListener = null
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