package com.kicist.smartdiabteticcare.helpers

import android.content.Intent
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.kicist.smartdiabteticcare.R
import java.util.concurrent.Callable

object LinkHelper {

    /**
     * Makes links in the given TextView clickable
     * @param textView the TextView containing links
     * @param linkColor the color of the links, default is Color.BLUE
     * @param shouldUnderline whether the link should be underlined, default is true
     */
    fun makeTextClickable(
        textView: TextView,
        handler: Callable<Any>,
        shouldUnderline: Boolean = true,
        linkColor: Int = R.color.colorPrimary
    ) {
        val spannableString = SpannableString(textView.text)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                handler.call()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ContextCompat.getColor(textView.context, linkColor)
                ds.isUnderlineText = shouldUnderline
            }
        }

        spannableString.setSpan(clickableSpan, 0, textView.text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()  // Important to make link clickable
    }
}
