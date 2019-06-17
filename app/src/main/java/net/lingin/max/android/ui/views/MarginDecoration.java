package net.lingin.max.android.ui.views;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.lingin.max.android.utils.PixelUtils;

/**
 * Created by: var_rain.
 * Created date: 2018/12/14.
 * Description: {@link RecyclerView} 的边距调整,适用于 {@link GridLayoutManager}
 * 通过 {@link RecyclerView.LayoutManager#addItemDecoration(RecyclerView.ItemDecoration)} 添加
 */
public class MarginDecoration extends RecyclerView.ItemDecoration {

    /* 边距 */
    private int margin;

    public MarginDecoration(float margin) {
        this.margin = PixelUtils.dp2px(margin);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getChildLayoutPosition(view) % 2 == 0) {
            outRect.set(margin, 0, margin, 0);
        } else {
            outRect.set(0, 0, margin, 0);
        }
    }
}
