package id.sch.smktelkom_mlg.tugas01.xiirpl5035.pemesanan_makanan;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    RadioGroup rgBonus;
    //RadioButton rbA,rbP,rbI;
    Spinner spOrder,spIsi;
        String[][]arIsi ={{"Meja 1","Meja 2","Meja 3","Meja 4", "Meja 5", "Meja 6","Meja 7", "Meja 8"},
                            {"Diantar","Bawa Pulang"}};
        ArrayList<String> listIsi = new ArrayList<>();
        ArrayAdapter<String> adapter;

    EditText etNama;
    CheckBox cbR,cbI,cbS,cbN,cbK,    cbT,cbJ,cbSu,cbKo,cbD;
    Button bOk;
    TextView tvHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spOrder = (Spinner) findViewById(R.id.spinnerOrder);
        spIsi = (Spinner) findViewById(R.id.spinnerIsi);

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listIsi);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spIsi.setAdapter(adapter);

            spOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                                            {
                                               @Override
                                               public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                                                   listIsi.clear();
                                                   listIsi.addAll(Arrays.asList(arIsi[pos]));
                                                   adapter.notifyDataSetChanged();
                                                   spIsi.setSelection(0);
                                               }

                                               @Override
                                               public void onNothingSelected(AdapterView<?> adapterView) {

                                               }
                                           }
            );

        rgBonus = (RadioGroup) findViewById(R.id.RadioGroupBonus);
        /*rbA = (RadioButton) findViewById(R.id.radioButtonAir);
        rbP = (RadioButton) findViewById(R.id.radioButtonPuding);
        rbI = (RadioButton) findViewById(R.id.radioButtonIce);*/

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

        rgBonus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                               @Override
                                               public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                                   if(i == R.id.radioButtonAir)
                                                   {
                                                       findViewById(R.id.tilJA).setVisibility(View.VISIBLE);
                                                   }
                                                   else
                                                   {
                                                       findViewById(R.id.tilJA).setVisibility(View.GONE);
                                                   }
                                               }
                                           }


        );
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


            String bonus = null;

            if(rgBonus.getCheckedRadioButtonId()!=-1) {
                RadioButton rb = (RadioButton)
                        findViewById(rgBonus.getCheckedRadioButtonId());
                bonus = rb.getText().toString();

                if (rgBonus.getCheckedRadioButtonId() == R.id.radioButtonAir)
                {
                    EditText etJA = (EditText) findViewById(R.id.editTextJA);
                    bonus += " " + etJA.getText()+ " gelas";
                }

            }

            /*if(rbA.isChecked())
            {
                bonus = rbA.getText().toString();

            }
            else if (rbP.isChecked())
            {
                bonus = rbP.getText().toString();
            }
            else if(rbI.isChecked())
            {
                bonus = rbI.getText().toString();
            }*/

            if (bonus==null)
            {
                tvHasil.setText("Nama :" + nama + "\n" + makanan +"\n" + minuman +"\nBelum memilih Bonus" +
                        "\n\nPesanan akan di "+ spOrder.getSelectedItem().toString()+ " dengan " + spIsi.getSelectedItem().toString());
            }
            else {
                tvHasil.setText("Nama :" + nama + "\n" + makanan + "\n" + minuman + "\nBonus :\n" + bonus +
                        "\n\nPesanan akan di "+ spOrder.getSelectedItem().toString()+ " dengan " + spIsi.getSelectedItem().toString());
            }
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
