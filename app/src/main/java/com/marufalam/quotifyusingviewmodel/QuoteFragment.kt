package com.marufalam.quotifyusingviewmodel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.marufalam.quotifyusingviewmodel.data.QuotesModel
import com.marufalam.quotifyusingviewmodel.databinding.FragmentQuoteBinding


class QuoteFragment : Fragment() {
    lateinit var binding: FragmentQuoteBinding
    lateinit var quoteViewMode: QuoteViewMode


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuoteBinding.inflate(inflater, container, false)
        quoteViewMode = ViewModelProvider(
            this,
            QuoteViewModelFactory(requireActivity().applicationContext)
        ).get(QuoteViewMode::class.java)

        setQuote(quoteViewMode.getQuote())

        println("2 ..........................${quoteViewMode.index}")
        println("2 ..........................*")
        if (quoteViewMode.index == 0) {
            binding.previousBtn.visibility = View.INVISIBLE
        }
        binding.nextBtn.setOnClickListener {
            if (quoteViewMode.index < quoteViewMode.listofQuotes.size-1) {
                setQuote(quoteViewMode.nextQuote())
                println("2.2 ..........................${quoteViewMode.index}")
                if (quoteViewMode.index == quoteViewMode.listofQuotes.size-1) {
                    binding.nextBtn.visibility = View.INVISIBLE
                }
                binding.previousBtn.visibility = View.VISIBLE
            } else binding.nextBtn.visibility = View.INVISIBLE

        }

        binding.previousBtn.setOnClickListener {
            if (quoteViewMode.index > 0) {
                setQuote(quoteViewMode.previousQuote())
                println("0.0 ..........................${quoteViewMode.index}")
                binding.nextBtn.visibility = View.VISIBLE
                if (quoteViewMode.index == 0) {
                     binding.previousBtn.visibility = View.INVISIBLE
                     binding.nextBtn.visibility = View.VISIBLE
                }
                else {
                    binding.previousBtn.visibility = View.VISIBLE

                }
            } else binding.previousBtn.visibility = View.INVISIBLE
        }



        return binding.root
    }

    private fun setQuote(quote: QuotesModel) {
        Log.e("TAG", "setQuotetext: ${quote.text}")
        Log.e("TAG", "setQuoteauthor: ${quote.author}")
        binding.quoteText.text = quote.text
        binding.quoteAuthor.text = quote.author

    }

    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, quoteViewMode.getQuote().text)
        startActivity(intent)
    }


}