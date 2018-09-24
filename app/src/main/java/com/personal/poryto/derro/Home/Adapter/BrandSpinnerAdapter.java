package com.personal.poryto.derro.Home.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.personal.poryto.derro.R;

import java.util.List;

/**
 * Created by Pory Sovann on 9/20/2018.
 */
public class BrandSpinnerAdapter extends ArrayAdapter {
    private Context context;
    private List<Integer> resources;

    public BrandSpinnerAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public BrandSpinnerAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);

    }

    public BrandSpinnerAdapter(@NonNull Context context, int resource, @NonNull Object[] objects) {
        super(context, resource, objects);

    }

    public BrandSpinnerAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull Object[] objects) {
        super(context, resource, textViewResourceId, objects);

    }

    public BrandSpinnerAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resources = objects;

    }

    public BrandSpinnerAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List objects) {
        super(context, resource, textViewResourceId, objects);

    }

    @Override
    public int getCount() {
        return resources.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return createView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createView(position,convertView,parent);
    }
    private View createView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        ImageView imageView;
        LayoutInflater mInflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.brand_logo_spinner_viewholder, parent, false);

        imageView = (ImageView) convertView.findViewById(R.id.image_logo);
        convertView.setTag(imageView);
        imageView.setImageResource(resources.get(position));
        return convertView;
    }
}
