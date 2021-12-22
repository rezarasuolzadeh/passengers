package ir.rezarasuolzadeh.passengers.utils.epoxy

import com.airbnb.lottie.LottieAnimationView
import ir.rezarasuolzadeh.passengers.R
import ir.rezarasuolzadeh.passengers.utils.base.BaseEpoxy

class EpoxyLoading : BaseEpoxy(R.layout.epoxy_loading) {

    private val loadingView by bind<LottieAnimationView>(R.id.loading_view)

    override fun bind() {
        loadingView.setAnimation(R.raw.loading)
    }

}