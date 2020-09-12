package com.nh.conversionapp;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import javax.xml.transform.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QRreadingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QRreadingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QRreadingFragment extends Fragment implements ZXingScannerView.ResultHandler{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private ZXingScannerView scannerView;
    View rootView;
    public QRreadingFragment() {
        // Required empty public constructor
    }


    public static QRreadingFragment newInstance(String param1, String param2) {
        QRreadingFragment fragment = new QRreadingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_qrreading, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnScannerAction();
    }

    public void btnScannerAction(){
        scannerView = new ZXingScannerView(getContext());
        LinearLayout content = rootView.findViewById(R.id.content);
        content.addView(scannerView);
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }


    @Override
    public void handleResult(com.google.zxing.Result result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Cambio");
        builder.setMessage(result.getText());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        scannerView.resumeCameraPreview(this);

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
