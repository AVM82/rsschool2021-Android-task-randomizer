package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment


class FirstFragment : Fragment(R.layout.fragment_first) {

    interface FirstFragmentListener {
        fun onGenerateButtonClicked(min: String, max: String)
        fun getPreviousResult(): Int
    }

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var validRangeText: TextView? = null
    private var editTextMin: EditText? = null
    private var editTextMax: EditText? = null
    private var firstFragmentListener: FirstFragmentListener? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        validRangeText = view.findViewById(R.id.valid_range)
        editTextMin = view.findViewById(R.id.min_value)
        editTextMax = view.findViewById(R.id.max_value)

        val validRange: Range? = arguments?.getParcelable(VALID_RANGE)
        validRangeText?.text =
            getString(R.string.valid_range, validRange?.min ?: 0, validRange?.max ?: 0)

        previousResult?.text = getString(
            R.string.previous_result,
            firstFragmentListener?.getPreviousResult().toString()
        )

        generateButton?.setOnClickListener {
            when {
                editTextMin?.text.toString().isBlank() -> {
                    editTextMin?.error = getString(R.string.min_not_specified)
                }
                editTextMax?.text.toString().isBlank() -> {
                    editTextMax?.error = getString(R.string.max_not_specified)
                }
                else -> {
                    firstFragmentListener?.onGenerateButtonClicked(
                        editTextMin?.text.toString(),
                        editTextMax?.text.toString()
                    )
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FirstFragmentListener) {
            firstFragmentListener = context
        }
    }

    override fun onResume() {
        super.onResume()
        editTextMin?.text?.clear()
        editTextMax?.text?.clear()
    }

    override fun onDetach() {
        super.onDetach()
        firstFragmentListener = null
    }

    companion object {

        @JvmStatic
        fun newInstance(validRange: Range): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putParcelable(VALID_RANGE, validRange)
            fragment.arguments = args
            return fragment
        }

        private const val VALID_RANGE = "VALID_RANGE"
    }
}