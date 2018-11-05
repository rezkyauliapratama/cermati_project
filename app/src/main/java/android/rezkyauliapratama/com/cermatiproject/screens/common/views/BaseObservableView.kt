package android.rezkyauliapratama.com.cermatiproject.screens.common.views

import org.jetbrains.anko.AnkoLogger
import java.util.*

abstract class BaseObservableView<ListenerType> : BaseView(), ObservableView<ListenerType>, AnkoLogger {

    private val mListeners = HashSet<ListenerType>()

    protected val listeners: Set<ListenerType>
        get() = Collections.unmodifiableSet(mListeners)


    override fun registerListener(listener: ListenerType) {
        mListeners.add(listener)
    }

    override fun unregisterListener(listener: ListenerType) {
        mListeners.remove(listener)
    }
}
