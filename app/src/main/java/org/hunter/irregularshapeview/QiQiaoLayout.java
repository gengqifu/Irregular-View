package org.hunter.irregularshapeview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.hunter.irregularshapeviewlib.clippath.CategoryBean;
import org.hunter.irregularshapeviewlib.clippath.DiamondView;
import org.hunter.irregularshapeviewlib.clippath.GlideImageLoadUtils;
import org.hunter.irregularshapeviewlib.clippath.SquareView;
import org.hunter.irregularshapeviewlib.clippath.TriangleView;

import java.util.ArrayList;

/**所有分类顶部 8个(七巧板样式)
 */

public class QiQiaoLayout extends RelativeLayout {
    private ArrayList<CategoryBean> mClassifyList  = new ArrayList<>();
    public QiQiaoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public  void setDate(ArrayList<CategoryBean> list){
        mClassifyList.clear();
        if(list!=null){
            mClassifyList.addAll(list);
        }
        if(mClassifyList!=null){
            for (int i=0;i<mClassifyList.size() && i<getChildCount();i++) {
                CategoryBean bean = mClassifyList.get(i);
                if(getChildAt(i) instanceof TriangleView){
                    TriangleView view = (TriangleView)getChildAt(i);
                    //GlideImageLoadUtils.displayImage(getContext(), bean.categoryIconUrl, view, GlideImageLoadUtils.getIconNormalOptions());
                    GlideImageLoadUtils.displayImage(getContext(), bean.iconId, view, GlideImageLoadUtils.getIconNormalOptions());
                    view.setText(bean.categoryName);
                    view.setOnViewClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getContext(),"点击了按钮!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else if(getChildAt(i) instanceof DiamondView) {
                    DiamondView view = (DiamondView)getChildAt(i);
                    //GlideImageLoadUtils.displayImage(getContext(), bean.categoryIconUrl, view, GlideImageLoadUtils.getIconNormalOptions());
                    GlideImageLoadUtils.displayImage(getContext(), bean.iconId, view, GlideImageLoadUtils.getIconNormalOptions());
                    view.setText(bean.categoryName);
                    view.setOnViewClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getContext(),"点击了按钮!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else if(getChildAt(i) instanceof SquareView) {
                    SquareView view = (SquareView)getChildAt(i);
                    //GlideImageLoadUtils.displayImage(getContext(), bean.categoryIconUrl, view, GlideImageLoadUtils.getIconNormalOptions());
                    GlideImageLoadUtils.displayImage(getContext(), bean.iconId, view, GlideImageLoadUtils.getIconNormalOptions());
                    view.setText(bean.categoryName);
                    view.setOnViewClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getContext(),"点击了按钮!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }
        postInvalidate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        TriangleView mClassifyView1 = (TriangleView) findViewById(R.id.classify_1);
        mClassifyView1.setMode(TriangleView.TYPE_LEFT_TOP);

        TriangleView mClassifyView2 = (TriangleView) findViewById(R.id.classify_2);
        mClassifyView2.setMode(TriangleView.TYPE_RIGHT_BOTTOM);

        TriangleView mClassifyView3 = (TriangleView) findViewById(R.id.classify_3);
        mClassifyView3.setMode(TriangleView.TYPE_RIGHT_MIDDLE);

        DiamondView mClassifyView4 = (DiamondView) findViewById(R.id.classify_4);
        mClassifyView4.setMode(DiamondView.TYPE_LEFT_TOP);

        TriangleView mClassifyView5 = (TriangleView) findViewById(R.id.classify_5);
        mClassifyView5.setMode(TriangleView.TYPE_RIGHT_BOTTOM_SMAILL);

        SquareView mClassifyView6 = (SquareView) findViewById(R.id.classify_6);

        TriangleView mClassifyView7 = (TriangleView) findViewById(R.id.classify_7);
        mClassifyView7.setMode(TriangleView.TYPE_LEFT_BOTTOM);

        TriangleView mClassifyView8 = (TriangleView) findViewById(R.id.classify_8);
        mClassifyView8.setMode(TriangleView.TYPE_RIGHT_TOP);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        w = w - getPaddingRight() - getPaddingLeft();
        float scale = w/660f;

        QiQiaoLayout.LayoutParams params = new QiQiaoLayout.LayoutParams((int)(230*scale),(int)(230*scale));
        params.setMargins(0,0,(int)(10*scale),(int)(10*scale));
        getChildAt(0).setLayoutParams(params);

        params = new QiQiaoLayout.LayoutParams((int)(230*scale),(int)(230*scale));
        params.setMargins((int)(10*scale),(int)(10*scale),0,0);
        getChildAt(1).setLayoutParams(params);

        params = new QiQiaoLayout.LayoutParams((int)(120*scale),(int)(240*scale));
        params.setMargins((int)(253*scale),0,0,0);
        getChildAt(2).setLayoutParams(params);

        params = new QiQiaoLayout.LayoutParams((int)(250*scale),(int)(110*scale));
        params.setMargins((int)(270*scale),0,0,(int)(10*scale));
        getChildAt(3).setLayoutParams(params);

        params = new QiQiaoLayout.LayoutParams((int)(120*scale),(int)(120*scale));
        params.setMargins((int)(270*scale),(int)(120*scale),0,0);
        getChildAt(4).setLayoutParams(params);

        params = new QiQiaoLayout.LayoutParams((int)(120*scale),(int)(120*scale));
        params.setMargins((int)(400*scale),(int)(120*scale),0,0);
        getChildAt(5).setLayoutParams(params);

        params = new QiQiaoLayout.LayoutParams((int)(120*scale),(int)(120*scale));
        params.setMargins((int)(530*scale),(int)(120*scale),0,0);
        getChildAt(6).setLayoutParams(params);

        params = new QiQiaoLayout.LayoutParams((int)(230*scale),(int)(230*scale));
        params.setMargins((int)(430*scale),0,0,0);
        getChildAt(7).setLayoutParams(params);


    }

}
