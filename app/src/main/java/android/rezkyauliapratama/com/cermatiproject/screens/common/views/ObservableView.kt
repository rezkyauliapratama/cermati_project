package android.rezkyauliapratama.com.cermatiproject.screens.common.views



interface ObservableView<ListenerType> : ViewPattern {

    fun registerListener(listener: ListenerType)

    fun unregisterListener(listener: ListenerType)
}
