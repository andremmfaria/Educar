package activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import br.andremmfaria.projetofinal.educar.R;
import objects.QueueElement;

public class MainActivity extends AppCompatActivity {

    private static final int NORMAL_Q_SIZE = 10;
    private static final int FUNCTION_Q_SIZE = 5;
    private static final int BLT_MAC_RETURN = 10;
    private static final int REQUEST_ENABLE_BT = 1;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private OutputStream outStream = null;
    private String address = null;

    private ArrayList<BluetoothDevice> mDeviceList = new ArrayList<>();

    protected ArrayList<QueueElement> NormalQueue = new ArrayList<>();
    protected ArrayList<QueueElement> FunctionQueue = new ArrayList<>();

    protected ArrayList<ImageButton> imgBtnsNormalQueue = new ArrayList<>();
    protected ArrayList<ImageButton> imgBtnsFunctionQueue = new ArrayList<>();

    protected Button btnMode;
    protected ProgressDialog mProgressDlg;
    protected ImageButton imgBtnConnectBluetooth;
    protected ImageButton imgBtnGo;
    protected ImageButton imgBtnClear;
    protected AlertDialog alerta;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btAdapter = BluetoothAdapter.getDefaultAdapter();
        checkBTState();

        initializeVariables();

        initializeNormalQueue();

        initializeFunctionQueue();

        IntentFilter filter = new IntentFilter();

        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        registerReceiver(mReceiver, filter);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if (address != null)
        {
            if (btAdapter != null) { if (btAdapter.isDiscovering()) { btAdapter.cancelDiscovery(); } }

            if (outStream != null)
            {
                try { outStream.flush(); }
                catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Unable to flush output stream", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }

            try { btSocket.close(); }
            catch (IOException e2) {
                Toast.makeText(getApplicationContext(), "Unable to close connection", Toast.LENGTH_LONG).show();
                e2.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if (address != null) {
            unregisterReceiver(mReceiver);
            try {
                btSocket.close();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "Unable to close connection", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == BLT_MAC_RETURN)
        {
            if (resultCode == RESULT_OK)
            {
                address = data.getStringExtra("MAC_ADDRESS");
                btConnect();
            }
            else if(resultCode == RESULT_CANCELED)
            {
                Toast.makeText(getApplicationContext(),"Try Again", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void btConnect()
    {
        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        try { btSocket = device.createRfcommSocketToServiceRecord(MY_UUID); }
        catch (IOException e)
        {
            Toast.makeText(getApplicationContext(),"Unable to create connection based on UUID", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        try
        {
            btSocket.connect();
            if(btSocket.isConnected()) { Toast.makeText(getApplicationContext(),"Connected", Toast.LENGTH_LONG).show(); }
        }
        catch (IOException e)  { Toast.makeText(getApplicationContext(),"Unable to open connection", Toast.LENGTH_LONG).show(); }

        try { outStream = btSocket.getOutputStream(); }
        catch (IOException e)
        {
            Toast.makeText(getApplicationContext(),"Unable to get output stream", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void initializeVariables()
    {
        imgBtnsNormalQueue.add((ImageButton) findViewById(R.id.imgButtonQueue0));
        imgBtnsNormalQueue.add((ImageButton) findViewById(R.id.imgButtonQueue1));
        imgBtnsNormalQueue.add((ImageButton) findViewById(R.id.imgButtonQueue2));
        imgBtnsNormalQueue.add((ImageButton) findViewById(R.id.imgButtonQueue3));
        imgBtnsNormalQueue.add((ImageButton) findViewById(R.id.imgButtonQueue4));
        imgBtnsNormalQueue.add((ImageButton) findViewById(R.id.imgButtonQueue5));
        imgBtnsNormalQueue.add((ImageButton) findViewById(R.id.imgButtonQueue6));
        imgBtnsNormalQueue.add((ImageButton) findViewById(R.id.imgButtonQueue7));
        imgBtnsNormalQueue.add((ImageButton) findViewById(R.id.imgButtonQueue8));
        imgBtnsNormalQueue.add((ImageButton) findViewById(R.id.imgButtonQueue9));

        imgBtnsFunctionQueue.add((ImageButton) findViewById(R.id.imgButtonFunction0));
        imgBtnsFunctionQueue.add((ImageButton) findViewById(R.id.imgButtonFunction1));
        imgBtnsFunctionQueue.add((ImageButton) findViewById(R.id.imgButtonFunction2));
        imgBtnsFunctionQueue.add((ImageButton) findViewById(R.id.imgButtonFunction3));
        imgBtnsFunctionQueue.add((ImageButton) findViewById(R.id.imgButtonFunction4));

        btnMode = (Button) findViewById(R.id.btnMode);
        btnMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("M");
            }
        });

        imgBtnGo = (ImageButton) findViewById(R.id.imgBtnGo);
        imgBtnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSocket.isConnected()) {
                    for (QueueElement e : NormalQueue) {
                        if (e.getCommand().equals("F")) {
                            for (QueueElement f : FunctionQueue) {
                                if (!f.getCommand().equals("N")) {
                                    sendData(f.getCommand());
                                }
                            }
                        } else if (!e.getCommand().equals("N")) {
                            sendData(e.getCommand());
                        }
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Not Connected",Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgBtnClear = (ImageButton) findViewById(R.id.imgBtnClear);
        imgBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(QueueElement e : NormalQueue) { e.setCommand("N");}
                reviseNormalQueue();
                for(QueueElement f : FunctionQueue) { f.setCommand("N");}
                reviseFunctionQueue();
            }
        });

        imgBtnConnectBluetooth = (ImageButton) findViewById(R.id.btnConnectBluetooth);
        imgBtnConnectBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                btAdapter.startDiscovery();
            }
        });

        mProgressDlg = new ProgressDialog(this);
        mProgressDlg.setMessage("Scanning...");
        mProgressDlg.setCancelable(false);
        mProgressDlg.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                btAdapter.cancelDiscovery();
            }
        });
    }

    private void initializeNormalQueue()
    {
        while(NormalQueue.size() < NORMAL_Q_SIZE)
        {
            QueueElement e = new QueueElement("N");
            NormalQueue.add(e);
        }
        reviseNormalQueue();

        for(int i=0;i<NORMAL_Q_SIZE;i++)
        {
            final int finalI = i;
            imgBtnsNormalQueue.get(finalI).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialogNormalQueue(finalI);
                }
            });
        }
    }

    private void initializeFunctionQueue()
    {
        while(FunctionQueue.size() < FUNCTION_Q_SIZE)
        {
            QueueElement e = new QueueElement("N");
            FunctionQueue.add(e);
        }
        reviseFunctionQueue();

        for(int i=0;i<FUNCTION_Q_SIZE;i++)
        {
            final int finalI = i;
            imgBtnsNormalQueue.get(finalI).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialogFunctionQueue(finalI);
                }
            });
        }
    }

    private void reviseNormalQueue()
    {
        for(int i=0;i<NORMAL_Q_SIZE;i++) {
            setBtnImageResource(imgBtnsNormalQueue.get(i), NormalQueue.get(i).getCommand());
        }
    }

    private void reviseFunctionQueue()
    {
        for(int i=0;i<FUNCTION_Q_SIZE;i++) {
            setBtnImageResource(imgBtnsFunctionQueue.get(i), FunctionQueue.get(i).getCommand());
        }
    }

    private void setBtnImageResource(ImageButton button, String command)
    {
        switch (command)
        {
            case "W":
            {button.setImageResource(R.drawable.uparrow);             break;}
            case "S":
            {button.setImageResource(R.drawable.downarrow);           break;}
            case "A":
            {button.setImageResource(R.drawable.leftarrow);           break;}
            case "D":
            {button.setImageResource(R.drawable.rightarrow);          break;}
            case "Q":
            {button.setImageResource(R.drawable.pause);               break;}
            case "N":
            {button.setImageResource(R.drawable.checkbox);            break;}
            case "F":
            {button.setImageResource(R.drawable.function);            break;}
        }
    }

    private void showDialogNormalQueue(final int index)
    {
        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.prompt_normal, null);
        view.findViewById(R.id.btnFoward).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NormalQueue.get(index).setCommand("W");
                reviseNormalQueue();
                alerta.dismiss();
            }
        });
        view.findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NormalQueue.get(index).setCommand("S");
                reviseNormalQueue();
                alerta.dismiss();
            }
        });
        view.findViewById(R.id.btnStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NormalQueue.get(index).setCommand("Q");
                reviseNormalQueue();
                alerta.dismiss();
            }
        });
        view.findViewById(R.id.btnLeft).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NormalQueue.get(index).setCommand("A");
                reviseNormalQueue();
                alerta.dismiss();
            }
        });
        view.findViewById(R.id.btnRight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NormalQueue.get(index).setCommand("D");
                reviseNormalQueue();
                alerta.dismiss();
            }
        });
        view.findViewById(R.id.btnDelCommand).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NormalQueue.remove(index);
                NormalQueue.add(new QueueElement("N"));
                reviseNormalQueue();
                alerta.dismiss();
            }
        });
        view.findViewById(R.id.btnFunction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NormalQueue.get(index).setCommand("F");
                reviseNormalQueue();
                alerta.dismiss();
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
    }

    private void showDialogFunctionQueue(final int index)
    {
        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.prompt_function, null);
        view.findViewById(R.id.btnFoward).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunctionQueue.get(index).setCommand("W");
                reviseFunctionQueue();
                alerta.dismiss();
            }
        });
        view.findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunctionQueue.get(index).setCommand("S");
                reviseFunctionQueue();
                alerta.dismiss();
            }
        });
        view.findViewById(R.id.btnStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunctionQueue.get(index).setCommand("Q");
                reviseFunctionQueue();
                alerta.dismiss();
            }
        });
        view.findViewById(R.id.btnLeft).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunctionQueue.get(index).setCommand("A");
                reviseFunctionQueue();
                alerta.dismiss();
            }
        });
        view.findViewById(R.id.btnRight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunctionQueue.get(index).setCommand("D");
                reviseFunctionQueue();
                alerta.dismiss();
            }
        });
        view.findViewById(R.id.btnDelCommand).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunctionQueue.remove(index);
                FunctionQueue.add(new QueueElement("N"));
                reviseFunctionQueue();
                alerta.dismiss();
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
    }

    public void sendData(String message)
    {
        byte[] msgBuffer = message.getBytes();
        try {
            outStream.write(msgBuffer);
            Toast.makeText(getApplicationContext(),"Enviando "+message,Toast.LENGTH_SHORT).show();
        }
        catch (IOException e)
        { Toast.makeText(getApplicationContext(),"Falha no envio",Toast.LENGTH_SHORT).show(); }
    }

    public void checkBTState()
    {
        if(btAdapter != null)
        {
            if(btAdapter.isEnabled())
            {
                Toast.makeText(getApplicationContext(), "Bluetooth already activated", Toast.LENGTH_LONG).show();
            }
            else
            {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                Toast.makeText(getApplicationContext(), "Bluetooth activated", Toast.LENGTH_LONG).show();
            }
        }
        else {Toast.makeText(getApplicationContext(),"Bluetooth not supported",Toast.LENGTH_LONG).show(); }
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);

                if (state == BluetoothAdapter.STATE_ON) {
                    Toast.makeText(getApplicationContext(), "Enabled", Toast.LENGTH_LONG).show();
                }
            }
            else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();
                mDeviceList = new ArrayList<>();
                mDeviceList.addAll(pairedDevices);

                mProgressDlg.show();
            }
            else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                mProgressDlg.dismiss();

                Intent newIntent = new Intent(MainActivity.this, DeviceSelectionActivity.class);

                newIntent.putParcelableArrayListExtra("device.list", mDeviceList);

                startActivityForResult(newIntent, BLT_MAC_RETURN);
            }
            else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                mDeviceList.add(device);

                Toast.makeText(getApplicationContext(),"Found device " + device.getName(),Toast.LENGTH_LONG).show();
            }
        }
    };
}