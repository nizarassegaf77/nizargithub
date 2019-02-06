package nizar.id.github.utils

import android.content.Context
import android.graphics.*
import android.graphics.Color.parseColor
import android.opengl.ETC1.getWidth
import android.graphics.drawable.BitmapDrawable
import android.opengl.ETC1.getHeight
import android.graphics.drawable.Drawable
import android.support.annotation.NonNull
import android.util.AttributeSet
import android.widget.ImageView


class CircularImageView : ImageView {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}

    override protected fun onDraw(@NonNull canvas: Canvas) {

        val drawable = getDrawable() ?: return

        if (getWidth() == 0 || getHeight() == 0) {
            return
        }
        val b = (drawable as BitmapDrawable).bitmap
        val bitmap = b.copy(Bitmap.Config.ARGB_8888, true)

        val w = getWidth()/*, h = getHeight( )*/

        val roundBitmap = getCroppedBitmap(bitmap, w)
        canvas.drawBitmap(roundBitmap, 0F, 0F, null)

    }

    private fun getCroppedBitmap(@NonNull bmp: Bitmap, radius: Int): Bitmap {
        val bitmap: Bitmap

        if (bmp.width != radius || bmp.height != radius) {
            val smallest = Math.min(bmp.width, bmp.height).toFloat()
            val factor = smallest / radius
            bitmap = Bitmap.createScaledBitmap(bmp, (bmp.width / factor).toInt(), (bmp.height / factor).toInt(), false)
        } else {
            bitmap = bmp
        }

        val output = Bitmap.createBitmap(radius, radius,
                Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)

        val paint = Paint()
        val rect = Rect(0, 0, radius, radius)

        paint.setAntiAlias(true)
        paint.setFilterBitmap(true)
        paint.setDither(true)
        canvas.drawARGB(0, 0, 0, 0)
        paint.setColor(Color.parseColor("#BAB399"))
        canvas.drawCircle(radius / 2 + 0.7f,
                radius / 2 + 0.7f, radius / 2 + 0.1f, paint)
        paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
        canvas.drawBitmap(bitmap, rect, rect, paint)

        return output
    }

}