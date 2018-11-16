package com.example.game2048;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.game2048.Utils.AnimLayer;
import com.example.game2048.Utils.GameView;

/**
 * Created by Administrator on 2018/1/30.
 * description:
 */

public class MainFragment extends Fragment {

    private int score = 0;
    private TextView tvScore, tvBestScore;
    private LinearLayout root = null;
    private GameView gameView;
    private AnimLayer animLayer = null;
    public static final String SP_KEY_BEST_SCORE = "bestScore";
    public static MainFragment mainFragment;

    public MainFragment(){
        mainFragment = this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        root = (LinearLayout) rootView.findViewById(R.id.container);
        root.setBackgroundColor(0xfffaf8ef);

        tvScore = (TextView) rootView.findViewById(R.id.tv_score);
        tvBestScore = (TextView) rootView.findViewById(R.id.tvBestScore);
        gameView = (GameView) rootView.findViewById(R.id.gameView);
        animLayer = (AnimLayer) rootView.findViewById(R.id.animLayer);

        return rootView;
    }

    public static MainFragment getMainFragment() {
        return mainFragment;
    }

    public void clearScore(){
        score = 0;
        showScore();
    }

    public void showScore() {
        tvScore.setText(""+score);
        //tvScore.setText("11111");
    }

    public void addScore(int s){
        score += s;
        showScore();
        int maxScore = Math.max(score, getBestScore());
        saveBestScore(maxScore);
        showBestScore(maxScore);
    }

    public void showBestScore(int maxScore) {
        tvBestScore.setText(maxScore + "");
    }

    public void saveBestScore(int maxScore) {
        SharedPreferences.Editor ed = getActivity().getPreferences(getActivity().MODE_PRIVATE).edit();
        ed.putInt(SP_KEY_BEST_SCORE, maxScore);
        ed.commit();
    }

    public int getBestScore() {
        return getActivity().getPreferences(getActivity().MODE_PRIVATE).getInt(SP_KEY_BEST_SCORE,
                0);
    }

    public int getScore() {
        return score;
    }

    public AnimLayer getAnimLayer() {
        return animLayer;
    }

    /**
     * 开始或重新开始游戏
     */
    public void startGame(){
        gameView.startGame();
    }
}
