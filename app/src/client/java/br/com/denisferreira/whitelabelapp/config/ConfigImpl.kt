package br.com.denisferreira.whitelabelapp.config

import android.view.View

class ConfigImpl: Config {
    override val addButtonVisibility: Int
        get() = View.GONE
}