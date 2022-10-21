package com.marufalam.quotifyusingviewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.marufalam.quotifyusingviewmodel.data.QuotesModel
import java.io.InputStream


class QuoteViewMode(val context: Context) : ViewModel() {
     var index:Int = 0

    var listofQuotes:Array<QuotesModel> = emptyArray()
    init {
        listofQuotes = loadQuotefromAssets()
    }

    private fun loadQuotefromAssets(): Array<QuotesModel> {
        val inputStream: InputStream = context.assets.open("quotes.json") //for access assets folder and open json file
        val size:Int = inputStream.available() //define size
        val buffer = ByteArray(size) //store in buffer
        inputStream.read(buffer) //for read file
        inputStream.close() //after read close the stream

        //~~now Convert json to object~~\\

        val json = String(buffer,Charsets.UTF_8) // byteArray convert to String format
        val gson: Gson = Gson() //create gson object for Convert json to object
        return gson.fromJson(json,Array<QuotesModel>::class.java) // its return Array<QuoteModel>



    }


    fun getQuote() = listofQuotes[index]
    fun nextQuote() = listofQuotes[++index]
    fun previousQuote() = listofQuotes[--index]

}