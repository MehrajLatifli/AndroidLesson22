package com.example.androidlesson22.utilities

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.androidlesson22.R

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun ImageView.loadImageWithoutBindingAdapter(url: String?) {
    Glide
        .with(this)
        .load(url)
        .centerCrop().placeholder(R.drawable.ic_launcher_background)
        .into(this)
}



fun String.highlightName(fragment: Fragment, name: String, colorResId: Int): SpannableString {
    val spannableString = SpannableString(this)
    val start = this.indexOf(name)
    if (start != -1) {
        spannableString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(fragment.requireContext(), colorResId)),
            start,
            start + name.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    return spannableString
}


@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String?) {
    Glide
        .with(this)
        .load(url)
        .centerCrop().placeholder(R.drawable.ic_launcher_background)
        .into(this)
}

@BindingAdapter("load_image_with_id")
fun load_image_with_id(imageView: ImageView, imageId: Int) {

    imageView.setImageResource(imageId)
}

fun loadimagewithidWithoutBindingAdapter(imageView: ImageView, imageId: Int) {

    imageView.setImageResource(imageId)
}



@BindingAdapter("loadImageFromList")
fun loadImageFromList(imageView: ImageView, imageList: List<String>?) {

    imageView.setImageDrawable(null)


    if (!imageList.isNullOrEmpty()) {

        for (imageUrl in imageList) {

            Log.d("ImageSlider", "Loading image: " + imageUrl)

            try {

                Glide.with(imageView.context)
                    .load(imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.ic_launcher_background)
                    .centerCrop()
                    .into(imageView)

            } catch (e: Exception) {
                Log.e("ImageSlider", "Error loading image: " + imageUrl, e);

            }
        }
    } else {

        imageView.setImageResource(R.drawable.ic_launcher_background)
    }
}






@BindingAdapter("setEllipsizedTextIfLong")
fun TextView.setEllipsizedTextIfLong(text: String) {
    if (text.split("\\s+".toRegex()).size >= 10) {
        val words = text.split("\\s+".toRegex()).take(10)
        val truncatedText = words.joinToString(" ")
        this.text = "$truncatedText..."
    } else {
        this.text = text
    }
}