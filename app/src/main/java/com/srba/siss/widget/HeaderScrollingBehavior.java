package com.srba.siss.widget;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Scroller;

import com.srba.siss.R;

import java.lang.ref.WeakReference;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/22 10:27
 * 描述:  自定义behavior
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/22       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class HeaderScrollingBehavior extends CoordinatorLayout.Behavior<RecyclerView> {

    private boolean isExpanded = false;
    private boolean isScrolling = false;

    private WeakReference<View> dependentView;
    private Scroller scroller;
    private Handler handler;

    public HeaderScrollingBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
        handler = new Handler();
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    /**
     * 判断child的布局是否依赖dependency
     *
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, RecyclerView child, View dependency) {
        if (dependency != null && dependency.getId() == R.id.scrolling_header) {
            dependentView = new WeakReference<>(dependency);
            return true;
        }
        return false;
    }

    /**
     * 负责对被 Behavior 控制的视图进行布局
     *
     * @param parent
     * @param child
     * @param layoutDirection
     * @return
     */
    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, RecyclerView child, int layoutDirection) {
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        if (lp.height == CoordinatorLayout.LayoutParams.MATCH_PARENT) {
            //设置RecyclerView位置
            child.layout(0, 0, parent.getWidth(), (int) (parent.getHeight() - getDependentViewCollapsedHeight()));
            return true;
        }
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    /**
     * 根据依赖视图进行调整的方法，当依赖视图发生变化时，这个方法就会被调用
     *
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, RecyclerView child, View dependency) {
        Resources resources = getDependentView().getResources();
        //dependency.getHeight() - resources.getDimension(R.dimen.collapsed_header_height)为需要滑动的距离
        //progress为剩余所需滑动距离比例
        final float progress = 1.f -
                Math.abs(dependency.getTranslationY() / (dependency.getHeight() - resources.getDimension(R.dimen.collapsed_header_height)));
        //让RecyclerView随header滑动
        child.setTranslationY(dependency.getHeight() + dependency.getTranslationY());

        //根据进度指定一个缩放比例
        float scale = 1 + 0.4f * (1.f - progress);
        dependency.setScaleX(scale);
        dependency.setScaleY(scale);

        dependency.setAlpha(progress);
        return true;
    }

    /**
     * 用户按下手指时触发，询问 NSP 是否要处理这次滑动操作，如果返回 true 则表示“我要处理这次滑动”，如果返回 false 则表示“我不关心滑动
     *
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes
     * @return
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    /**
     * 当 NSP 接受要处理本次滑动后，这个回调被调用，我们可以做一些准备工作，比如让之前的滑动动画结束。
     *
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes
     */
    @Override
    public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, RecyclerView child, View directTargetChild, View target, int nestedScrollAxes) {
        scroller.abortAnimation();
        isScrolling = false;

        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    /**
     * 当 NSC 即将被滑动时调用，在这里你可以做一些处理。值得注意的是，这个方法有一个参数 int[] consumed，你可以修改这个数组来表示你到底处理掉了多少像素。假设用户滑动了 100px，你做了 90px 的位移，那么就需要把 consumed[1] 改成 90（下标 0、1 分别对应 x、y 轴），这样 NSC 就能知道，然后继续处理剩下的 10px。
     *
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dx
     * @param dy
     * @param consumed
     */
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View target, int dx, int dy, int[] consumed) {
        if (dy < 0) {
            return;
        }

        View dependentView = getDependentView();
        float newTranslateY = dependentView.getTranslationY() - dy;
        float minHeaderTranslate = -(dependentView.getHeight() - getDependentViewCollapsedHeight());

        if (newTranslateY > minHeaderTranslate) {
            dependentView.setTranslationY(newTranslateY);
            consumed[1] = dy;
        }
    }

    /**
     * NSC 处理剩下的距离
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dxConsumed
     * @param dyConsumed
     * @param dxUnconsumed
     * @param dyUnconsumed
     */
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        if (dyUnconsumed > 0) {
            return;
        }

        View dependentView = getDependentView();
        float newTranslateY = dependentView.getTranslationY() - dyUnconsumed;
        final float maxHeaderTranslate = 0;

        if (newTranslateY < maxHeaderTranslate) {
            dependentView.setTranslationY(newTranslateY);
        }
    }

    /**
     * 用户松开手指并且会发生惯性滚动之前调用
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param velocityX
     * @param velocityY
     * @return
     */
    @Override
    public boolean onNestedPreFling(CoordinatorLayout oordinatorLayout, RecyclerView child, View target, float velocityX, float velocityY) {
        return onUserStopDragging(velocityY);
    }

    /**
     * 一切滚动停止后调用，如果不会发生惯性滚动，fling 相关方法不会调用，直接执行到这里。这里我们做一些清理工作，当然有时也要处理中间态问题。
     * @param coordinatorLayout
     * @param child
     * @param target
     */
    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View target) {
        if (!isScrolling) {
            onUserStopDragging(800);
        }
    }

    private boolean onUserStopDragging(float velocity) {
        View dependentView = getDependentView();
        float translateY = dependentView.getTranslationY();
        float minHeaderTranslate = -(dependentView.getHeight() - getDependentViewCollapsedHeight());

        if (translateY == 0 || translateY == minHeaderTranslate) {
            return false;
        }

        boolean targetState; // Flag indicates whether to expand the content.
        if (Math.abs(velocity) <= 800) {
            if (Math.abs(translateY) < Math.abs(translateY - minHeaderTranslate)) {
                targetState = false;
            } else {
                targetState = true;
            }
            velocity = 800; // Limit velocity's minimum value.
        } else {
            if (velocity > 0) {
                targetState = true;
            } else {
                targetState = false;
            }
        }

        float targetTranslateY = targetState ? minHeaderTranslate : 0;

        scroller.startScroll(0, (int) translateY, 0, (int) (targetTranslateY - translateY), (int) (1000000 / Math.abs(velocity)));
        handler.post(flingRunnable);
        isScrolling = true;
        return true;
    }

    /**
     * 获取header折叠高度
     *
     * @return
     */
    private float getDependentViewCollapsedHeight() {
        return getDependentView().getResources().getDimension(R.dimen.collapsed_header_height);
    }

    /**
     * 返回依赖视图
     * @return
     */
    private View getDependentView() {
        return dependentView.get();
    }

    private Runnable flingRunnable = new Runnable() {
        @Override
        public void run() {
            if (scroller.computeScrollOffset()) {
                getDependentView().setTranslationY(scroller.getCurrY());
                handler.post(this);
            } else {
                isExpanded = getDependentView().getTranslationY() != 0;
                isScrolling = false;
            }
        }
    };
}
