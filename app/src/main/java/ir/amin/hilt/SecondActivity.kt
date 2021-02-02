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
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * Creating 2 instance of same type
 *
 * */

@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {

    @Inject
    lateinit var someClass: SomeClass2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(someClass.doAThing2())
        println(someClass.doAThing3())
    }
}

class SomeClass2
@Inject
constructor(
    @Impl1 private val someInterface2: SomeInterface2,
    @Impl2 private val someInterface3: SomeInterface2,
) {
    fun doAThing2(): String {
        return "Look I got: ${someInterface2.getThing()}"
    }
    fun doAThing3(): String {
        return "Look I got: ${someInterface3.getThing()}"
    }
}

class SomeInterfaceImpl2
@Inject
constructor() : SomeInterface2 {
    override fun getThing(): String {
        return "A thing2 "
    }

}

class SomeInterfaceImpl3
@Inject
constructor() : SomeInterface2 {
    override fun getThing(): String {
        return "A thing3 "
    }

}

interface SomeInterface2 {
    fun getThing(): String
}

@InstallIn(SingletonComponent::class)//above activity
@Module
class MyModule2{

    @Impl1
    @Singleton
    @Provides
    fun provideInterfaceImpl2():SomeInterface2{
        return SomeInterfaceImpl2()
    }

    @Impl2
    @Singleton
    @Provides
    fun provideInterfaceImpl3():SomeInterface2{
        return SomeInterfaceImpl3()
    }

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl1

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl2

