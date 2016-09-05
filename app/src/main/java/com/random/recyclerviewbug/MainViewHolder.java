package com.random.recyclerviewbug;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MainViewHolder extends RecyclerView.ViewHolder {

	private final TextView text;

	public MainViewHolder(View itemView) {
		super(itemView);
		text = (TextView) itemView.findViewById(R.id.item_text);
	}

	public void bind(MainModel mainModel) {
		text.setText(String.format("%s - %s", mainModel.id, mainModel.text));
	}
}
