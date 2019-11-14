package es.raulprieto.widgetstring;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import es.raulprieto.widgetstring.databinding.LourdesActivityMainBinding;

public class MainActivityLourdes extends AppCompatActivity {

    LourdesActivityMainBinding binding;
    /*LourdesLayoutbinding.includeBusiness binding.includeBusiness;
    LourdesLayoutbinding.includeParticular binding.includeParticular;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.lourdes_activity_main);

        /*ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content).getRootView();
        binding.includeBusiness = DataBindingUtil.inflate(getLayoutInflater(), R.layout.lourdes_layout_business, viewGroup, false);
        binding.includeParticular = DataBindingUtil.inflate(getLayoutInflater(), R.layout.lourdes_layout_particular, viewGroup, false);
*/
        binding.rgTypeClient.check(binding.rbtParticular.getId());
        binding.includeBusiness.getRoot().setVisibility(View.GONE);
//        binding.includeBussiness.setVisibility(View.GONE);

        binding.rgTypeClient.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtBusiness:
                        binding.includeParticular.getRoot().setVisibility(View.GONE);
                        binding.includeBusiness.getRoot().setVisibility(View.VISIBLE);
                        break;
                    case R.id.rbtParticular:
                        binding.includeBusiness.getRoot().setVisibility(View.GONE);
                        binding.includeParticular.getRoot().setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }
        });

        binding.includeBusiness.spNumEmployees.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String message;
                switch (position) {
                    case 0:
                        message = getString(R.string.zeroE);
                        break;
                    default:
                        message = binding.includeBusiness.spNumEmployees.getResources()
                                .getQuantityString(R.plurals.numEmp,
                                        Integer.parseInt(binding.includeBusiness.spNumEmployees.getSelectedItem().toString()),
                                        Integer.parseInt(binding.includeBusiness.spNumEmployees.getSelectedItem().toString()));
                        break;
                }

                Toast.makeText(MainActivityLourdes.this, message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        autoCompleteMonths();

        showWebViewData();


    }

    private void showWebViewData() {
        binding.includeBusiness.wbInformation.loadData(getString(R.string.contentPreview), "text/HTML", "UTF-8");
    }

    private void autoCompleteMonths() {
        String[] months = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, months);

        binding.includeParticular.actMonth.setAdapter(adapter);
        binding.includeParticular.actMonth.setThreshold(0);
    }
}
