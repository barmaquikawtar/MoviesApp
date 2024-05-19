package com.example.movies_app.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies_app.Activities.ListItemActivity;
import com.example.movies_app.Activities.MainActivity;
import com.example.movies_app.Models.Film;
import com.example.movies_app.R;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements ItemTouchHelperAdapter,
        Filterable {

    private Context context;
    private List<Film> films;
    private List<Film> films_filtred;
    private Search_Filter search_filter;
    ItemTouchHelper itemTouchHelper;

    public MyAdapter(Context context, List<Film> films) {
        this.context = context;
        this.films = films;
        films_filtred = new ArrayList<>();
        films_filtred.addAll(films);
        search_filter = new Search_Filter(this);
//        this.filtred_films = films;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.activity_list_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        holder.title.setText("helllo");

        holder.title.setText(films_filtred.get(position).getNom());
        holder.type.setText(films_filtred.get(position).getType());
        holder.image.setImageResource(films_filtred.get(position).getImage());
        holder.ratingBar.setRating((float) films_filtred.get(position).getRating());
        String descsub = films_filtred.get(position).getDesc();
        if (descsub.length() > 30) {
            descsub = descsub.substring(0, 30) + "...";
        }
        holder.desc.setText(descsub);
        holder.duration.setText(films_filtred.get(position).getCreated_at() + " min");

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Film film=films.get(position);
                String mssg="Title : "+film.getNom()+"\n"+"DESCRIPTION : "+film.getDesc()+"\n"
                        +"Duration : "+film.getType()+"\n"+"Rating : "+film.getRating();
                ShareCompat.IntentBuilder.from((Activity) context).setChooserTitle(film.getNom())
                        .setType("text/plain").setText(mssg).startChooser();
            }
        });
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Film film=films.get(position);
                String mssg="Title : "+film.getNom()+"\n"+"DESCRIPTION : "+film.getDesc()+"\n"
                        +"Duration : "+film.getType()+"\n"+"Rating : "+film.getRating();
                ShareCompat.IntentBuilder.from((Activity) context).setChooserTitle(film.getNom())
                        .setType("text/plain").setText(mssg).startChooser();
            }
        });
    }

    @Override
    public int getItemCount() {
        return films_filtred.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Film frm_film = films.get(fromPosition);
        films.remove(frm_film);
        films.add(toPosition, frm_film);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemSwiped(int position, int direction) {
        if (direction == ItemTouchHelper.RIGHT) {
            films.remove(position);
            notifyItemRemoved(position);
        } else if (direction == ItemTouchHelper.LEFT) {
            Film film = films.get(position);
            View dialoguedesign = LayoutInflater.from(context).inflate(R.layout.rating_pop_layout, null);

            TextView title = dialoguedesign.findViewById(R.id.pop_title);
            ImageView image = dialoguedesign.findViewById(R.id.pop_image);
            RatingBar ratingbar = dialoguedesign.findViewById(R.id.pop_rating);

            title.setText(film.getNom().toString());
            image.setImageResource(film.getImage());
            ratingbar.setRating((float) film.getRating());

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this.context);
            alertDialog
//                    .setMessage("Rate this movie").setTitle("Rate movie")
                    .setView(dialoguedesign)
                    .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            film.setRating(ratingbar.getRating());
                            notifyDataSetChanged();
                        }
                    }).setNegativeButton("Cancel", null);
//            TextView textView=findViewById R.id.pop_title;

            alertDialog.show();
            notifyItemChanged(position);
//            Toast.makeText(this.context,direction+"",Toast.LENGTH_SHORT).show();
        }
    }

    public void setTochHelper(ItemTouchHelper itemTouchHelper) {
        this.itemTouchHelper = itemTouchHelper;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener, View.OnTouchListener, GestureDetector.OnGestureListener {
        private TextView title, type, desc;
        private ImageView image;
        private RatingBar ratingBar;
        private Button duration;
        GestureDetector gestureDetector;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.card_title);
            type = itemView.findViewById(R.id.card_type);
            image = itemView.findViewById(R.id.card_image);
            ratingBar = itemView.findViewById(R.id.card_rating);
            duration = itemView.findViewById(R.id.card_duration);
            desc = itemView.findViewById(R.id.card_desc);
            gestureDetector = new GestureDetector(itemView.getContext(), this);
        }

        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {
            Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();

            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {
            itemTouchHelper.startDrag(this);
        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();

        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            gestureDetector.onTouchEvent(motionEvent);
            return false;
        }
    }

    public Filter getFilter() {
        return search_filter;
    }

    public class Search_Filter extends Filter {
        public RecyclerView.Adapter adapter;

        public Search_Filter(RecyclerView.Adapter adapter) {
            this.adapter = adapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            films_filtred.clear();
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                films_filtred.addAll(films);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Film item : films) {
                    if (item.getNom().toLowerCase().contains(filterPattern)) {
                        films_filtred.add(item);
                    }
                }
            }
//            Toast.makeText(context,films.toString(),Toast.LENGTH_SHORT).show();
            results.values = films_filtred;
            results.count = films_filtred.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            films_filtred = (List<Film>) results.values;
            this.adapter.notifyDataSetChanged();
        }
    }

    ;

}
