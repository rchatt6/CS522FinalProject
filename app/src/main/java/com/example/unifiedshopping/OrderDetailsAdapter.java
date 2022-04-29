package com.example.unifiedshopping;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.CustomViewHolder>{

    private List<Order> OrderList;
    private LayoutInflater Linflater;

    ArrayList<Boolean> states = new ArrayList<>();

    private ItemClickListner gitemClickListner;

    OrderDetailsAdapter(List<Order> orders, Context context) {
        this.OrderList = orders;
        Linflater = LayoutInflater.from(context);

        //gitemClickListner = itemClickListner;
    }


    @NonNull
    @Override
    public OrderDetailsAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = Linflater.inflate(R.layout.order_details , parent , false);
        return new CustomViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailsAdapter.CustomViewHolder holder, int position) {
        Log.d("OrderDetails", String.valueOf(position)+" "+ OrderList.get(position).getAddressName());
        if (OrderList.get(position).getImage()==-42069)
            holder.prodImg.setImageResource(R.drawable.dummy_order_image);
        else
            holder.prodImg.setImageResource(OrderList.get(position).getImage());        String name = OrderList.get(position).getOrderName();
        if (name.length()>50)
            name = name.substring(0,45)+"...";
        holder.prodName.setText(name);
        holder.deliveryName.setText(OrderList.get(position).getAddressName());
        holder.prodStatus.setText(OrderList.get(position).getCurrStatus());
        holder.vendorName.setText(OrderList.get(position).getVendorName());
        holder.backgroundImg.setImageResource(R.drawable.grey_dotted);

        //Log.i("Yash", "inadapter: "+OrderList.get(position).getCurrStatus()+Integer.toString(OrderList.get(position).getPriorityFlag()));
        if (OrderList.get(position).getPriorityFlag()==0) {
            holder.icon1.setVisibility(View.INVISIBLE);
            if (OrderList.get(position).getCurrStatus().contains("Shipped ")==true)
                holder.progressBar.setImageResource(R.drawable.muted_placed);
            else
                holder.progressBar.setImageResource(R.drawable.muted_shipped);
        }
        else if(OrderList.get(position).getPriorityFlag()<0) {
            holder.icon1.setVisibility(View.VISIBLE);
            holder.icon1.setImageResource(R.drawable.mute);
            if (OrderList.get(position).getCurrStatus().contains("Shipped ")==true)
                holder.progressBar.setImageResource(R.drawable.muted_placed);
            else
                holder.progressBar.setImageResource(R.drawable.muted_shipped);
        }
        else {
            holder.icon1.setVisibility(View.VISIBLE);
            holder.icon1.setImageResource(R.drawable.priority);
            if (OrderList.get(position).getCurrStatus().contains("Shipped ")==true)
                holder.progressBar.setImageResource(R.drawable.default_placed);
            else
                holder.progressBar.setImageResource(R.drawable.default_shipped);
        }
        holder.orderDescription.setText("DESCRIPTION: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.");

        holder.orderPrice.setText("$"+String.valueOf(OrderList.get(position).getPrice()));
        holder.DatePlaced.setText(OrderList.get(position).getDatePlaced());


    }

    @Override
    public int getItemCount() {
        return OrderList.size();
    }

    @Override
    public void onViewRecycled(OrderDetailsAdapter.CustomViewHolder holder) {

        super.onViewRecycled(holder);
    }

    public interface  ItemClickListner
    {
        void onItemClick(int pos);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public OrderDetailsAdapter.ItemClickListner itemClickListner;

        private ImageView prodImg;
        private TextView prodName;
        private TextView deliveryName;
        private TextView prodStatus;
        private TextView vendorName;
        private ImageView progressBar;
        private ImageView icon1;
        TextView orderPrice;
        TextView orderDescription;
        TextView DatePlaced;
        ImageView backgroundImg;


        public CustomViewHolder(final View itemView)  {
            super(itemView);
            prodImg = itemView.findViewById(R.id.Order_Pic);
            prodStatus = itemView.findViewById(R.id.Delivery_Status);
            deliveryName = itemView.findViewById(R.id.Address);
            prodName = itemView.findViewById(R.id.Order_Name);
            vendorName = itemView.findViewById(R.id.VendorName);
            icon1 = itemView.findViewById(R.id.PriorityIndicator);
            progressBar = itemView.findViewById(R.id.iProgressBar);
            orderPrice = itemView.findViewById(R.id.Order_Price);
            orderDescription = itemView.findViewById(R.id.Order_Description);
            DatePlaced = itemView.findViewById(R.id.DatePlaced);
            backgroundImg = itemView.findViewById(R.id.imageView);

        }

        /*public CustomViewHolder(final View itemView, OrderDetailsAdapter.ItemClickListner itemClickListner) {
            super(itemView);

        }*/

        /*@Override
        public void onClick(View view) {
            itemClickListner.onItemClick(getAdapterPosition());
        }*/

    }
}
