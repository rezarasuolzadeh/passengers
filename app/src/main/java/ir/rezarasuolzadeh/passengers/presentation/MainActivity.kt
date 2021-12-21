package ir.rezarasuolzadeh.passengers.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ir.rezarasuolzadeh.passengers.databinding.ActivityMainBinding
import ir.rezarasuolzadeh.passengers.model.PassengerModel
import ir.rezarasuolzadeh.passengers.utils.epoxy.EpoxyPassengerController
import ir.rezarasuolzadeh.passengers.viewmodel.PassengerViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: PassengerViewModel by viewModels()

    private val controller by lazy { EpoxyPassengerController() }

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
        binding.passengerList.apply {
            adapter = controller.adapter
            binding.passengerList.layoutManager = LinearLayoutManager(
                this@MainActivity,
                RecyclerView.VERTICAL,
                false
            )
        }
    }

    private fun observePassengers(passengers: List<PassengerModel>) {
        controller.setData(passengers)
    }

}