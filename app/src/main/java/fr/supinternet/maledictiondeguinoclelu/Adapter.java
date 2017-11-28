package fr.supinternet.maledictiondeguinoclelu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by clementineferreol on 28/11/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<Model.Lieu> arrayLieu = new ArrayList<>();

    private Context context;

    public Adapter(Context context, ArrayList<Model.Lieu> places) {

        this.context = context;
        this.arrayLieu = places;
    }

    @Override
    public int getItemCount() {
        return arrayLieu.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        holder.lieu.setText(arrayLieu.get(position).getId());
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView lieu;


        public ViewHolder(View contentView) {
            super(contentView);

            lieu = (TextView) itemView.findViewById(R.id.lieu);

        }
    }
}
