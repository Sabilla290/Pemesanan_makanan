package id.sch.smktelkom_mlg.tugas01.xiirpl5035.pemesanan_makanan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    EditText etNama;
    CheckBox cbR,cbI,cbS,cbN,cbK,    cbT,cbJ,cbSu,cbKo,cbD;
    Button bOk;
    TextView tvHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbR = (CheckBox) findViewById(R.id.checkBoxRendang);
        cbI = (CheckBox) findViewById(R.id.checkBoxIga);
        cbS = (CheckBox) findViewById(R.id.checkBoxSoup);
        cbN = (CheckBox) findViewById(R.id.checkBoxNasi);
        cbK = (CheckBox) findViewById(R.id.checkBoxKentang);


        cbT = (CheckBox) findViewById(R.id.checkBoxTeh);
        cbJ = (CheckBox) findViewById(R.id.checkBoxJeruk);
        cbSu = (CheckBox) findViewById(R.id.checkBoxSusu);
        cbKo = (CheckBox) findViewById(R.id.checkBoxKopi);
        cbD = (CheckBox) findViewById(R.id.checkBoxDawet);

        etNama = (EditText) findViewById(R.id.editTextNama);
        bOk = (Button) findViewById(R.id.buttonPesan);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);

        bOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                doProcess();
            }
        });
    }

    private void doProcess()
    {
        if (isValid())
        {
            String nama = etNama.getText().toString();

            String makanan="Pesanan Makanan:\n";
                    int makan = makanan.length();
            if(cbR.isChecked())makanan+=cbR.getText()+"\n";
            if(cbI.isChecked())makanan+=cbI.getText()+"\n";
            if(cbS.isChecked())makanan+=cbS.getText()+"\n";
            if(cbN.isChecked())makanan+=cbN.getText()+"\n";
            if(cbK.isChecked())makanan+=cbK.getText()+"\n";
            if(makanan.length()==makan)
                makanan+="-";

            String minuman="Pesanan Minuman:\n";
                 int minum = minuman.length();
            if(cbT.isChecked())minuman+=cbT.getText()+"\n";
            if(cbJ.isChecked())minuman+=cbJ.getText()+"\n";
            if(cbSu.isChecked())minuman+=cbSu.getText()+"\n";
            if(cbKo.isChecked())minuman+=cbKo.getText()+"\n";
            if(cbD.isChecked())minuman+=cbD.getText()+"\n";
            if(minuman.length()==minum)
                minuman+="-";


            tvHasil.setText("Nama :" + nama + "\n" + makanan +"\n" + minuman );

        }
    }

    private boolean isValid()
    {
        boolean valid = true;

        String nama = etNama.getText().toString();

        if (nama.isEmpty())
        {
            etNama.setError("Nama belum diisi");
            valid = false;
        }
        else if (nama.length()<3)
        {
            etNama.setError("Nama minimal 3 karakter");
            valid = false;
        }
        else
        {
            etNama.setError(null);
        }


        return valid;
    }
}
