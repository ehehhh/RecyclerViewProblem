package com.random.recyclerviewbug;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {

	private List<MainModel> dataset;

	public MainAdapter(List<MainModel> dataset) {
		this.dataset = new ArrayList<>(dataset);
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
		DiffUtil.DiffResult diffResult = calculateDiffResult(dataset);
		this.dataset = new ArrayList<>(dataset);
		diffResult.dispatchUpdatesTo(this);
	}

	private DiffUtil.DiffResult calculateDiffResult(final List<MainModel> dataset) {
		return DiffUtil.calculateDiff(new DiffUtil.Callback() {
			@Override
			public int getOldListSize() {
				return MainAdapter.this.dataset.size();
			}

			@Override
			public int getNewListSize() {
				return dataset.size();
			}

			@Override
			public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
				MainModel oldItem = MainAdapter.this.dataset.get(oldItemPosition);
				MainModel newItem = dataset.get(newItemPosition);
				return oldItem.id == newItem.id;
			}

			@Override
			public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
				MainModel oldItem = MainAdapter.this.dataset.get(oldItemPosition);
				MainModel newItem = dataset.get(newItemPosition);
				return oldItem.text.equals(newItem.text);
			}
		});
	}
}
