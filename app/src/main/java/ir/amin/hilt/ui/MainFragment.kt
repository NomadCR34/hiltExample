package ir.amin.hilt.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ir.amin.hilt.R
import ir.amin.hilt.model.Blog
import ir.amin.hilt.util.DataState


private const val TAG = "MYLOG"

@AndroidEntryPoint
class MainFragment
    constructor(
        private val someString :String
    ):Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onCreateView -  Here is String : $someString" )

        text = view.findViewById(R.id.text)
        progress_bar = view.findViewById(R.id.progress_bar)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvent)
    }

        private val viewModel:BlogViewModel by viewModels()
    private lateinit var text: TextView
    private lateinit var progress_bar: ProgressBar


    private fun subscribeObservers(){
        viewModel.dataState.observe(viewLifecycleOwner,{ dataState ->
            when(dataState){
                is DataState.Success<List<Blog>>->{
                    appendBlogTitles(blogs = dataState.data)
                    displayProgressbar(false)
                }
                is DataState.Error<*> ->{
                    displayProgressbar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading ->{
                    displayProgressbar(true)
                }
            }
        })
    }

    private fun displayError(message:String?){
        if(message != null){
            text.text = message
        }else{
            text.text = "Unknown Error"
        }
    }
    private fun displayProgressbar(isDisplayed:Boolean){
        progress_bar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

    private fun appendBlogTitles(blogs: List<Blog>){
        val sb = StringBuilder()
        for(blog in blogs){
            sb.append(blog.title + "\n")
        }
        text.text = sb.toString()
    }
}