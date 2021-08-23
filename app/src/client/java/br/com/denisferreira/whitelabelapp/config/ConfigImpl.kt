package br.com.denisferreira.whitelabelapp.config

import android.view.View
import javax.inject.Inject

class ConfigImpl @Inject constructor(): Config {
    override val addButtonVisibility: Int
        get() = View.GONE
}