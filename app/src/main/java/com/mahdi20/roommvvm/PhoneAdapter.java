package com.mahdi20.roommvvm;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahdi20.roommvvm.phonetools.model.Phone;


public class PhoneAdapter extends ListAdapter<Phone, PhoneAdapter.PhoneHolder> {
    private OnItemClickListener listener;

    public PhoneAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Phone> DIFF_CALLBACK = new DiffUtil.ItemCallback<Phone>() {
        @Override
        public boolean areItemsTheSame(Phone oldItem, Phone newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Phone oldItem, Phone newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getPhone().equals(newItem.getPhone());
        }
    };

    @NonNull
    @Override
    public PhoneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.phone_item, parent, false);
        return new PhoneHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneHolder holder, int position) {
        Phone currentPhone = getItem(position);
        holder.textViewName.setText(currentPhone.getName());
        holder.textViewPhone.setText(currentPhone.getPhone());
    }

    public Phone getPhoneAt(int position) {
        return getItem(position);
    }

    class PhoneHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewPhone;
        private ImageView imgEdit;
        private ImageView imgDelete;

        public PhoneHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewPhone = itemView.findViewById(R.id.text_view_phone);
            imgEdit = itemView.findViewById(R.id.imgEdit);
            imgDelete = itemView.findViewById(R.id.imgDelete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });



            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });













        }
    }

    public interface OnItemClickListener {
        void onItemClick(Phone phone);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}