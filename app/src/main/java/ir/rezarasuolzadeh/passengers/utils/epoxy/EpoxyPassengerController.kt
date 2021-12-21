package ir.rezarasuolzadeh.passengers.utils.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import ir.rezarasuolzadeh.passengers.model.PassengerModel

class EpoxyPassengerController : TypedEpoxyController<List<PassengerModel>>() {

    private val TAG = this::class.java.simpleName

    override fun buildModels(data: List<PassengerModel>?) {
        data?.forEachIndexed { index, passenger ->
            EpoxyPassenger(passenger).id(index).addTo(this)
        }
    }
}