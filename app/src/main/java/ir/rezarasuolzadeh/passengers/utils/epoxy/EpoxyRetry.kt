package ir.rezarasuolzadeh.passengers.utils.epoxy

import android.widget.Button
import ir.rezarasuolzadeh.passengers.R
import ir.rezarasuolzadeh.passengers.utils.base.BaseEpoxy

class EpoxyRetry : BaseEpoxy(R.layout.epoxy_retry) {

    private val retry by bind<Button>(R.id.retry_button)

    override fun bind() {
        retry.setOnClickListener {
            actionsClick?.invoke(true)
        }
    }

    private var actionsClick: ((Boolean) -> Unit)? = null

    fun setClickActionsCallBack(call: (Boolean) -> Unit): EpoxyRetry {
        actionsClick = call
        return this
    }

}