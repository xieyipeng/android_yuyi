package com.example.xieyipeng.logindemo;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CityChoose extends ExpandableListActivity {

    private static String[] PROVINCE = {"山西省", "山东省", "浙江省"};
    private static String[][] CITY = {
            {"太原市", "运城市", "大同市", "阳泉市", "长治市"},
            {"青岛市", "济南市", "烟台市", "泰安市"},
            {"杭州市", "苏州市", "宁波市", "嘉兴市"},
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
            @Override
            public int getGroupCount() {
                return PROVINCE.length;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return CITY[groupPosition].length;
            }

            @Override
            public Object getGroup(int groupPosition) {
                return PROVINCE[groupPosition];
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return CITY[groupPosition][childPosition];
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                LinearLayout linearLayout = new LinearLayout(CityChoose.this);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                TextView textView = getTextView();
                linearLayout.addView(textView);
                assert textView != null;
                textView.setText(getGroup(groupPosition).toString());
                return linearLayout;
            }

            private TextView getTextView() {
                ViewGroup.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 64);
                TextView textView = new TextView(CityChoose.this);
                textView.setLayoutParams(params);
                textView.setTextSize(15);
                textView.setTextColor(Color.BLACK);
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                textView.setPadding(36, 0, 0, 0);
                textView.setTextSize(20);
                return textView;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                LinearLayout linearLayout = new LinearLayout(CityChoose.this);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                TextView textView = getTextView();
                linearLayout.addView(textView);
                assert textView != null;
                textView.setText(getChild(groupPosition, childPosition).toString());
                return linearLayout;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
        };
        setListAdapter(adapter);
        getExpandableListView().setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                bundle.putString("city", adapter.getChild(groupPosition, childPosition).toString());
                intent.putExtras(bundle);
                setResult(0, intent);
                finish();
                return false;
            }
        });
    }


}
