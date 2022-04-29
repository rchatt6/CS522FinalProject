package com.example.unifiedshopping;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterVendor extends RecyclerView.Adapter<AdapterVendor.CustomViewHolder>{

    private ArrayList<Vendor> VendorList;
    private LayoutInflater Linflater;

    ArrayList<Boolean> states = new ArrayList<>();

    private ItemClickListner gitemClickListner;

    public AdapterVendor(ArrayList<Vendor> vendorList, Context context, ItemClickListner itmClkListner) {
        VendorList = vendorList;
        Linflater = LayoutInflater.from(context);

        if (states.isEmpty()) {
            for (int i = 0; i < VendorList.size(); i++) {
                states.add(Boolean.FALSE);
            }
        }


        gitemClickListner = itmClkListner;
    }

    public AdapterVendor(ArrayList<Vendor> vendorList, Context context) {
        VendorList = vendorList;
        Linflater = LayoutInflater.from(context);

        if (states.isEmpty()) {
            for (int i = 0; i < VendorList.size(); i++) {
                states.add(Boolean.FALSE);
            }
        }

    }

    public AdapterVendor(ArrayList<Vendor> vendorList, Context context, ItemClickListner itmClkListner, ArrayList<Boolean> sw) {
        VendorList = vendorList;
        Linflater = LayoutInflater.from(context);

        /*if (states.isEmpty()) {
            for (int i = 0; i < VendorList.size(); i++) {
                states.add(Boolean.FALSE);
            }
        }*/

        states = sw;


        gitemClickListner = itmClkListner;
    }

    @NonNull
    @Override
    public AdapterVendor.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = Linflater.inflate(R.layout.vendors_list_item , parent , false);
        return new AdapterVendor.CustomViewHolder(item, gitemClickListner);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterVendor.CustomViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.vendorName.setText(VendorList.get(holder.getAdapterPosition()).getName());
        if (VendorList.get(position).getState()==true)
        {
            holder.aSwitch.setChecked(true);
        }
        else
            holder.aSwitch.setChecked(false);

        //holder.aSwitch.set
        //holder.aSwitch = VendorList.get(holder.getAdapterPosition()).getSwitch();
        //holder.aSwitch.setChecked(states.get(holder.getAdapterPosition()));
        //holder.aSwitch.setChecked(VendorList.get(position).aSwitch.isChecked());
        Log.d("switch", "ooutside");

        holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        //Toast.makeText(compoundButton.getContext(), "hi", Toast.LENGTH_SHORT).show();
                        //getAdapterPosition();
                        compoundButton.setChecked(true);
                        VendorList.get(position).setState(b);
                    }
                    else {
                        compoundButton.setChecked(false);
                        VendorList.get(position).setState(b);
                    }
                }
            });


    }

    @Override
    public int getItemCount() {
        return VendorList.size();
    }

    @Override
    public void onViewRecycled(AdapterVendor.CustomViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        //states.add(holder.getAdapterPosition(), holder.aSwitch.isChecked());

        super.onViewRecycled(holder);
    }

    public interface  ItemClickListner
    {
        void onItemClick(int pos);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView vendorName;
        private Switch aSwitch;

        public ItemClickListner itemClickListner;


        public CustomViewHolder(final View itemView) {
            super(itemView);
            vendorName = itemView.findViewById(R.id.vendorName2);
            aSwitch = itemView.findViewById(R.id.toggle2);
            /*aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        //Toast.makeText(compoundButton.getContext(), "hi", Toast.LENGTH_SHORT).show();
                        //getAdapterPosition();
                        compoundButton.setChecked(true);
                    }
                    else {
                        compoundButton.setChecked(false);
                    }
                }
            });*/

        }

        public CustomViewHolder(final View itemView, ItemClickListner itemClickListner) {
            super(itemView);
            vendorName = itemView.findViewById(R.id.vendorName2);
            aSwitch = itemView.findViewById(R.id.toggle2);

            this.itemClickListner = itemClickListner;

        }

        @Override
        public void onClick(View view) {
            itemClickListner.onItemClick(getAdapterPosition());
        }

    }
}
