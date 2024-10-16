package com.example.emtask.presentation.noui

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatchers {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}