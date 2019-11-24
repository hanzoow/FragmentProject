package com.example.simpleprojectroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpleprojectroom.View.MainActivity;
import com.example.simpleprojectroom.Interface.PersonAdapterClickListener;
import com.example.simpleprojectroom.Models.Person;
import com.example.simpleprojectroom.R;
import com.example.simpleprojectroom.adapter.ViewHolder.BusVH;
import com.example.simpleprojectroom.adapter.ViewHolder.PlaneVH;
import com.example.simpleprojectroom.adapter.ViewHolder.ViewHolderLoading;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM_BUS = 2;
    private final int VIEW_TYPE_ITEM_PLANE = 3;
    private final int VIEW_TYPE_LOADING = 1;
    onLoadMoreListener onLoadMoreListener;
//    private PersonAdapterLongClickToDelete personAdapterLongClickToDelete;
    MainActivity mainActivity = new MainActivity();
    private boolean isLoading;

    public PersonAdapterClickListener listener;
    List<Person> people;
    //Person person;

    public interface onLoadMoreListener{
        void onLoadMore();
    }



    public void updatePeopleData(List<Person> people){
        if(people == null || people.size() == 0){
            return;
        }
        isLoading = false;
        int perSize = this.people.size() + 1;
        people.addAll(people);
       // notifyItemRangeRemoved(perSize,people.size());
        notifyItemRangeChanged(perSize,people.size());
     //   notifyDataSetChanged();
    }

    public PersonAdapter(onLoadMoreListener mOnLoadMoreListener, List<Person> person,Context context) {
        listener = (PersonAdapterClickListener) context;
        this.onLoadMoreListener = mOnLoadMoreListener;
//        this.personAdapterLongClickToDelete = longClickToDelete;
        this.people = person;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM_BUS) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bus, parent, false);
            view.setLayoutParams(new LinearLayout.LayoutParams(parent.getMeasuredWidth(), LinearLayout.LayoutParams.WRAP_CONTENT));
            return new BusVH(view,listener);
        } else if (viewType == VIEW_TYPE_ITEM_PLANE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plane, parent, false);
            view.setLayoutParams(new LinearLayout.LayoutParams(parent.getMeasuredWidth(), LinearLayout.LayoutParams.WRAP_CONTENT));
            return new PlaneVH(view,listener);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_loading, parent, false);
            return new ViewHolderLoading(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BusVH) {
            ((BusVH) holder).bind(people.get(position));
        }
        if (holder instanceof PlaneVH) {
            ((PlaneVH) holder).bind(people.get(position));
        }

        if (!isLoading && (position == people.size() - 1)) {
            isLoading = true;
            if (onLoadMoreListener != null) {
                onLoadMoreListener.onLoadMore();
            }
        }
    }


    @Override
    public int getItemViewType(int position) {
        if((position < getItemCount() - 1) ){
            if(people.get(position).getPT().equals("bus")){
                return VIEW_TYPE_ITEM_BUS;
            }
            return VIEW_TYPE_ITEM_PLANE;
        }
        return VIEW_TYPE_LOADING;
    }

    @Override
    public int getItemCount() {
        return people.size() + 1;
    }
}
