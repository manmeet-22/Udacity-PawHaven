package com.udacity.pawhaven.components

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.content.ContextCompat
import com.udacity.pawhaven.R

class SoundPulseView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var radius = 0f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.pawhaven_orange)
        style = Paint.Style.STROKE
        strokeWidth = 8f
    }

    private var animator: ValueAnimator? = null

    fun startAnimation() {
        animator?.cancel()
        animator = ValueAnimator.ofFloat(0f, width.toFloat() / 2).apply {
            duration = 1000
            repeatCount = ValueAnimator.INFINITE
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener {
                radius = it.animatedValue as Float
                paint.alpha = ((1 - (radius / (width / 2))) * 255).toInt()
                invalidate()
            }
            start()
        }
    }

    fun stopAnimation() {
        animator?.cancel()
        radius = 0f
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (radius > 0) {
            canvas.drawCircle(width / 2f, height / 2f, radius, paint)
        }
    }
}