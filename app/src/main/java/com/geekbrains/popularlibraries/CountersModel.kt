package com.geekbrains.popularlibraries

// Пока вынес нашу "БД" сюда, чтобы не размывать во вьюшке логику с SaveInstanceState.
private val counters = mutableListOf(0, 0, 0)

// В следующем уроке будет Moxy, который позволит презентеру переживать пересоздание активити,
// тем самым обеспечит возможность сохранение состояний.
// Была ещё мысль сохранять данные в лайвдате, но не стал заморачиваться, т.к. у нас тут MVP же ведь.

class CountersModel {

    fun getCurrent(position: Int): Int {
        return counters[position]
    }

    fun next(position: Int): Int {
        return ++counters[position]
    }

    fun set(position: Int, value: Int) {
        counters[position] = value
    }
}