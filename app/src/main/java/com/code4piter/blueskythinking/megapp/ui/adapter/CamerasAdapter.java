package com.code4piter.blueskythinking.megapp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.code4piter.blueskythinking.megapp.R;
import com.code4piter.blueskythinking.megapp.model.dto.CameraDto;
import com.code4piter.blueskythinking.megapp.utils.ImageUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pavel on 13.05.2017.
 */

public class CamerasAdapter extends RecyclerView.Adapter<CamerasAdapter.ViewHolder> {
    List<CameraDto> list;

    public CamerasAdapter(List<CameraDto> list) {
        this.list = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.camera_item,parent);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CameraDto cameraDto = list.get(position);
        holder.previewImage.setImageBitmap(ImageUtils.decodeImage(cameraDto.getImageBase64()));
        holder.aboutText.setText(cameraDto.getName());
    }

    public void setData(List<CameraDto> cameraDtos){
        list = cameraDtos;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.previewImage) ImageView previewImage;
        @BindView(R.id.aboutText) TextView aboutText;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}
