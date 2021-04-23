package ar.com.postapp.listing.view

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout.LayoutParams
import android.widget.ImageView
import ar.com.postapp.R
import ar.com.postapp.listing.presenter.ui.UIComponents

class EmptyUI(private val context: Context): UIComponents<View> {
    override fun createView(): View {
        val imageView = ImageView(context)
        imageView.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER)
        imageView.setImageResource(R.mipmap.ic_launcher)
        imageView.visibility = View.GONE
        return imageView
    }
}
