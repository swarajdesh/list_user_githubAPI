package com.example.githubapi.Adapter;

/**
 * Author:    Swaraj Deshmukh
 * Created:   01.06.2019
 *
 **/
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.githubapi.MainActivity;
import com.example.githubapi.Model.User;
import com.example.githubapi.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.AdapterViewHolder> {

    Context context;


    List<User> data;
    ImageView profilepic;
    TextView username,type;





public Adapter(List<User> data, Context context)
{
    this.context = context;
    this.data=data;

}
    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.list_item,viewGroup,false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder adapterViewHolder, int i) {


    final User geter1 = data.get(i);
    String login,image,usertype;
    login = geter1.getLogin();
    usertype=geter1.getType();
    image=geter1.getAvatarUrl();
    username.setText(login);
    type.setText(usertype);
    Picasso.get().load(image).into(profilepic);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {


        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            profilepic = itemView.findViewById(R.id.img);
            username = itemView.findViewById(R.id.txt1);
            type = itemView.findViewById(R.id.txt2);




        }
    }
}
