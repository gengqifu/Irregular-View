package org.hunter.irregularshapeview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.bumptech.glide.Glide;

import org.hunter.irregularshapeviewlib.clippath.CategoryBean;

import java.util.ArrayList;

public class ClipPathActivity extends AppCompatActivity {

    private QiQiaoLayout mQiQiaoLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_path);

        ArrayList<CategoryBean> categoryBeans = new ArrayList<>();
        CategoryBean categoryBean = new CategoryBean();
        categoryBean.categoryName = "上三角";
        categoryBean.categoryIconUrl = "http://img.shuowan.com/Public/cate1/5.png";
        categoryBean.iconId = R.drawable.a;

        CategoryBean categoryBean1 = new CategoryBean();
        categoryBean1.categoryName = "下三角";
        categoryBean1.categoryIconUrl = "http://img.shuowan.com/Public/cate1/21.png";
        categoryBean1.iconId = R.drawable.b;

        CategoryBean categoryBean2 = new CategoryBean();
        categoryBean2.categoryName = "右三角";
        categoryBean2.categoryIconUrl = "http://img.shuowan.com/Public/cate1/22.png";
        categoryBean2.iconId = R.drawable.c;

        CategoryBean categoryBean3 = new CategoryBean();
        categoryBean3.categoryName = "菱形";
        categoryBean3.categoryIconUrl = "http://img.shuowan.com/Public/cate1/34.png";
        categoryBean3.iconId = R.drawable.d;

        CategoryBean categoryBean4 = new CategoryBean();
        categoryBean4.categoryName = "右下小三角";
        categoryBean4.categoryIconUrl = "http://img.shuowan.com/Public/cate1/23.png";
        categoryBean4.iconId = R.drawable.e;

        CategoryBean categoryBean5 = new CategoryBean();
        categoryBean5.categoryName = "正方形";
        categoryBean5.categoryIconUrl = "http://img.shuowan.com/Public/cate1/4.png";
        categoryBean5.iconId = R.drawable.f;

        CategoryBean categoryBean6 = new CategoryBean();
        categoryBean6.categoryName = "左下小三角";
        categoryBean6.categoryIconUrl = "http://img.shuowan.com/Public/cate1/33.png";
        categoryBean6.iconId = R.drawable.d;

        CategoryBean categoryBean7 = new CategoryBean();
        categoryBean7.categoryName = "右上三角";
        categoryBean7.categoryIconUrl = "http://img.shuowan.com/Public/cate1/32.png";
        categoryBean7.iconId = R.drawable.f;

        categoryBeans.add(categoryBean);
        categoryBeans.add(categoryBean1);
        categoryBeans.add(categoryBean2);
        categoryBeans.add(categoryBean3);
        categoryBeans.add(categoryBean4);
        categoryBeans.add(categoryBean5);
        categoryBeans.add(categoryBean6);
        categoryBeans.add(categoryBean7);
        mQiQiaoLayout = (QiQiaoLayout)findViewById(R.id.activity_main_qiqiaolayout);
        mQiQiaoLayout.setDate(categoryBeans);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Glide.with(this).resumeRequests();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Glide.with(this).pauseRequests();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Glide.with(this).onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        new Thread(){
            @Override
            public void run() {
                Glide.get(ClipPathActivity.this).clearDiskCache();
            }
        }.start();
        Glide.get(this).clearMemory();
    }
}
