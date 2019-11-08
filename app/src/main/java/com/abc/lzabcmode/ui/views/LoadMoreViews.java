package com.abc.lzabcmode.ui.views;

import com.abc.lzabcmode.R;
import com.chad.library.adapter.base.loadmore.LoadMoreView;

/**
 * @name lz
 * @time 2019/3/29 14:40
 */
public final class LoadMoreViews extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return R.layout.item_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.barLoading;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.tvLoadFail;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.loadMoreEndLayout;
    }
}
