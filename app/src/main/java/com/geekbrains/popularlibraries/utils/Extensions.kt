package com.geekbrains.popularlibraries.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.geekbrains.popularlibraries.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

fun ImageView.loadImage(url: String?) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_avatar_placeholder)
        .into(this)
}

fun <T> Single<T>.subscribeByDefault(): Single<T> {
    return this
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun Disposable.disposeBy(bag: CompositeDisposable) {
    bag.add(this)
}

fun View.toggleVisibility(isVisible: Boolean) {
    if (isVisible) this.visibility = View.VISIBLE
    else this.visibility = View.GONE
}

fun <T> Single<T>.doCompletableIf(
    predicate: Boolean,
    completableCreator: (data: T) -> Completable
): Single<T> {
    return if (predicate) {
        this.flatMap {
            completableCreator(it).andThen(Single.just(it))
        }
    } else {
        this
    }
}