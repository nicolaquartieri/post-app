package ar.com.postapp.listing.view

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.setMargins
import ar.com.postapp.listing.presenter.ui.UIComponents
import ar.com.utils.ViewUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FABElementUI(private val context: Context) : UIComponents<View> {
    override fun createView(): View {
        val fab = FloatingActionButton(context)
        fab.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT,
        ).apply {
            gravity = Gravity.END or Gravity.BOTTOM
            setMargins(ViewUtils.convertDpToPx(context,16F).toInt())
        }
        fab.setImageResource(android.R.drawable.ic_input_add)
        return fab
    }
}
