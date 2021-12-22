package ir.rezarasuolzadeh.passengers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.rezarasuolzadeh.passengers.model.PassengerModel
import ir.rezarasuolzadeh.passengers.repository.PassengerRepository
import ir.rezarasuolzadeh.passengers.utils.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PassengerViewModel @Inject constructor(
    private val repository: PassengerRepository,
) : BaseViewModel() {

    private val passengers = MutableLiveData<List<PassengerModel>>()
    val passengersLiveData: LiveData<List<PassengerModel>>
        get() = passengers

    fun fetchPassengers() = viewModelScope.launch(exceptionHandler) {
        passengers.value = (repository.getPassengers())
    }

}