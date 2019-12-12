package com.fuatmeydan.nothesapla;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DuzenleDialog extends AppCompatDialogFragment { //oluşturduğumuz dialogfragmenti kullanabilmemiz için AppCompatDialogFragment den extend etmemiz gerekiyor.
    @NonNull
    private EditText editText_ders_adi; //dialogbox daki text yazılcak alanı tanımladık.
    private duzenleDialogListener listener;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view =inflater.inflate(R.layout.duzenle_layout,null); //oluşturduğumuz layout u inflate etmesi için view e atadık.
        builder.setView(view)
                .setTitle("Düzenle")
                .setNegativeButton("Vazgeç", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String yeniad= editText_ders_adi.getText().toString();
                        listener.dersDuzenle(yeniad);


                    }
                });
        editText_ders_adi=view.findViewById(R.id.editText_yeniAd);
        return builder.create();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener =(duzenleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"duzenleDialogListener implenet edilmemiş.");
        }
    }

    public interface duzenleDialogListener{
        void dersDuzenle(String yeniAd);
    }
}
