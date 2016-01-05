package activities;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import adapters.DeviceListAdapter;
import br.andremmfaria.projetofinal.educar.R;

public class DeviceSelectionActivity extends AppCompatActivity {

    private ListView mListView;
    private DeviceListAdapter mAdapter;
    private ArrayList<BluetoothDevice> mDeviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_selection);

        mDeviceList		= getIntent().getExtras().getParcelableArrayList("device.list");

        mListView		= (ListView) findViewById(R.id.lvPaired);

        mAdapter = new DeviceListAdapter(this);

        mAdapter.setData(mDeviceList);

        mAdapter.setConnListener(new DeviceListAdapter.OnConnectButtonClickListener() {
            @Override
            public void onConnectButtonClick(int position) {
                BluetoothDevice device = mDeviceList.get(position);
                finishWithResult(device.getAddress());
            }
        });

        mListView.setAdapter(mAdapter);
    }

    private void finishWithResult(String result)
    {
        Bundle conData = new Bundle();
        conData.putString("MAC_ADDRESS", result);
        Intent intent = new Intent();
        intent.putExtras(conData);
        setResult(RESULT_OK, intent);
        finish();
    }
}
