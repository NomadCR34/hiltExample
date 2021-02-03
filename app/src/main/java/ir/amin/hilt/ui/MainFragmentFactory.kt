package ir.amin.hilt.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import ir.amin.hilt.di.SomeStringFragment
import javax.inject.Inject

class MainFragmentFactory
@Inject
constructor(
    @SomeStringFragment private val someString :String
):FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            //if we have more than 1 fragment we have more case here
            MainFragment::class.java.name->{
                MainFragment(someString)
            }
            else -> super.instantiate(classLoader, className)
        }

    }
}