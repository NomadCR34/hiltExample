package ir.amin.hilt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ir.amin.hilt.R
import ir.amin.hilt.model.Blog
import ir.amin.hilt.retrofit.BlogRetrofit
import ir.amin.hilt.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.lang.Exception
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class BlogActivity : AppCompatActivity() {

    private val viewModel:BlogViewModel by viewModels()
    private lateinit var text:TextView
    private lateinit var progress_bar:ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog)

        text = findViewById(R.id.text)
        progress_bar = findViewById(R.id.progress_bar)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvent)
    }

    private fun subscribeObservers(){
        viewModel.dataState.observe(this,{ dataState ->
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