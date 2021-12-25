package com.pqt.phamquangthanh.web_service;

import android.app.Notification;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UserListViewAdapter extends RecyclerView.Adapter<UserListViewAdapter.UserViewHolder> {
    private Context mContext;
    private ArrayList<User> data;
    private OnUserItemClickListener onUserItemClickListener;

    public UserListViewAdapter(Context mContext, ArrayList<User> data) {
        this.mContext = mContext;
        this.data = data;
    }

    public void setOnUserItemClickListener(OnUserItemClickListener onUserItemClickListener) {
        this.onUserItemClickListener = onUserItemClickListener;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        ImageView img_avatar;
        TextView txt_name,txt_email;
        LinearLayout linearLayout_person;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            img_avatar              = itemView.findViewById(R.id.avatar);
            txt_name            = itemView.findViewById(R.id.txt_name);
            txt_email           = itemView.findViewById(R.id.txt_email);
            linearLayout_person = itemView.findViewById(R.id.linearLayout_person);
        }

        void setUser(User user) {
            txt_name.setText(user.getName());
            txt_email.setText(user.getEmail());
            Glide.with(mContext)
                    .load(user.getImg_avatar())
                    .into(img_avatar);
            linearLayout_person.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onUserItemClickListener.onUserItemClick(user.getId());
                }
            });
        }
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=  LayoutInflater.from(mContext).inflate(R.layout.row_person, parent, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder viewHolder, int position) {

        viewHolder.setUser(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public interface OnUserItemClickListener {
        void onUserItemClick(int id);
    }

}
