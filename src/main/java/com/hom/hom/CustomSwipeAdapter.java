package com.hom.hom;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by djaka on 11/21/2017.
 */

public class CustomSwipeAdapter extends PagerAdapter {
    private int[] image_resource = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3};
    private Context ctx;
    private LayoutInflater layoutInflater;

    public CustomSwipeAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return image_resource.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swipe_layout, container, false);
        ImageView imageview = (ImageView) item_view.findViewById(R.id.image_view);
        imageview.setImageResource(image_resource[position]);

//        item_view.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                if (position == 0){
//                    Toast.makeText(ctx ,"SLide 1 CLiked", Toast.LENGTH_SHORT).show();
//                }else if (position == 1){
//                    Toast.makeText(ctx ,"SLide 1 CLiked", Toast.LENGTH_SHORT).show();
//                }else if (position == 2){
//                    Toast.makeText(ctx ,"SLide 1 CLiked", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
