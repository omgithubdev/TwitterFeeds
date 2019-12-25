package com.omagrahari.demotwitter.feeds.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.omagrahari.demotwitter.R;
import com.omagrahari.demotwitter.databinding.LayoutFeedsItemBinding;
import com.omagrahari.demotwitter.entity.Tweets;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omprakash on 25,December,2019
 */
public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.FeedsViewHolder> {
    List<Tweets> feedsList = new ArrayList<>();

    public FeedsAdapter(List<Tweets> feedsList) {
        this.feedsList = feedsList;
    }

    @NonNull
    @Override
    public FeedsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        LayoutFeedsItemBinding layoutFeedsItemBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.layout_feeds_item, parent, false);
        return new FeedsViewHolder(layoutFeedsItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedsViewHolder holder, int position) {
        holder.binding.setFeeds(feedsList.get(position));
        holder.binding.setUser(feedsList.get(position).getUser());
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return feedsList.size();
    }

    public class FeedsViewHolder extends RecyclerView.ViewHolder {
        private LayoutFeedsItemBinding binding;

        public FeedsViewHolder(LayoutFeedsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
