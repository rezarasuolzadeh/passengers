package ir.rezarasuolzadeh.passengers.utils.base

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.airbnb.epoxy.EpoxyModel
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

abstract class BaseEpoxy(
    @LayoutRes private val layoutRes: Int
) : EpoxyModel<View>() {

    protected var view: View? = null

    abstract fun bind()

    override fun bind(view: View) {
        this.view = view
        bind()
    }

    override fun unbind(view: View) {
        this.view = null
    }

    private var visibilityChanges: ((ItemVisibilityState) -> Unit)? = null

    fun setVisibilityChanges(call: (ItemVisibilityState) -> Unit): BaseEpoxy {
        visibilityChanges = call
        return this
    }

    override fun getDefaultLayout() = layoutRes

    protected fun <V : View> bind(@IdRes id: Int) = object : ReadOnlyProperty<BaseEpoxy, V> {
        override fun getValue(thisRef: BaseEpoxy, property: KProperty<*>): V {
            // This is not efficient because it looks up the view by id every time (it loses
            // the pattern of a "holder" to cache that look up). But it is simple to use and could
            // be optimized with a map
            @Suppress("UNCHECKED_CAST")
            return view?.findViewById(id) as V?
                ?: throw IllegalStateException("View ID $id for '${property.name}' not found.")
        }
    }

    override fun onVisibilityStateChanged(visibilityState: Int, view: View) {
        super.onVisibilityStateChanged(visibilityState, view)
        visibilityChanges?.invoke(
            when (visibilityState) {
                ItemVisibilityState.VISIBLE.state -> ItemVisibilityState.VISIBLE
                else -> ItemVisibilityState.INVISIBLE
            }
        )
    }

    enum class ItemVisibilityState(val state: Int) {
        VISIBLE(0),
        INVISIBLE(1)
    }
}