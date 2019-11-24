package com.example.simpleprojectroom.adapter.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpleprojectroom.Interface.PersonAdapterClickListener;
//import com.example.simpleprojectroom.Interface.PersonAdapterLongClickToDelete;
import com.example.simpleprojectroom.Models.Person;
import com.example.simpleprojectroom.R;

public class PlaneVH extends RecyclerView.ViewHolder {
    ImageView ivPref,ivDel;
    TextView tvName, tvSurname;
    PersonAdapterClickListener listener;
//    PersonAdapterLongClickToDelete personAdapterLongClickToDelete;
    public PlaneVH(@NonNull View itemView,PersonAdapterClickListener listener) {
        super(itemView);
//        this.personAdapterLongClickToDelete = clickToDelete;
        this.listener = listener;

        tvName = itemView.findViewById(R.id.tvName);
        tvSurname = itemView.findViewById(R.id.tvSurname);
        ivPref = itemView.findViewById(R.id.ivPref);
        ivDel = itemView.findViewById(R.id.imgDelete);
    }

    public void bind(final Person person) {
        // - get element from your data at this position
        // - replace the contents of the view with that element
//        itemView.setTag(person);
        tvName.setText(person.getName());
        tvSurname.setText(person.getSurName());

//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                activity.onItemClicked(person.indexOf((Person) view.getTag()));
//                listener.onPersonClick(person);
//            }
//        });
//        itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                 personAdapterLongClickToDelete.deletePerson(person);
//                return false;
//            }
//        });

        ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener.onDeleteClick(position);
                    }
                }
            }
        });
    }
}
