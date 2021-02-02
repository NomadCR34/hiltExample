package ir.amin.hilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

/**
 * basic example
 * */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(someClass.doAThing())
    }
}

class SomeClass
@Inject
constructor(
    private val someInterface: SomeInterface,
    private val gson: Gson
) {
    fun doAThing(): String {
        return "Look I got: ${someInterface.getThing()}"
    }
}

class SomeInterfaceImpl
@Inject
constructor(
    private val someDependency: String
) : SomeInterface {
    override fun getThing(): String {
        return "A thing $someDependency"
    }

}

interface SomeInterface {
    fun getThing(): String
}

@InstallIn(SingletonComponent::class)//above activity
@Module
class MyModule{

    @Singleton
    @Provides
    fun provideSomeString():String{
        return "some String"
    }

    @Singleton
    @Provides
    fun provideInterfaceImpl(
        someString:String
    ):SomeInterface{
        return SomeInterfaceImpl(someString)
    }

    @Singleton
    @Provides
    fun provideGson():Gson{
        return Gson()
    }


}


