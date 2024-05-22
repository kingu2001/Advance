package com.example.advance

import android.app.Application
import com.example.advance.model.Graph

class AdvanceApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this
        )
    }
}