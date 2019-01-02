package app.lloyd.com.mocktestapp.userdetails;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.lloyd.com.mocktestapp.ItemOnClickListener;
import app.lloyd.com.mocktestapp.MockBaseActivity;
import app.lloyd.com.mocktestapp.MockBaseFragment;
import app.samyad.com.mocktestapp.R;
import app.lloyd.com.mocktestapp.loginorsignin.LoginResponse;
import app.lloyd.com.mocktestapp.loginorsignin.Places;
import app.lloyd.com.mocktestapp.loginorsignin.Users;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class UserDetailFragment extends MockBaseFragment implements
        ItemOnClickListener<Places>,
        UserContract.UserView, View.OnClickListener {

    private static final String TAG = UserDetailFragment.class.getSimpleName();
    private static final int REQUEST_PHONE_CALL = 1;
    private TextView tvFName;
    private TextView tvLName;
    private TextView tvNoOfPlaces;
    private TextView tvPhoneNumber;
    private RecyclerView rvUserDetails;
    private String phoneNumber;
    private UserDetailPresenter userDetailPresenter;

    public static UserDetailFragment getInstance(Bundle bundle) {
        UserDetailFragment userDetailFragment = new UserDetailFragment();
        userDetailFragment.setArguments(bundle);
        return userDetailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewsAndInstances(view);
        initData();
    }

    private void initData() {
        userDetailPresenter = new UserDetailPresenter(new UserDetailInteractor());
        userDetailPresenter.subscribeView(this);
        userDetailPresenter.checkData(getArguments());
    }

    @Override
    protected void initViewsAndInstances(View view) {
        tvFName = view.findViewById(R.id.tv_user_first_name);
        tvLName = view.findViewById(R.id.tv_user_last_name);
        tvNoOfPlaces = view.findViewById(R.id.tv_user_place_visited);
        tvPhoneNumber = view.findViewById(R.id.tv_user_phone_no);
        tvPhoneNumber.setOnClickListener(this);
        rvUserDetails = view.findViewById(R.id.rv_user_feeds);
        if (getContext() != null) {
            rvUserDetails.setLayoutManager(new LinearLayoutManager(getContext()));
            rvUserDetails.addItemDecoration(new DividerItemDecoration(getContext(), VERTICAL));
        }
        setupViewListeners();
    }

    @Override
    protected void setupViewListeners() {
        //not used.
    }

    @Override
    public void onItemClicked(int position, Places places, View view) {
        if (places != null && getActivity() instanceof MockBaseActivity &&
                !getActivity().isFinishing()) {
            ((MockBaseActivity) getActivity()).navigateTo(11, places);
        }
    }

    @Override
    public void setUserData(LoginResponse loginResponse) {
        tvFName.setText(loginResponse.getFirstName());
        tvLName.setText(loginResponse.getLastName());
        userDetailPresenter.getUserDetails(loginResponse.getUserId());
    }

    @Override
    public void noUserDataError(String errorMsg) {
        Log.d(TAG, "No User Data found Message : " + errorMsg);
    }

    @Override
    public void setUserDetails(Users user) {
        tvNoOfPlaces.setText(String.valueOf(user.getNumOfPlaces()));
        tvPhoneNumber.setText(String.valueOf(user.getPhoneNo()));
        phoneNumber = String.valueOf(user.getPhoneNo());
        rvUserDetails.setAdapter(new UserVisitedPlacesAdapter(user.getPlaces(), this));
    }

    @Override
    public void errorMsg(String errorMsg) {
        Log.d(TAG, "No User Details Found Message : " + errorMsg);
        if (getActivity() instanceof MockBaseActivity && !getActivity().isFinishing()) {
            ((MockBaseActivity) getActivity()).navigateTo(9, null);
        }
    }

    @Override
    public void showKeyPad() {
        //not used
    }

    @Override
    public void hideKeyPad() {
        //not used
    }

    @Override
    public void loadProgressIndicator() {
        //not used
    }

    @Override
    public void stopProgressIndicator() {
        //not used
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_user_phone_no) {

            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
            } else {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                startActivity(intent);

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PHONE_CALL: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));

                    startActivity(intent);
                }
            }
        }
    }

}
