package ir.rezarasuolzadeh.passengers.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyVisibilityTracker
import dagger.hilt.android.AndroidEntryPoint
import ir.rezarasuolzadeh.passengers.databinding.ActivityMainBinding
import ir.rezarasuolzadeh.passengers.model.PassengerModel
import ir.rezarasuolzadeh.passengers.utils.base.BaseEpoxy
import ir.rezarasuolzadeh.passengers.utils.epoxy.EpoxyLoading
import ir.rezarasuolzadeh.passengers.utils.epoxy.EpoxyPassenger
import ir.rezarasuolzadeh.passengers.utils.extensions.withLoadMore
import ir.rezarasuolzadeh.passengers.viewmodel.PassengerViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: PassengerViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configEpoxy()

        viewModel.passengersLiveData.observe(this, ::observePassengers)

        viewModel.fetchPassengers()
    }

    private fun configEpoxy() {
        binding.passengerList.layoutManager = LinearLayoutManager(
            this@MainActivity,
            RecyclerView.VERTICAL,
            false
        )
        EpoxyVisibilityTracker().attach(binding.passengerList)
    }

    private fun observePassengers(passengers: List<PassengerModel>) {
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

            // loading view holder
            EpoxyLoading().apply {
                id("loading")
            }.addTo(this)
        }
    }

}