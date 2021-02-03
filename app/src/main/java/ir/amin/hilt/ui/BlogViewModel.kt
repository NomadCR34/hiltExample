package ir.amin.hilt.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.amin.hilt.model.Blog
import ir.amin.hilt.repository.MainRepository
import ir.amin.hilt.util.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogViewModel
@Inject
constructor(
    private val mainRepository: MainRepository,
    private val savedStateHandle: SavedStateHandle
):ViewModel(){

    private val _dataState:MutableLiveData<DataState<List<Blog>>> = MutableLiveData()

    val dataState :LiveData<DataState<List<Blog>>>
            get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetBlogEvent ->{
                    mainRepository.getBlog()
                        .onEach { dataState ->  
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                is MainStateEvent.None->{
                    //who cares!
                }
            }
        }
    }
}

sealed class MainStateEvent{
    object  GetBlogEvent:MainStateEvent()
    object  None:MainStateEvent()
}