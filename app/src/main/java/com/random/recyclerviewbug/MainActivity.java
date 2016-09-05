package com.random.recyclerviewbug;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

	String abc = "ABCDEFGHIJKLMNOPQRSTUVXYZ1234567890";
	Random random = new Random();

	private long currentFreeId = 1L;
	private List<MainModel> dataset;

	private RecyclerView recyclerView;
	private LinearLayoutManager layoutManager;
	private MainAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initDataset();
		initRecyclerView();

		findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addItemToDatasetStart();
				adapter.datasetChanged(dataset);
			}
		});
	}

	private void initDataset() {
		dataset = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			addItemToDatasetStart();
		}
	}

	private void addItemToDatasetStart() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 5 + random.nextInt(10); i++) {
			builder.append(abc.charAt(random.nextInt(abc.length())));
		}
		dataset.add(0, new MainModel(currentFreeId++, builder.toString()));
	}

	private void initRecyclerView() {
		recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		layoutManager = new LinearLayoutManager(this);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		layoutManager.setReverseLayout(true);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setScrollContainer(true);
		recyclerView.setLayoutAnimation(null);
		recyclerView.setItemAnimator(null);
		adapter = new MainAdapter(dataset);
		recyclerView.setAdapter(adapter);
	}
}
