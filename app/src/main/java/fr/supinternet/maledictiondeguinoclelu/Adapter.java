package fr.supinternet.maledictiondeguinoclelu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by clementineferreol on 28/11/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<Place> arrayPlaces = new ArrayList<>();

    private Context context;

    public Adapter(Context context, ArrayList<Place> places) {

        this.context = context;
        this.arrayPlaces = places;
    }

    @Override
    public int getItemCount() {
        return (arrayPlaces.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Adapter.ViewHolder holder, final int position) {
        holder.lieu.setText(arrayPlaces.get(position).getId());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventsActivity.startActivity(context, arrayPlaces.get(holder.getAdapterPosition()));
                /*Intent intent = new Intent(view.getContext(), EventsActivity.class);
                intent.putExtra(EventsActivity.STRING_KEY, arrayPlaces.get(holder.getAdapterPosition()));
                view.getContext().startActivity(intent);*/
            }
        });

    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView lieu;


        public ViewHolder(View contentView) {
            super(contentView);

            lieu = (TextView) itemView.findViewById(R.id.lieu);

        }
    }
}
