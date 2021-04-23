package ar.com.postapp.listing.presenter.ui

interface UIComponents<T> {
    fun createView(): T
}
