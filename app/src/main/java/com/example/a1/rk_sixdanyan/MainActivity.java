package com.example.a1.rk_sixdanyan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List <String> list = new ArrayList<>();
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        initDate();
    }

    private void initDate() {
        for (int i =0;i<100;i++){
            list.add("离毕业还有"+i+"天");
        }
        //设置适配器
        mListView.setAdapter(new MyAdapter());
        //调用下面设置listview高度的方法
        setListViewHeightBasedOnChildren(mListView);

    }

    private void initview() {
        mListView = (ListView) findViewById(R.id.list);
    }
    //适配器
    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            viewHoler holer;
            if (convertView ==null){
                convertView = View.inflate(MainActivity.this,R.layout.item,null);
                holer = new viewHoler();
                holer.mTextView = (TextView) convertView.findViewById(R.id.list_item);
                convertView.setTag(holer);
            }else {
                holer = (viewHoler) convertView.getTag();
            }
            holer.mTextView.setText(list.get(position));

            return convertView;
        }
        class viewHoler{
            TextView mTextView;
        }
    }
    //动态设置listview的高度
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);  //在还没有构建View 之前无法取得View的度宽。在此之前我们必须选                 measure 一下.
            totalHeight += listItem.getMeasuredHeight();
           // Log.i("xxxxxxxx listItem xxxxxxxxx", i + "  height : " + totalHeight);
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        listView.setLayoutParams(params);
    }
}
