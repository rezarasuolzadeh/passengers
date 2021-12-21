package ir.rezarasuolzadeh.passengers.utils.epoxy

import android.widget.ImageView
import android.widget.TextView
import ir.rezarasuolzadeh.passengers.R
import ir.rezarasuolzadeh.passengers.model.PassengerModel
import ir.rezarasuolzadeh.passengers.utils.extensions.loadImage

class EpoxyPassenger(
    private val passenger: PassengerModel
) : KotlinModel(R.layout.epoxy_passenger) {

    private val airlineImage by bind<ImageView>(R.id.airline_image)
    private val nameText by bind<TextView>(R.id.name_text)
    private val idText by bind<TextView>(R.id.id_text)

    override fun bind() {
        passenger.also {
            nameText.text = it.name
            idText.text = view?.context?.getString(R.string.ticket_id, it.id)
            airlineImage.loadImage(it.airline[0].logo)
        }
    }

}