package com.nh.conversionapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;
import java.util.List;

public class CoinAdapter extends  RecyclerView.Adapter<CoinAdapter.ViewHolder> {

    private List<Coin> mCoin;
    private double value;
    private String coinSelected;
    private Context contextAdapter;
    // Pass in the contact array into the constructor
    public CoinAdapter(List<Coin> coins, double valueNew, String coinSelectedNew, Context context) {

        mCoin = coins;
        value = valueNew;
        coinSelected = coinSelectedNew;
        contextAdapter = context;
    }
    @Override
    public CoinAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        //contextAdapter = context;
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.row_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(CoinAdapter.ViewHolder holder, int position) {
        Coin coin = mCoin.get(position);
        TextView textView = holder.nameTextView;
        textView.setText(coin.getName());
        TextView valueTextView = holder.valueTextView;
        valueTextView.setText(Double.toString(coin.getValue()) + " " + coinSelected);
    }

    @Override
    public int getItemCount() {
        return mCoin.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView valueTextView;
        public Button changeButton;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.coin_name);
            valueTextView = (TextView) itemView.findViewById(R.id.coin_value);
            changeButton = (Button) itemView.findViewById(R.id.changebtn);
            changeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    AlertDialog.Builder alertadd = new AlertDialog.Builder(contextAdapter);
                    LayoutInflater factory = LayoutInflater.from(contextAdapter);
                    final View view = factory.inflate(R.layout.qr_view, null);
                    ImageView imageView = view.findViewById(R.id.imageQR);
                    try {
                        String[] tasa = valueTextView.getText().toString().split(" ");
                        Log.d("resultado","");
                        String result = Double.toString(Double.parseDouble(tasa[0]) * value);
                        Log.d("",result);
                        TextView text = view.findViewById(R.id.value);
                        text.setText(result + " " +coinSelected);
                        Bitmap bitmap = encodeAsBitmap(result);
                        imageView.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                    alertadd.setView(view);


                    alertadd.show();
                }
            });
        }

        Bitmap encodeAsBitmap(String str) throws WriterException {
            Bitmap bmp = null;

            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); // H = 30% damage

            int size = 256;

            BitMatrix bitMatrix = null;
            try {
                bitMatrix = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, size, size, hintMap);

                int width = bitMatrix.getWidth();
                bmp = Bitmap.createBitmap(width, width, Bitmap.Config.RGB_565);
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < width; y++) {
                        bmp.setPixel(y, x, bitMatrix.get(x, y) == true ? Color.BLACK : Color.WHITE);
                    }
                }
            } catch (WriterException e) {
                e.printStackTrace();
            }

            return bmp;
        }
    }
}
