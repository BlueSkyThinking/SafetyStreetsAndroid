package com.code4piter.blueskythinking.megapp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.code4piter.blueskythinking.megapp.R;
import com.code4piter.blueskythinking.megapp.model.dto.CameraDto;

import java.util.List;

public class CamerasAdapter extends RecyclerView.Adapter<CamerasAdapter.CameraViewHolder> {
	List<CameraDto> list;

	public CamerasAdapter(List<CameraDto> list) {
		this.list = list;
	}

	@Override
	public CameraViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.camera_item, parent, false);

		return new CameraViewHolder(view);
	}

	@Override
	public void onBindViewHolder(CameraViewHolder holder, int position) {
		CameraDto cameraDto = list.get(position);

		holder.name.setText(cameraDto.getName());
		holder.address.setText(cameraDto.getAddress());
	}

	public void setData(List<CameraDto> cameraDtos) {
		list = cameraDtos;
		this.notifyDataSetChanged();
	}

	public CameraDto getItem(int index) {
		return list.get(index);
	}

	@Override
	public int getItemCount() {
		return list.size();
	}

	class CameraViewHolder extends RecyclerView.ViewHolder {
		TextView name;
		TextView address;

		public CameraViewHolder(View itemView) {
			super(itemView);
			name = (TextView) itemView.findViewById(R.id.name);
			address = (TextView) itemView.findViewById(R.id.address);
		}
	}
}
