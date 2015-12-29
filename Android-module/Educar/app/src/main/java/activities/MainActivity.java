package activities;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
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
import java.util.UUID;

import objects.QueueElement;
import br.andremmfaria.projetofinal.educar.R;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 1;
    public BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private OutputStream outStream = null;

    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private static String address = "98:D3:31:B3:E5:33";

    protected ArrayList<QueueElement> NormalQueue = new ArrayList<>();
    protected ArrayList<QueueElement> FunctionQueue = new ArrayList<>();

    protected ArrayList<ImageButton> imgBtnsNormalQueue = new ArrayList<>();
    protected ArrayList<ImageButton> imgBtnsFunctionQueue = new ArrayList<>();

    protected Button btnConnectBluetooth;
    protected ImageButton imgBtnGo;
    protected AlertDialog alerta;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btAdapter = BluetoothAdapter.getDefaultAdapter();
        checkBTState();

        initializeVariables();

        normalQueue();

        functionQueue();
    }

    @Override
    public void onResume()
    {
        super.onResume();

        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        try
        {
            btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
        }
        catch (IOException e)
        {
            errorExit("Fatal Error", "In onResume() and socket create failed: " + e.getMessage() + ".");
        }

        btAdapter.cancelDiscovery();

        try
        {
            btSocket.connect();
        }
        catch (IOException e)
        {
            try
            {
                btSocket.close();
            }
            catch (IOException e2)
            {
                errorExit("Fatal Error", "In onResume() and unable to close socket during connection failure" + e2.getMessage() + ".");
            }
        }

        try
        {
            outStream = btSocket.getOutputStream();
        }
        catch (IOException e)
        {
            errorExit("Fatal Error", "In onResume() and output stream creation failed:" + e.getMessage() + ".");
        }

    }

    @Override
    public void onPause()
    {
        super.onPause();

        if (outStream != null)
        {
            try
            {
                outStream.flush();
            }
            catch (IOException e)
            {
                errorExit("Fatal Error", "In onPause() and failed to flush output stream: " + e.getMessage() + ".");
            }
        }

        try
        {
            btSocket.close();
        }
        catch (IOException e2)
        {
            errorExit("Fatal Error", "In onPause() and failed to close socket." + e2.getMessage() + ".");
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

        imgBtnGo = (ImageButton) findViewById(R.id.imgBtnGo);
        imgBtnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (QueueElement e : NormalQueue)
                {
                    if(e.getCommand().equals("F"))
                    {
                        for (QueueElement f : FunctionQueue)
                            {
                                if (!f.getCommand().equals("N"))
                                {
                                    sendData(f.getCommand());
                                }
                                Toast.makeText(getApplicationContext(), "Enviando fila de funcao", Toast.LENGTH_SHORT).show();
                            }
                        }
                    else if (!e.getCommand().equals("N"))
                    {
                        sendData(e.getCommand());
                        Toast.makeText(getApplicationContext(), "Enviando fila normal", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnConnectBluetooth = (Button) findViewById(R.id.btnConnectBluetooth);
        btnConnectBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(),"Reconectando bluetooth", Toast.LENGTH_LONG).show();

                BluetoothDevice device = btAdapter.getRemoteDevice(address);

                try
                {
                    btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
                }
                catch (IOException e)
                {
                    errorExit("Fatal Error", "In onResume() and socket create failed: " + e.getMessage() + ".");
                }

                btAdapter.cancelDiscovery();

                try
                {
                    btSocket.connect();
                }
                catch (IOException e)
                {
                    try
                    {
                        btSocket.close();
                    }
                    catch (IOException e2)
                    {
                        errorExit("Fatal Error", "In onResume() and unable to close socket during connection failure" + e2.getMessage() + ".");
                    }
                }

                try
                {
                    outStream = btSocket.getOutputStream();
                }
                catch (IOException e)
                {
                    errorExit("Fatal Error", "In onResume() and output stream creation failed:" + e.getMessage() + ".");
                }
            }
        });
    }

    private void reviseNormalQueue()
    {
        int i;
        for(i=0;i<10;i++)
        {
            String command = NormalQueue.get(i).getCommand();
            switch (i)
            {
                case 0:
                {
                    setBtnImageResource(imgBtnsNormalQueue.get(0), command);
                    break;
                }
                case 1:
                {
                    setBtnImageResource(imgBtnsNormalQueue.get(1), command);
                    break;
                }
                case 2:
                {
                    setBtnImageResource(imgBtnsNormalQueue.get(2), command);
                    break;
                }
                case 3:
                {
                    setBtnImageResource(imgBtnsNormalQueue.get(3), command);
                    break;
                }
                case 4:
                {
                    setBtnImageResource(imgBtnsNormalQueue.get(4), command);
                    break;
                }
                case 5:
                {
                    setBtnImageResource(imgBtnsNormalQueue.get(5), command);
                    break;
                }
                case 6:
                {
                    setBtnImageResource(imgBtnsNormalQueue.get(6), command);
                    break;
                }
                case 7:
                {
                    setBtnImageResource(imgBtnsNormalQueue.get(7), command);
                    break;
                }
                case 8:
                {
                    setBtnImageResource(imgBtnsNormalQueue.get(8), command);
                    break;
                }
                case 9:
                {
                    setBtnImageResource(imgBtnsNormalQueue.get(9), command);
                    break;
                }
            }

        }
    }

    private void reviseFunctionQueue()
    {
        int i;
        for(i=0;i<5;i++)
        {
            String command = FunctionQueue.get(i).getCommand();
            switch (i)
            {
                case 0:
                {
                    setBtnImageResource(imgBtnsFunctionQueue.get(0), command);
                    break;
                }
                case 1:
                {
                    setBtnImageResource(imgBtnsFunctionQueue.get(1), command);
                    break;
                }
                case 2:
                {
                    setBtnImageResource(imgBtnsFunctionQueue.get(2), command);
                    break;
                }
                case 3:
                {
                    setBtnImageResource(imgBtnsFunctionQueue.get(3), command);
                    break;
                }
                case 4:
                {
                    setBtnImageResource(imgBtnsFunctionQueue.get(4), command);
                    break;
                }
            }
        }
    }

    private void normalQueue()
    {
        while(NormalQueue.size() < 10)
        {
            QueueElement e = new QueueElement("N");
            NormalQueue.add(e);
        }
        reviseNormalQueue();

        imgBtnsNormalQueue.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupNormalQueue(0);
            }
        });
        imgBtnsNormalQueue.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupNormalQueue(1);
            }
        });
        imgBtnsNormalQueue.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupNormalQueue(2);
            }
        });
        imgBtnsNormalQueue.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupNormalQueue(3);
            }
        });
        imgBtnsNormalQueue.get(4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupNormalQueue(4);
            }
        });
        imgBtnsNormalQueue.get(5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupNormalQueue(5);
            }
        });
        imgBtnsNormalQueue.get(6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupNormalQueue(6);
            }
        });
        imgBtnsNormalQueue.get(7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupNormalQueue(7);
            }
        });
        imgBtnsNormalQueue.get(8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupNormalQueue(8);
            }
        });
        imgBtnsNormalQueue.get(9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupNormalQueue(9);
            }
        });
    }

    private void functionQueue()
    {
        while(FunctionQueue.size() < 5)
        {
            QueueElement e = new QueueElement("N");
            FunctionQueue.add(e);
        }
        reviseFunctionQueue();

        imgBtnsFunctionQueue.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupFunctionQueue(0);
            }
        });
        imgBtnsFunctionQueue.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupFunctionQueue(1);
            }
        });
        imgBtnsFunctionQueue.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupFunctionQueue(2);
            }
        });
        imgBtnsFunctionQueue.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupFunctionQueue(3);
            }
        });
        imgBtnsFunctionQueue.get(4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupFunctionQueue(4);
            }
        });
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
            {button.setImageResource(R.drawable.stop);                break;}
        }
    }

    private void showPopupNormalQueue(final int index)
    {
        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.prompt, null);
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

    private void showPopupFunctionQueue(final int index)
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

        try
        {
            outStream.write(msgBuffer);
        }
        catch (IOException e)
        {
            String msg = "In onResume() and an exception occurred during write: " + e.getMessage();
            if (address.equals("00:00:00:00:00:00"))
            {
                msg = msg + ".\n\nUpdate your server address from 00:00:00:00:00:00 to the correct address on line 37 in the java code";
            }
            msg = msg +  ".\n\nCheck that the SPP UUID: " + MY_UUID.toString() + " exists on server.\n\n";

            errorExit("Fatal Error", msg);
        }
    }

    public void checkBTState()
    {
        if(btAdapter==null)
        {
            errorExit("Fatal Error", "BluetoothActivity Not supported. Aborting.");
        }
        else
        {
            if (btAdapter.isEnabled())
            {
                Toast.makeText(getApplicationContext(), "Bluetooth activated", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
    }

    private void errorExit(String title, String message)
    {
        Toast msg = Toast.makeText(getApplicationContext(), title + " - " + message, Toast.LENGTH_SHORT);
        msg.show();
        finish();
    }
}