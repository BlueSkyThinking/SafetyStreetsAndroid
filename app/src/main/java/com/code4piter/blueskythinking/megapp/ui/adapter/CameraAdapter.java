package com.code4piter.blueskythinking.megapp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.code4piter.blueskythinking.megapp.R;
import com.code4piter.blueskythinking.megapp.model.Camera;

import java.util.List;

public class CameraAdapter extends RecyclerView.Adapter<CameraAdapter.CameraViewHolder> {

	private List<Camera> cameras;

	public CameraAdapter(List<Camera> cameras) {
		super();
		this.cameras = cameras;
	}

	@Override
	public CameraViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_camera_item,
				parent, false);
		return new CameraViewHolder(item);
	}

	@Override
	public void onBindViewHolder(CameraViewHolder holder, int position) {
		if (cameras == null || cameras.get(position) == null) {
			return;
		}

		holder.camera.setText(cameras.get(position).getName());
	}

	public Camera getItem(int position) {
		return cameras.get(position);
	}

	@Override
	public int getItemCount() {
		if (cameras == null) {
			return 0;
		}
		return cameras.size();
	}

	class CameraViewHolder extends RecyclerView.ViewHolder {
		TextView camera;

		CameraViewHolder(View view) {
			super(view);
			camera = (TextView) view.findViewById(R.id.name);
		}
	}
}
