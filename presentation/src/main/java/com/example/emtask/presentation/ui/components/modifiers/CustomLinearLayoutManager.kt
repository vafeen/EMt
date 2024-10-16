package com.example.emtask.presentation.ui.components.modifiers

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

class CustomLinearLayoutManager(
    context: Context,
    orientation: Int,
    reverseLayout: Boolean,
    private val canScrollVertically: Boolean,
    private val canScrollHorizontally: Boolean
) : LinearLayoutManager(context, orientation, reverseLayout) {
    override fun canScrollVertically(): Boolean {
        return canScrollVertically
    }

    override fun canScrollHorizontally(): Boolean {
        return canScrollHorizontally
    }
}