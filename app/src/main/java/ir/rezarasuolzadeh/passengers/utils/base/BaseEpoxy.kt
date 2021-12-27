package ir.rezarasuolzadeh.passengers.utils.base

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.airbnb.epoxy.EpoxyModel
import ir.rezarasuolzadeh.passengers.utils.enums.VisibilityState
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

abstract class BaseEpoxy(
    @LayoutRes private val layoutRes: Int
) : EpoxyModel<View>() {

    abstract fun bind()

    private var visibilityChanges: ((VisibilityState) -> Unit)? = null

    protected var view: View? = null

    override fun bind(view: View) {
        this.view = view
        bind()
    }

    override fun unbind(view: View) {
        this.view = null
    }

    fun setVisibilityChanges(call: (VisibilityState) -> Unit): BaseEpoxy {
        visibilityChanges = call
        return this
    }

    override fun getDefaultLayout() = layoutRes

    protected fun <V : View> bind(@IdRes id: Int) = object : ReadOnlyProperty<BaseEpoxy, V> {
        override fun getValue(thisRef: BaseEpoxy, property: KProperty<*>): V {
            @Suppress("UNCHECKED_CAST")
            return view?.findViewById(id) as V?
                ?: throw IllegalStateException("View ID $id for '${property.name}' not found.")
        }
    }

    override fun onVisibilityStateChanged(visibilityState: Int, view: View) {
        super.onVisibilityStateChanged(visibilityState, view)
        visibilityChanges?.invoke(
            when (visibilityState) {
                VisibilityState.VISIBLE.state -> VisibilityState.VISIBLE
                else -> VisibilityState.INVISIBLE
            }
        )
    }

}