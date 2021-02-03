package ir.amin.hilt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
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

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog)

    }


}