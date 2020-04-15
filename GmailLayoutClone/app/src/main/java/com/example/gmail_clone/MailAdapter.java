package com.example.gmail_clone;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class MailAdapter extends BaseAdapter {

    List<MailModel> mails;

    public MailAdapter(List<MailModel> mails) {
        this.mails = mails;
    }

    @Override
    public int getCount() {
        return mails.size();
    }

    @Override
    public Object getItem(int position) {
        return mails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {



        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        }

        TextView textName = convertView.findViewById(R.id.name);
        TextView textSubject = convertView.findViewById(R.id.subject);
        TextView textContent = convertView.findViewById(R.id.content);
        TextView textTime = convertView.findViewById(R.id.time);
        TextView textAvatar = convertView.findViewById(R.id.avatar);
        ImageView imageFavorite = convertView.findViewById(R.id.favorite);


        MailModel mail = mails.get(position);

        if (mail.isFavorited()) {
            imageFavorite.setImageResource(R.drawable.ic_star_black_24dp);
        } else {
            imageFavorite.setImageResource(R.drawable.ic_star_border_black_24dp);
        }

        imageFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isFavorited = mails.get(position).isFavorited;
                mails.get(position).setFavorited(!isFavorited);
                notifyDataSetChanged();
            }
        });

        textName.setText(mail.getName());
        textContent.setText(mail.getContent());
        textSubject.setText(mail.getSubject());
        textTime.setText(mail.getTime());
        textAvatar.setText(mail.getName().substring(0, 1));

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

        // ------------------------------------
        // Disposable codes below
        //ColorFilter colorFilter = new ColorFilter();
        //textAvatar.getBackground().setColorFilter(colorFilter);
        //textAvatar.setBackgroundColor(color);

        Drawable background = textAvatar.getBackground();
        background.setTint(color);

        return convertView;
    }
}
