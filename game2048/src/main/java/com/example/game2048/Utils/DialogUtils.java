package com.example.game2048.Utils;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.game2048.Data.MySqlHelper;
import com.example.game2048.MainFragment;
import com.example.game2048.Model.Gamer;
import com.example.game2048.R;

/**
 * Created by Administrator on 2018/2/3.
 * description:
 */

public class DialogUtils {

    public static void getAddChartDialog(final Context context, final int score){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_charts, null);
        final EditText editText = (EditText) v.findViewById(R.id.et_name);

        builder.setTitle("你输了").setView(v).setPositiveButton("我要入榜", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MySqlHelper mySqlHelper = new MySqlHelper(context, "game2048.db", null, 1);
                SQLiteDatabase db = mySqlHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("user_name", editText.getText().toString());
                values.put("user_score", score);
                db.insert("charts", "id", values);
                MainFragment.getMainFragment().startGame();
            }
        }).setNegativeButton("放弃", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainFragment.getMainFragment().startGame();
            }
        });

        AlertDialog dialog =builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);        //按返回键不能退出dialog
        dialog.show();

        final Button mBtnPositive = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        mBtnPositive.setEnabled(false);     //暂时禁用这个确定按键，直到输入框有内容
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() != 0){
                    mBtnPositive.setEnabled(true);
                }else {
                    mBtnPositive.setEnabled(false);
                }
            }
        });
    }

    public static void getOpenDialog(Context context, Gamer gamer){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.dialog_open_title);
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_open, null);

        TextView tvName = (TextView) v.findViewById(R.id.tv_name);
        TextView tvScore = (TextView) v.findViewById(R.id.tv_score);
        tvName.setText("姓名：" + gamer.getName());
        tvScore.setText("分数：" + gamer.getScore());

        builder.setView(v);
        builder.setPositiveButton("确定", null);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
