package com.sdiri.animalsounds;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.zip.Inflater;

public class SlideAdapter extends PagerAdapter {

    Context context ;
    LayoutInflater inflater;
    MediaPlayer mediaPlayer;

    public SlideAdapter(Context context){
        this.context = context ;
    }
    public int [] list_images={R.drawable.cat
                              ,R.drawable.dog
                              ,R.drawable.horse
                              ,R.drawable.tiger
                              ,R.drawable.bear};
    public int [] list_audioResources = {R.raw.catsound
                                        ,R.raw.dogsound
                                        ,R.raw.horsesound
                                        ,R.raw.tigersound
                                        ,R.raw.bearsound};
    public String [] list_title = {"cat"
                                   ,"dog"
                                   ,"horse"
                                   ,"tiger"
                                   ,"bear"};

    @Override
    public int getCount() {
        return list_title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==(RelativeLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide,container,false);
        ImageView imageView = (ImageView)view.findViewById(R.id.animalImage);
        TextView  textView = (TextView)view.findViewById(R.id.animalTitle);
        Button button =(Button)view.findViewById(R.id.button);
        imageView.setImageResource(list_images[position]);
        textView.setText(list_title[position]);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null)
                    mediaPlayer.stop();
                mediaPlayer = MediaPlayer.create(context,list_audioResources[position]);
                mediaPlayer.start();

            }
        });
        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
