package com.example.txs.popupwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {
    private PopupWindow popupWindowBottom;
    private PopupWindow popupWindowView;
    private LinearLayout mRootview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRootview = (LinearLayout) findViewById(R.id.rootview);
    }
    public void bottompop(View view) {
        if (popupWindowBottom == null) {
            //跟布局
            View view1 = this.getLayoutInflater().inflate(R.layout.bottom_pop, null);
            /**方法一：
             public PopupWindow (Context context)
             //方法二：
             public PopupWindow(View contentView)
             //方法三：
             public PopupWindow(View contentView, int width, int height)
             //方法四：
             public PopupWindow(View contentView, int width, int height, boolean focusable)  */
            popupWindowBottom = new PopupWindow(view1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            // 设置PopupWindow是否能响应外部点击事件
            popupWindowBottom.setFocusable(true);
            // 设置PopupWindow是否能响应点击事件
            popupWindowBottom.setOutsideTouchable(true);
            // 背景颜色全透明
            ColorDrawable cd = new ColorDrawable(0x00ffffff);
            //PopupWindow确实会处理back键事件，前提是调用了下面的函数：
            popupWindowBottom.setBackgroundDrawable(cd);
            //设置宽高
            WindowManager wm = (WindowManager) this
                    .getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();
            int height = wm.getDefaultDisplay().getHeight();
//            popupWindowBottom.setHeight((int) (height*0.2));
//            popupWindowBottom.setWidth((int) (width));
            //背景阴影效果
            backgroundAlpha(0.7f);
            //动画效果
            popupWindowBottom.setAnimationStyle(R.style.popup);
            popupWindowBottom.setOnDismissListener(new poponDismissListener());
            /**
             //相对某个控件的位置（正左下方），无偏移
             showAsDropDown(View anchor)：
             //相对某个控件的位置，有偏移;xoff表示x轴的偏移，正值表示向左，负值表示向右；yoff表示相对y轴的偏移，正值是向下，负值是向上；
             showAsDropDown(View anchor, int xoff, int yoff)：
             //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
             showAtLocation(View parent, int gravity, int x, int y)：  */
            popupWindowBottom.showAtLocation(mRootview, Gravity.BOTTOM, 0, 0);
        } else {
            backgroundAlpha(0.7f);
            popupWindowBottom.showAtLocation(mRootview, Gravity.BOTTOM, 0, 0);
        }
    }

    public void underlinepop(View view) {
        if (popupWindowView == null) {
            View view1 = this.getLayoutInflater().inflate(R.layout.mypop, null);
            popupWindowView = new PopupWindow(view1, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
            popupWindowView.setFocusable(true);
            popupWindowView.setOutsideTouchable(true);
            ColorDrawable cd = new ColorDrawable(0x00ffffff);// 背景颜色全透明
            popupWindowView.setBackgroundDrawable(cd);
            int height = windowManager.getDefaultDisplay().getWidth();
            int width = windowManager.getDefaultDisplay().getWidth();
            backgroundAlpha(0.7f);
            popupWindowView.setOnDismissListener(new poponDismissListener());
            popupWindowView.showAsDropDown(view);
        } else {
            backgroundAlpha(0.7f);
            /**水平方向：
             ALIGN_LEFT：在锚点内部的左边；
             ALIGN_RIGHT：在锚点内部的右边；
             CENTER_HORI：在锚点水平中部；
             TO_RIGHT：在锚点外部的右边；
             TO_LEFT：在锚点外部的左边。
             垂直方向：
             ALIGN_ABOVE：在锚点内部的上方；
             ALIGN_BOTTOM：在锚点内部的下方；
             CENTER_VERT：在锚点垂直中部；
             TO_BOTTOM：在锚点外部的下方；
             TO_ABOVE：在锚点外部的上方。
             https://images2015.cnblogs.com/blog/830999/201706/830999-20170617100758259-937613284.png*/
            popupWindowView.showAsDropDown(view);
        }
    }
    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        this.getWindow().setAttributes(lp);
    }

    /**
     * pop关闭的事件，主要是为了将背景透明度改回来
     */
    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }
    }
}
