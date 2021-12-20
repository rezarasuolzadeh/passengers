package ir.rezarasuolzadeh.passengers.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ir.rezarasuolzadeh.passengers.R
import ir.rezarasuolzadeh.passengers.viewmodel.PassengerViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: PassengerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.fetchPassengers()
    }

}