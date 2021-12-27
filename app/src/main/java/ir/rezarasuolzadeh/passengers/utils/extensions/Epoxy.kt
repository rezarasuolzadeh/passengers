package ir.rezarasuolzadeh.passengers.utils.extensions

import com.airbnb.epoxy.EpoxyController
import ir.rezarasuolzadeh.passengers.utils.base.BaseEpoxy
import ir.rezarasuolzadeh.passengers.utils.enums.VisibilityState

fun EpoxyController.withLoadMore(
    models: List<BaseEpoxy>?,
    preloadThreshold: Int = 5,
    call: () -> Unit
) {
    var firstCalled = true
    models?.forEachIndexed { index, kotlinModel ->
        kotlinModel.setVisibilityChanges {
            if (models.size - index < preloadThreshold)
                if (it == VisibilityState.VISIBLE && firstCalled) {
                    firstCalled = false
                    call.invoke()
                }
        }.addTo(this)
    }

}