package ir.rezarasuolzadeh.passengers.view

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyVisibilityTracker
import dagger.hilt.android.AndroidEntryPoint
import ir.rezarasuolzadeh.passengers.databinding.ActivityMainBinding
import ir.rezarasuolzadeh.passengers.model.PassengerModel
import ir.rezarasuolzadeh.passengers.utils.base.BaseActivity
import ir.rezarasuolzadeh.passengers.utils.epoxy.EpoxyLoading
import ir.rezarasuolzadeh.passengers.utils.epoxy.EpoxyPassenger
import ir.rezarasuolzadeh.passengers.utils.epoxy.EpoxyRetry
import ir.rezarasuolzadeh.passengers.utils.extensions.gone
import ir.rezarasuolzadeh.passengers.utils.extensions.visible
import ir.rezarasuolzadeh.passengers.utils.extensions.withLoadMore
import ir.rezarasuolzadeh.passengers.utils.state.ApiState
import ir.rezarasuolzadeh.passengers.viewmodel.PassengerViewModel
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: PassengerViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun configViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun configComponents() {
        configEpoxy()
        configStates()
    }

    override fun configRequests() {
        viewModel.fetchPassengers()
    }

    private fun configStates() {
        lifecycleScope.launchWhenStarted {
            viewModel.passengersStateFlow.collect { state ->
                when (state) {
                    is ApiState.Loading -> {
                        if (state.passengers.isEmpty()) {
                            configLoading()
                        } else {
                            configEpoxyItems(
                                passengers = state.passengers,
                                failed = false,
                                isLoading = true
                            )
                        }
                    }
                    is ApiState.Failure -> {
                        configEpoxyItems(
                            passengers = state.passengers,
                            failed = true
                        )
                    }
                    is ApiState.Success -> {
                        configEpoxyItems(
                            passengers = state.passengers,
                            failed = false
                        )
                    }
                }
            }
        }
    }

    private fun configEpoxy() {
        binding.passengerList.layoutManager = LinearLayoutManager(
            this@MainActivity,
            RecyclerView.VERTICAL,
            false
        )
        EpoxyVisibilityTracker().attach(binding.passengerList)
    }

    private fun configEpoxyItems(
        passengers: List<PassengerModel>?,
        failed: Boolean = false,
        isLoading: Boolean = false
    ) {
        binding.loadingView.gone()
        binding.passengerList.visible()
        passengers?.let {
            binding.passengerList.withModels {
                // passenger view holder
                val models = passengers.map { passenger ->
                    EpoxyPassenger(passenger).apply {
                        id(passenger.id)
                    }
                }
                withLoadMore(models, 10) {
                    if (!isLoading) {
                        viewModel.fetchPassengers()
                    }
                }

                if (failed) {
                    // retry view holder
                    EpoxyRetry().setClickActionsCallBack {
                        viewModel.fetchPassengers()
                    }.apply {
                        id("retry")
                    }.addTo(this)
                } else {
                    // loading view holder
                    EpoxyLoading().apply {
                        id("loading")
                    }.addTo(this)
                }
            }
        }
    }

    private fun configLoading() {
        binding.loadingView.visible()
        binding.passengerList.gone()
    }

}