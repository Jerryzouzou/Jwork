package com.example.game2048.Model;

import android.content.Context;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.game2048.R;

/**
 * Created by Administrator on 2018/1/30.
 * description: 游戏中每个数字卡片的图卡，卡片的bean
 */

public class Card extends FrameLayout {

    private TextView label;
    private View background;
    private int num = 0;

    public Card(Context context) {
        super(context);

        LayoutParams lp = null;
        background = new View(getContext());
        lp = new LayoutParams(-1, -1);
        lp.setMargins(10, 10, 0, 0);
        background.setBackgroundColor(getResources().getColor(
                R.color.normalCardBack));
        addView(background, lp);

        label = new TextView(getContext());
        label.setTextSize(28);
        label.setGravity(Gravity.CENTER);
        TextPaint tp = label.getPaint();
        tp.setFakeBoldText(true);       //设置字体为粗体

        lp = new LayoutParams(-1, -1);
        lp.setMargins(10, 10, 0, 0);
        addView(label, lp);

        setNum(0);
    }

    public int getNum() {
        return num;
    }

    /**
     * 设置卡片上的数字，和背景颜色
     */
    public void setNum(int i) {
        this.num = i;
        if(num <= 0){
            label.setText("");
        }else{
            label.setText(num + "");
        }
        switch (num){
            case 0:
                label.setBackgroundResource(R.color.normalCardBack);
                break;
            case 2:
                label.setTextColor(getResources().getColor(R.color._2Font));
                label.setBackgroundResource(R.color._2Back);
                break;
            case 4:
                label.setTextColor(getResources().getColor(R.color._4Font));
                label.setBackgroundResource(R.color._4Back);
                break;
            case 8:
                label.setTextColor(getResources().getColor(R.color.otherFont));
                label.setBackgroundResource(R.color._8Back);
                break;
            case 16:
                label.setTextColor(getResources().getColor(R.color.otherFont));
                label.setBackgroundResource(R.color._16Back);
                break;
            case 32:
                label.setTextColor(getResources().getColor(R.color.otherFont));
                label.setBackgroundResource(R.color._32Back);
                break;
            case 64:
                label.setTextColor(getResources().getColor(R.color.otherFont));
                label.setBackgroundResource(R.color._64Back);
                break;
            case 128:
                label.setTextColor(getResources().getColor(R.color.otherFont));
                label.setBackgroundResource(R.color._128Back);
                break;
            case 256:
                label.setTextColor(getResources().getColor(R.color.otherFont));
                label.setBackgroundResource(R.color._256Back);
                break;
            case 512:
                label.setTextColor(getResources().getColor(R.color.otherFont));
                label.setBackgroundResource(R.color._512Back);
                break;
            case 1024:
                label.setTextColor(getResources().getColor(R.color.otherFont));
                label.setBackgroundResource(R.color._1024Back);
                break;
            case 2048:
                label.setTextColor(getResources().getColor(R.color.otherFont));
                label.setBackgroundResource(R.color._2048Back);
                break;
            default:
                label.setTextColor(getResources().getColor(R.color._2Font));
                label.setBackgroundResource(R.color._2Back);
                break;
        }
    }

    public boolean equals(Card o){
        return getNum() == o.getNum();
    }

    protected Card clone(){
        Card c = new Card(getContext());
        c.setNum(getNum());
        return c;
    }

    public TextView getLabel() {
        return label;
    }
}
