package ir.rezarasuolzadeh.passengers.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyVisibilityTracker
import dagger.hilt.android.AndroidEntryPoint
import ir.rezarasuolzadeh.passengers.databinding.ActivityMainBinding
import ir.rezarasuolzadeh.passengers.model.PassengerModel
import ir.rezarasuolzadeh.passengers.utils.epoxy.EpoxyLoading
import ir.rezarasuolzadeh.passengers.utils.epoxy.EpoxyPassenger
import ir.rezarasuolzadeh.passengers.utils.epoxy.EpoxyRetry
import ir.rezarasuolzadeh.passengers.utils.extensions.withLoadMore
import ir.rezarasuolzadeh.passengers.utils.state.ApiState
import ir.rezarasuolzadeh.passengers.viewmodel.PassengerViewModel
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: PassengerViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configEpoxy()

        configStates()

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
                            configEpoxyItems(passengers = state.passengers, failed = false)
                        }
                    }
                    is ApiState.Failure -> {
                        configEpoxyItems(passengers = state.passengers, failed = true)
                    }
                    is ApiState.Success -> {
                        configEpoxyItems(passengers = state.passengers, failed = false)
                    }
                    is ApiState.Empty -> {
                        // noting to do
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

    private fun configEpoxyItems(passengers: List<PassengerModel>?, failed: Boolean = false) {
        binding.loadingView.visibility = View.GONE
        binding.passengerList.visibility = View.VISIBLE
        passengers?.let {
            binding.passengerList.withModels {
                // passenger view holder
                val models = passengers.map { passenger ->
                    EpoxyPassenger(passenger).apply {
                        id(passenger.id)
                    }
                }
                withLoadMore(models, 5) {
                    viewModel.fetchPassengers()
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
        binding.loadingView.visibility = View.VISIBLE
        binding.passengerList.visibility = View.GONE
    }

}