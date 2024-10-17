package com.example.emtask.presentation.ui.components.modifiers

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

internal class CustomItemDecoration(
    private val space: Int,
    private val orientation: CustomItemDecorationOrientation
) : RecyclerView.ItemDecoration() {
    enum class CustomItemDecorationOrientation {
        VERTICAL,
        HORIZONTAL
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position >= 0 && position < state.itemCount - 1) {
            if (orientation == CustomItemDecorationOrientation.VERTICAL)
                outRect.bottom = space
            else outRect.right = space
        }
    }
}
