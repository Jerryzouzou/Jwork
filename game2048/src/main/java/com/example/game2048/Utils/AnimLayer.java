package com.example.game2048.Utils;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import com.example.game2048.Model.Card;

/**
 * Created by Administrator on 2018/1/31.
 * description:卡片动画
 */

public class AnimLayer extends FrameLayout {
    public AnimLayer(@NonNull Context context) {
        super(context);
    }

    public AnimLayer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimLayer(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 创建卡片移到效果动画
     */
    public void createMoveAnim(final Card form, final Card to, int fromX, int toX, int fromY,
                               int toY){
        final Card c = getCard(form.getNum());

        LayoutParams lp = new LayoutParams(Config.CARD_WIDTH, Config.CARD_WIDTH);
        lp.leftMargin = fromX*Config.CARD_WIDTH;
        lp.topMargin = fromY*Config.CARD_WIDTH;
        c.setLayoutParams(lp);

        if(to.getNum() <= 0){   //如果卡片是0，隐藏卡片
            to.getLabel().setVisibility(INVISIBLE);
        }

        TranslateAnimation ta = new TranslateAnimation(0, Config.CARD_WIDTH*(toX - fromX),
                0, Config.CARD_WIDTH*(toY - fromY));
        ta.setDuration(100);    //动画时间
        ta.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                to.getLabel().setVisibility(VISIBLE);
                recycleCard(c);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        c.startAnimation(ta);
    }

    /**
     * 回收卡片c
     */
    private void recycleCard(Card c) {
        c.setVisibility(INVISIBLE);
        c.setAnimation(null);
    }

    /**
     * 拷贝卡片，获取拷贝动画的信息
     */
    private Card getCard(int num) {
        Card c;
        c = new Card(getContext());
        addView(c);
        c.setVisibility(View.VISIBLE);
        c.setNum(num);
        return c;
    }

    /**
     * 卡片扩散动画, 用于新出现的卡片
     */
    public void createScaleTo1(Card target){
        //fromX：起始X坐标上的伸缩尺寸
        //toX：结束X坐标上的伸缩尺寸。
        //fromY：起始Y坐标上的伸缩尺寸。
        //toY：结束Y坐标上的伸缩尺寸。
        //pivotXType：X轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。
        //pivotXValue：X坐标的伸缩值。
        //pivotYType：Y轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。
        //pivotYValue：Y坐标的伸缩值。
        ScaleAnimation sa = new ScaleAnimation(0.1f, 1, 0.1f, 1, Animation.RELATIVE_TO_SELF,
                0x5f, Animation.RELATIVE_TO_SELF, 0x5f);
        sa.setDuration(100);

        target.setAnimation(null);
        target.getLabel().startAnimation(sa);
    }
}
