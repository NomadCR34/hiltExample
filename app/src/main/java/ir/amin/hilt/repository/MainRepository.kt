package ir.amin.hilt.repository

import android.service.autofill.Dataset
import ir.amin.hilt.model.Blog
import ir.amin.hilt.retrofit.BlogRetrofit
import ir.amin.hilt.retrofit.NetworkMapper
import ir.amin.hilt.room.BlogDao
import ir.amin.hilt.room.CacheMapper
import ir.amin.hilt.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository
constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
){

    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(1000)//NOT IN PRODUCTION!!!!!!!
        try {
            val networkBlogs = blogRetrofit.getData()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for(blog in blogs){
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cacheBlogs = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cacheBlogs)))
        }catch (e:Exception){
            emit(DataState.Error<Exception>(e))
        }
    }


}
