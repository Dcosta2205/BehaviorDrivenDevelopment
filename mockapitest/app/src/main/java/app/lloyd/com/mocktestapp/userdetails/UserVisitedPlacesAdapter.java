package app.lloyd.com.mocktestapp.userdetails;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.lloyd.com.mocktestapp.ItemOnClickListener;
import app.lloyd.com.mocktestapp.loginorsignin.Places;
import app.lloyd.com.mocktestapp.ItemOnClickListener;
import app.samyad.com.mocktestapp.R;
import app.lloyd.com.mocktestapp.loginorsignin.Places;

class UserVisitedPlacesAdapter extends RecyclerView.Adapter<UserVisitedPlacesAdapter.PlacesViewHolder> {

    private List<Places> placesList;
    private ItemOnClickListener<Places> itemOnClickListener;

    UserVisitedPlacesAdapter(List<Places> places, ItemOnClickListener<Places> itemOnClickListener) {
        this.placesList = places;
        this.itemOnClickListener = itemOnClickListener;
    }

    @NonNull
    @Override
    public PlacesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PlacesViewHolder(LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.item_place_visited, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlacesViewHolder placesViewHolder, int i) {
        placesViewHolder.tvPlaceName.setText(placesList.get(i).getPlace());
        placesViewHolder.tvPlaceRating.setText(String.valueOf(placesList.get(i).getRating()));
    }

    @Override
    public int getItemCount() {
        return (placesList == null || placesList.isEmpty()) ? 0 : placesList.size();
    }

    class PlacesViewHolder extends RecyclerView.ViewHolder {

        private TextView tvPlaceName;
        private TextView tvPlaceRating;

        PlacesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPlaceName = itemView.findViewById(R.id.tv_place_name);
            tvPlaceRating = itemView.findViewById(R.id.tv_place_rating);
            itemView.setOnClickListener(v -> {
                Log.d("PlacesViewHolder", "OnClick : " +
                        placesList.get(getAdapterPosition()).getId());
                if (itemOnClickListener != null) {
                    itemOnClickListener.onItemClicked(getAdapterPosition(),
                            placesList.get(getAdapterPosition()), itemView);
                }
            });
        }
    }
}
