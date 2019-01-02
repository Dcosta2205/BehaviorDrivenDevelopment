package app.lloyd.com.mocktestapp;

import yml.com.baselibrary.BaseFragment;

public abstract class MockBaseFragment extends BaseFragment {

    public void showDialog() {
        if (getActivity() instanceof MockBaseActivity && !getActivity().isFinishing() &&
                !((MockBaseActivity) getActivity()).isProgressShown()) {
            ((MockBaseActivity) getActivity()).showProgress();
        }
    }

    public void hideDialog() {
        if (getActivity() instanceof MockBaseActivity && !getActivity().isFinishing() &&
                ((MockBaseActivity) getActivity()).isProgressShown()) {
            ((MockBaseActivity) getActivity()).hideProgress();
        }
    }

}
