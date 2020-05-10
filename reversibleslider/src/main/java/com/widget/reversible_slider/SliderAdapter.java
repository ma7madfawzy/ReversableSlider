package com.widget.reversible_slider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;



import com.widget.reversibleslider.R;
import com.widget.reversibleslider.databinding.CustomSliderRowBinding;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Fawzy on 02,October,2018
 */
public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ViewHolder> {
    private CustomSliderRowBinding binding;
    private ArrayList<String> dataList, dataListStore;
    private LayoutInflater layoutInflater;
    private SliderClickHandler slideClickHandler;

    public SliderAdapter(Context context, List<String> dataList, SliderClickHandler slideClickHandler) {
        setDataList(dataList);
        layoutInflater = LayoutInflater.from(context);
        this.slideClickHandler = slideClickHandler;
    }

    public ArrayList<String> getDataList() {
        return dataListStore;
    }

    public void setDataList(List<String> list) {
        this.dataList = new ArrayList<>(list);
        dataListStore = dataList;
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        renderView(layoutInflater, parent);

        return new SliderAdapter.ViewHolder(binding);
    }

    protected void renderView(LayoutInflater layoutInflater, ViewGroup parent) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(layoutInflater,
                R.layout.custom_slider_row, parent, false);
    }

    @Override
    public void onBindViewHolder(SliderAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setSliderClickListener(SliderClickHandler sliderClickHandler) {
        this.slideClickHandler = sliderClickHandler;
        notifyDataSetChanged();
    }

    public interface SliderClickHandler {
        void onSliderClicked(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CustomSliderRowBinding binding;

        public ViewHolder(CustomSliderRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            handleCLick(binding.getRoot(), getAdapterPosition());
        }

        private void handleCLick(View v, int position) {
            if (slideClickHandler != null)
                v.setOnClickListener(view -> slideClickHandler.onSliderClicked(position));
        }

        public void bind(int position) {
            binding.setDataModel(dataList.get(position));
        }

    }
}

