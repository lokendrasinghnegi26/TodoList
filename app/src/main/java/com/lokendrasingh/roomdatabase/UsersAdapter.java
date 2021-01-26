package com.lokendrasingh.roomdatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.media.CamcorderProfile.get;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserAdapterVH> {

    private List<Users>usersList;
    private Context context;
    private ClickListner clickListner;
    public UsersAdapter(ClickListner clickListner) {
        this.clickListner = clickListner;
    }

    public void setData(List<Users>usersList)
    {
        this.usersList= usersList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     context= parent.getContext();
     return new UserAdapterVH(
             LayoutInflater.from(context).inflate(R.layout.row_items, parent,false)
     );
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterVH holder, int position) {
        Users users= usersList.get(position);
        String username= users.getUsername();
        holder.textView.setText(username);
        holder.imageOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp(view, users);

            }
        });

    }
    public void showPopUp(View view, Users users)
    {
        PopupMenu popupMenu= new PopupMenu(context,view);
        popupMenu.inflate(R.menu.menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id= item.getItemId();
                switch (id){
                    case R.id.itupdate:
                        clickListner.UpdateClicked(users);
                    break;
                    case R.id.itDelete:
                        clickListner.deleteClicked(users);
                        break;
                }

                return false;
            }
        });
        popupMenu.show();
    }
    public interface ClickListner{
        void UpdateClicked(Users users);
        void deleteClicked(Users users);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public  class UserAdapterVH extends RecyclerView.ViewHolder{
        ImageView imageOptions;
        TextView textView;
        public UserAdapterVH(@NonNull View itemView) {
            super(itemView);
            textView= itemView.findViewById(R.id.tvName);
            imageOptions= itemView.findViewById(R.id.imageOptions);

        }
    }
}
