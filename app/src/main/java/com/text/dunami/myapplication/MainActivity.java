package com.text.dunami.myapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout r=findViewById(R.id.main);
       // r.addView(addbutton("bilibili"));
        //TextView
//        ObjectAnimator 属性动画
//        ValueAnimator  值动画
        MotionEvent mv=MotionEvent.obtain(100,100,MotionEvent.ACTION_DOWN,100,100,1);
        r.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        ViewGroup viewGroup=findViewById(R.id.viewGroup);
        viewGroup.addView(addbutton("bilibili"));
        viewGroup.addView(addbutton("dilidili"));
        viewGroup.addView(addbutton("优酷"));
        viewGroup.addView(addbutton("爱奇艺"));
        viewGroup.addView(addbutton("牧羊人之心"));
        viewGroup.addView(addbutton("万象物语"));
        viewGroup.addView(addbutton("大众点评"));
        viewGroup.addView(addbutton("电瓶骨干家"));
        viewGroup.addView(addbutton("支付宝"));
        viewGroup.addView(addbutton("卫星"));
        viewGroup.addView(addbutton("麦当劳"));

       // Toast.makeText(this,length("pwwkew"),Toast.LENGTH_SHORT).show();
        //Handler
    }
    public String length(String s) {
       HashMap m=new HashMap();
       m.put("a",0);
       m.put("a",10);
        Log.d("test",m.get("a")+"");
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))) {
                // 此处是根据角标j来遍历，一个一个的往里存
                set.add(s.charAt(j));
                j++;
                ans = Math.max(ans, j - i);//注意这里为什么是j-1而不是j-i+1呢？因为j已经++了
            } else {
                set.remove(s.charAt(i++));
              //  Log.d("test",set.size()+"");
            }


        }
        for(char zifu:set)
        {
            Log.d("test",zifu+"\\");
        }
        return "";
    }
    public  String lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>(); // Hashmap中存放的是 字符-索引
        // try to extend the range [i, j]
        int i = 0;
        for (int j = 0 ;j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                //如果map里已经有这个index[j]处的char，将i赋成j+1。
                i = Math.max(map.get(s.charAt(j)) + 1, i);
            }
            //坐标为减去角标位
            ans = Math.max(ans, j - i + 1);//注意这里的max，适用于“abba”这种情况，“a”虽然存在于map中了，但i也不移动。
            // 将char加入哈希表中，value为索引值j。
            map.put(s.charAt(j), j);
        }
        return s.substring(i,i+ans);
    }
    private View addbutton(String name) {
        Button b=new Button(this);

        b.setText(name);
        b.setTextSize(16);
        return b;
    }
}
