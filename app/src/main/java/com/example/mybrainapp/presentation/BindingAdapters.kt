package com.example.mybrainapp.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.mybrainapp.R
import com.example.mybrainapp.domain.entity.GameResult

interface OnOptionClickListener {
    fun onOptionClick(option: Int)
}


@BindingAdapter("emojiResult")
fun bindRequiredAnswers(imageView: ImageView, winner: Boolean) {
    if (winner) {
        imageView.setImageResource(R.drawable.ic_smile)
    } else {
        imageView.setImageResource(R.drawable.ic_sad)
    }
}

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_answers),
        count
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.your_score),
        count
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage_answers),
        count
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, result: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.percent_correct_answers),
        getPercentOfRightAnswers(result)
    )
}

private fun getPercentOfRightAnswers(result: GameResult) = with(result) {
    if (countOfRightAnswers == 0) {
        return 0
    } else {
        ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
    }
}


@BindingAdapter("enoughCountOfRightAnswers")
fun bindEnoughCountOfRightAnswers(textView: TextView, goodState: Boolean) {
    textView.setTextColor(getColorByState(textView.context, goodState))
}

private fun getColorByState(context: Context, goodState: Boolean): Int {
    val colorResId = if (goodState) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}

@BindingAdapter("enoughPercentOfRightAnswers")
fun bindEnoughPercentOfRightAnswers(progressBar: ProgressBar, goodState: Boolean) {
    val color = getColorByState(progressBar.context, goodState)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

@BindingAdapter("percentOfRightAnswer")
fun bindPercentOfRightAnswer(progressBar: ProgressBar, percent: Int) {
    progressBar.setProgress(percent, true)
}

@BindingAdapter("numberAstext")
fun bindNumberAsText(textView: TextView, number: Number) {
    textView.text = number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener: OnOptionClickListener) {
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}
