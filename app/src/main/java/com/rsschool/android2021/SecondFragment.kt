package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class SecondFragment : Fragment(R.layout.fragment_second) {

    interface BackButtonClickListener {
        fun onBackButtonClicked()
    }

    private var backButton: Button? = null
    private var result: TextView? = null
    private var backButtonClickListener: BackButtonClickListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)

        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0

        result?.text = generate(min, max).toString()

        backButton?.setOnClickListener {
            backButtonClickListener?.onBackButtonClicked()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BackButtonClickListener) {
            backButtonClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        backButtonClickListener = null
    }

    private fun generate(min: Int, max: Int): Int {
        // TODO: generate random number
        return 25
    }

    companion object {

        @JvmStatic
        fun newInstance(range: Range): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()

            //TODO: implement adding arguments

            return fragment
        }

        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }
}