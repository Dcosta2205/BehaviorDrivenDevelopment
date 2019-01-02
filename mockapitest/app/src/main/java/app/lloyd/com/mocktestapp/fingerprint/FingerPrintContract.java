package app.lloyd.com.mocktestapp.fingerprint;

import javax.crypto.Cipher;

import app.lloyd.com.mocktestapp.MockBasePresenter;
import app.lloyd.com.mocktestapp.MockBaseView;

public class FingerPrintContract {

    public interface View extends MockBaseView{

    }

    public interface Presenter extends MockBasePresenter{
        void generateKeys();

        boolean  cipherInit();

        Cipher getCipherObject();
    }
}
