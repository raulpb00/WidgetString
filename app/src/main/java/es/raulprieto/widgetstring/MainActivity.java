package es.raulprieto.widgetstring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import es.raulprieto.widgetstring.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.rgCliente.check(binding.rbEmpresa.getId());
        binding.includeParticular.setVisibility(View.GONE);
//        binding.includeBussiness.setVisibility(View.GONE);

        binding.rgCliente.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbEmpresa:
                        binding.includeParticular.setVisibility(View.GONE);
                        binding.includeBussiness.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rbParticular:
                        binding.includeBussiness.setVisibility(View.GONE);
                        binding.includeParticular.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
