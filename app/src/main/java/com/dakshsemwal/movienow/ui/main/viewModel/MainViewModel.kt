package com.dakshsemwal.movienow.ui.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dakshsemwal.movienow.data.repository.MainRepository
import com.dakshsemwal.movienow.model.Movie
import com.dakshsemwal.movienow.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    var selectedmovie: MutableLiveData<Movie> = MutableLiveData()


    fun setSelectedMovie(movie: Movie)
    {
        selectedmovie.value= movie
    }

    fun getSelectedMovie():MutableLiveData<Movie>
    {
        return selectedmovie
    }

    fun getMovie() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getMovie()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}