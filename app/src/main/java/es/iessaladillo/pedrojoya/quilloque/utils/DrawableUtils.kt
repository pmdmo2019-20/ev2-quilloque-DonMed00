package es.iessaladillo.pedrojoya.quilloque.utils

import android.graphics.drawable.Drawable
import androidx.core.text.isDigitsOnly
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator

private val drawableBuilder: TextDrawable.IBuilder = TextDrawable.builder()
    .beginConfig()
    .width(32)
    .height(32)
    .toUpperCase()
    .endConfig()
    .round()

fun createAvatarDrawable(text: String): Drawable {
    val firstLetter = text.substring(0, 1)
    return drawableBuilder.build(
        if (firstLetter.isDigitsOnly()) "?" else firstLetter,
        ColorGenerator.MATERIAL.getColor(text)
    )
}
