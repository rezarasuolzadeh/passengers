package ir.rezarasuolzadeh.passengers.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.rezarasuolzadeh.passengers.model.PassengerModel
import ir.rezarasuolzadeh.passengers.repository.PassengerRepository
import ir.rezarasuolzadeh.passengers.utils.base.BaseViewModel
import ir.rezarasuolzadeh.passengers.utils.state.ApiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PassengerViewModel @Inject constructor(
    private val repository: PassengerRepository,
) : BaseViewModel() {

    private var currentPassengers: List<PassengerModel> = emptyList()

    private val _passengersStateFlow: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
    val passengersStateFlow: StateFlow<ApiState> = _passengersStateFlow

    fun fetchPassengers() = viewModelScope.launch {
        _passengersStateFlow.value = ApiState.Loading(passengers = currentPassengers)
        delay(3000)
        repository.getPassengers()
            .catch { e ->
                _passengersStateFlow.value =
                    ApiState.Failure(passengers = currentPassengers, msg = e)
            }.collect { data ->
                currentPassengers = data
                _passengersStateFlow.value = ApiState.Success(passengers = data)
            }
    }

}