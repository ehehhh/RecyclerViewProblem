package com.random.recyclerviewbug;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {

	private List<MainModel> dataset;

	public MainAdapter(List<MainModel> dataset) {
		this.dataset = dataset;
		setHasStableIds(true);
	}

	@Override
	public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		Context context = parent.getContext();
		return new MainViewHolder(
				LayoutInflater.from(context)
						.inflate(R.layout.recyclerview_item, parent, false));
	}

	@Override
	public void onBindViewHolder(MainViewHolder holder, int position) {
		MainModel mainModel = dataset.get(position);
		holder.bind(mainModel);
	}

	@Override
	public int getItemCount() {
		return dataset.size();
	}

	@Override
	public long getItemId(int position) {
		return dataset.get(position).id;
	}

	public void datasetChanged(List<MainModel> dataset) {
		this.dataset = dataset;
		notifyItemInserted(0);
	}
}
