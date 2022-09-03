package com.oheyadam.core.common.ui.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView ItemDecoration that adds a top margin only to the first element in the list
 * */
class ListItemDecoration(
  private val margin: Int
) : RecyclerView.ItemDecoration() {

  override fun getItemOffsets(
    outRect: Rect,
    view: View,
    parent: RecyclerView,
    state: RecyclerView.State
  ) {
    val position = parent.getChildLayoutPosition(view)
    if (position == 0) {
      outRect.top = margin
    }
    outRect.bottom = margin
    outRect.left = margin
    outRect.right = margin
  }
}
