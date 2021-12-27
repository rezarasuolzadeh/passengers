package ir.rezarasuolzadeh.passengers.utils.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configViewBinding()
        configComponents()
        configRequests()
    }

    abstract fun configViewBinding()
    abstract fun configComponents()
    abstract fun configRequests()

}