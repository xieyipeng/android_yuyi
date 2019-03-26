package com.example.xieyipeng.logindemo;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;

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

        ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
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

            /**
             *
             * @return
             */
            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                return null;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                return null;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return false;
            }
        };


    }
}
