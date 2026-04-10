package com.udacity.pawhaven.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import kotlinx.parcelize.Parcelize
import java.util.UUID


abstract class Animal(
    open val name: String,
    open val age: Int,
    open val description: String,
    open val imageRes: Int,
    open val soundRes: Int
) : Parcelable {
    val id: String = UUID.randomUUID().toString()
}


@Parcelize
class Dog(
    override val name: String,
    override val age: Int,
    override val description: String = AnimalType.DOG.defaultDescription(name),
    @DrawableRes override val imageRes: Int = AnimalType.DOG.defaultIconRes,
    @RawRes override val soundRes: Int = AnimalType.DOG.defaultSoundRes
) : Animal(name, age, description, imageRes, soundRes), Parcelable

//TODO Add other animals

