package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class SecondFragment : Fragment(R.layout.fragment_second) {

    interface SecondFragmentListener {
        fun onBackButtonClicked()
        fun passRandomNumber(num: Int)
    }

    private var backButton: Button? = null
    private var result: TextView? = null
    private var secondFragmentListener: SecondFragmentListener? = null
    private var range: Range? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)

        range = arguments?.getParcelable(RANGE)

        result?.text = range?.let {
            val randomNum = generate(it)
            secondFragmentListener?.passRandomNumber(randomNum)
            randomNum.toString()
        }

        backButton?.setOnClickListener {
            secondFragmentListener?.onBackButtonClicked()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SecondFragmentListener) {
            secondFragmentListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        secondFragmentListener = null
    }

    private fun generate(range: Range): Int {
        return RandomGenerator.generate(range)
    }

    companion object {

        @JvmStatic
        fun newInstance(range: Range): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()
            args.putParcelable(RANGE, range)
            fragment.arguments = args
            return fragment
        }

        private const val RANGE = "MIN_VALUE"
    }
}