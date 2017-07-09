package com.fyppractice.fyppractice.recyclerCuAdapters;

/**
 * Created by GrimReaper on 7/8/2017.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;



import com.fyppractice.fyppractice.R;
import com.fyppractice.fyppractice.rvmodels.crClassDataModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class crClassAdapter extends  RecyclerView.Adapter<crClassAdapter.StdntVuHolder>{
    private LayoutInflater mlayoutInflater;
    private List<crClassDataModel> listData;
    Context context;//for the EmptyNUllPointerException
//    List<crClassDataModel> data; = Collections.emptyList();  //for the EmptyNUllPointerException


    //    my custom Constructor to get Filled
    public crClassAdapter(Context context,List<crClassDataModel> listData) {
        this.mlayoutInflater = LayoutInflater.from(context);
        this.listData = listData;
        this.context = context;
    }
    //    the interface for the clickLisnter
    private StudentItemClickCallback itemClickCallback;

    //the interface
    //an interface for making this recyclerView ClickAble
    public interface StudentItemClickCallback {
        void onItemClick(int p);

        void onSecondaryIconClick(int p);
    }
    public void setItemClickCallback(final StudentItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }


    @Override
    public StdntVuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mlayoutInflater.inflate(R.layout.dummy_stdnt_row,parent,false);
        //by doing this it will use that findViewByID word only once and we will get optimized RecyclerView
        return new StdntVuHolder((view));
    }

    @Override
    public void onBindViewHolder(StdntVuHolder holder, int position) {
        //now all incoming data from the server needed to be
        //tightly bound here on the recycler View will be here
        crClassDataModel item  = listData.get(position);

        holder.stdName.setText(item.getStdName());
        holder.stdContact.setText(item.getStdContact());
        holder.stdRollNumber.setText(item.getStdRollNo());

//        Log.e("==>",item.getTchrName());


        holder.StdActionEdit.setText(item.getStdEditAction());
        holder.StdActionView.setText(item.getStdViewAction());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    //the custom class for the View holder in the android :-) for my own view to get bind

    public class StdntVuHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView stdName;
        TextView stdContact;
        TextView stdRollNumber;
        TextView StdActionEdit;
        TextView StdActionView;

        public StdntVuHolder(View itemView) {
            super(itemView);
            stdName = (TextView) itemView.findViewById(R.id.stdnt_name_dummy_row);
            stdContact = (TextView) itemView.findViewById(R.id.stdnt_contact_dummy_row);
            stdRollNumber = (TextView) itemView.findViewById(R.id.stdnt_RlNum_dummy_row);
            StdActionEdit = (TextView) itemView.findViewById(R.id.stdnt_actionsEdit_dummy_row);
            StdActionView = (TextView) itemView.findViewById(R.id.stdnt_actionsView_dummy_row);
            StdActionView.setOnClickListener(this);
            StdActionEdit.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.stdnt_actionsView_dummy_row) {
                itemClickCallback.onItemClick(getAdapterPosition());
            } else if(view.getId() == R.id.stdnt_actionsEdit_dummy_row) {
                itemClickCallback.onSecondaryIconClick(getAdapterPosition());
            }
        }

    }
    public void setListData(ArrayList<crClassDataModel> exerciseList) {
        this.listData.clear();
        this.listData.addAll(exerciseList);
    }

}

