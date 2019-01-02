package app.lloyd.com.mocktestapp.userdetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import app.lloyd.com.mocktestapp.MockBaseFragment;
import app.lloyd.com.mocktestapp.loginorsignin.Places;
import app.lloyd.com.mocktestapp.MockBaseFragment;
import app.samyad.com.mocktestapp.R;
import app.lloyd.com.mocktestapp.loginorsignin.Places;

public class PlaceDetailFragment extends MockBaseFragment {

    private static final String TAG = PlaceDetailFragment.class.getSimpleName();
    private TextView tvPlaceName;
    private TextView tvPlaceRating;
    private TextView tvPlaceDesc;
    private TextView tvPlaceComment;
    private ImageView ivPlace;
    private Places places;

    public PlaceDetailFragment() {
        //default constructor.
    }

    public static PlaceDetailFragment getInstance(Bundle bundle) {
        PlaceDetailFragment placeDetailFragment = new PlaceDetailFragment();
        placeDetailFragment.setArguments(bundle);
        return placeDetailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_place_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewsAndInstances(view);
    }

    @Override
    protected void initViewsAndInstances(View view) {
        tvPlaceName = view.findViewById(R.id.tv_place_name);
        tvPlaceRating = view.findViewById(R.id.tv_place_rating);
        tvPlaceDesc = view.findViewById(R.id.tv_place_desc);
        tvPlaceComment = view.findViewById(R.id.tv_place_comment);
        ivPlace = view.findViewById(R.id.iv_place);
        initData();
    }

    private void initData() {
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            if (!TextUtils.isEmpty(bundle.getString("places"))) {
                places = new Gson().fromJson(bundle.getString("places"), Places.class);
                if (places != null) {
                    Log.d(TAG, "place : " + places.getId());
                    setPlaceData();
                }
            }
        }
    }

    private void setPlaceData() {
        if (places != null) {
            tvPlaceName.setText(places.getPlace());
            tvPlaceRating.setText(String.valueOf(places.getRating()));
            tvPlaceDesc.setText(places.getDesc());
            tvPlaceComment.setText(places.getComments());
            Log.d(TAG,"image url : "+places.getImg());
            Glide.with(this)
                    .load(places.getImg())
                    .into(ivPlace);
        }
    }

    @Override
    protected void setupViewListeners() {
        //not used
    }
}
