package com.example.game2048;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.common.library.SwipeMenu;
import com.common.library.SwipeMenuCreator;
import com.common.library.SwipeMenuItem;
import com.common.library.SwipeMenuListView;
import com.example.game2048.Adapter.ChartsAdapter;
import com.example.game2048.Data.MySqlHelper;
import com.example.game2048.Model.Gamer;
import com.example.game2048.Utils.DialogUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/2/7.
 * description:
 */

public class ChartsActivity extends ActionBarActivity {

    private SwipeMenuListView mListView;    //排行榜列表
    private ChartsAdapter mAdapter;         //排行榜列表的适配器
    private ArrayList<Gamer> mList = new ArrayList<Gamer>();    //排行榜列表数据

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);
        getData();
        mListView = (SwipeMenuListView) findViewById(R.id.chartsListView);
        mAdapter = new ChartsAdapter(this, mList);
        mListView.setAdapter(mAdapter);
        
        initToolbar();

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                openItem.setWidth(dp2px(90));
                openItem.setTitle("Open");
                openItem.setTitleSize(18);
                openItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(openItem);

                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                deleteItem.setWidth(dp2px(90));
                deleteItem.setIcon(R.drawable.ic_delete);
                menu.addMenuItem(deleteItem);
            }
        };

        mListView.setmMenuCreator(creator);
        final Context context = this;
        mListView.setmOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index){
                    case 0:
                        //第一个图标按钮
                        //显示用户信息dialog
                        DialogUtils.getOpenDialog(context, mList.get(position));
                        break;
                    case 1:
                        //第二个图标按钮---删除信息
                        MySqlHelper mySqlHelper = new MySqlHelper(getApplicationContext(), "game2048.db", null, 1);
                        SQLiteDatabase db = mySqlHelper.getWritableDatabase();
                        db.delete("charts", "id = ?", new String[]{String.valueOf(mList.get(position).getId())});
                        mList.remove(position);
                        mAdapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });
    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView tvToolbar = (TextView) findViewById(R.id.text_view_toolbar_title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvToolbar.setText("排行榜");
        mToolbar.setNavigationIcon(R.drawable.btn_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /**
     * 获取排行榜数据
     */
    private void getData() {
        MySqlHelper splHelper = new MySqlHelper(this, "game2048.db", null, 1);
        final SQLiteDatabase db = splHelper.getWritableDatabase();

        //查询数据
        /**
         * table：表名。相当于select语句from关键字后面的部分。如果是多表联合查询，可以用逗号将两个表名分开。
         * columns：要查询出来的列名。相当于select语句select关键字后面的部分。
         * selection：查询条件子句，相当于select语句where关键字后面的部分，在条件子句允许使用占位符“？”
         * selectionArgs：对应于selection语句中占位符的值，值在数组中的位置与占位符在语句中的位置必须一致，否则就会有异常。
         * groupBy：相当于select语句groupby关键字后面的部分
         * having：相当于select语句having关键字后面的部分
         * orderBy：相当于select语句orderby关键字后面的部分
         */
        Cursor cursor = db.query("charts", null, null, null, null, null, "user_score desc");
        while (cursor.moveToNext()){
            int nameIndex = cursor.getColumnIndex("user_name");
            int scoreIndex = cursor.getColumnIndex("user_score");
            int idIndex = cursor.getColumnIndex("id");
            String name = cursor.getString(nameIndex);
            int score = cursor.getInt(scoreIndex);
            int id = cursor.getInt(idIndex);
            mList.add(new Gamer(id, name, score));
        }
    }

    //dp换算成px
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
