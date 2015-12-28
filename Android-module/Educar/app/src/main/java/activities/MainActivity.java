package activities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import objects.QueueElement;
import br.andremmfaria.projetofinal.educar.R;

public class MainActivity extends AppCompatActivity {

    protected ArrayList<QueueElement> NormalQueue = new ArrayList<>();
    protected ArrayList<QueueElement> FunctionQueue = new ArrayList<>();
    private AlertDialog alerta;

    protected String queue;

    //protected BluetoothActivity bluetoothActivity;

    ImageButton imgBtnQueue0;
    ImageButton imgBtnQueue1;
    ImageButton imgBtnQueue2;
    ImageButton imgBtnQueue3;
    ImageButton imgBtnQueue4;
    ImageButton imgBtnQueue5;
    ImageButton imgBtnQueue6;
    ImageButton imgBtnQueue7;
    ImageButton imgBtnQueue8;
    ImageButton imgBtnQueue9;

    ImageButton imgBtnFunction0;
    ImageButton imgBtnFunction1;
    ImageButton imgBtnFunction2;
    ImageButton imgBtnFunction3;
    ImageButton imgBtnFunction4;

    ImageButton imgBtnGo;

    Button btnConnectBluetooth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Intent i  = new Intent(getApplicationContext(), BluetoothActivity.class);
        startActivity(i);
        bluetoothActivity.btAdapter = BluetoothAdapter.getDefaultAdapter();
        bluetoothActivity.checkBTState();*/

        initializeVariables();

        normalQueue();

        functionQueue();
    }

    @Override
    public void onResume() {
        super.onResume();

        //bluetoothActivity.resume();
    }

    @Override
    public void onPause() {
        super.onPause();

        //bluetoothActivity.pause();
    }

    private void initializeVariables()
    {
        /*ArrayList<ImageButton> arraybutton = new ArrayList<>();
        arraybutton.add((ImageButton)findViewById(R.id.imgButtonQueue0));*/

        imgBtnQueue0 = (ImageButton) findViewById(R.id.imgButtonQueue0);
        imgBtnQueue1 = (ImageButton) findViewById(R.id.imgButtonQueue1);
        imgBtnQueue2 = (ImageButton) findViewById(R.id.imgButtonQueue2);
        imgBtnQueue3 = (ImageButton) findViewById(R.id.imgButtonQueue3);
        imgBtnQueue4 = (ImageButton) findViewById(R.id.imgButtonQueue4);
        imgBtnQueue5 = (ImageButton) findViewById(R.id.imgButtonQueue5);
        imgBtnQueue6 = (ImageButton) findViewById(R.id.imgButtonQueue6);
        imgBtnQueue7 = (ImageButton) findViewById(R.id.imgButtonQueue7);
        imgBtnQueue8 = (ImageButton) findViewById(R.id.imgButtonQueue8);
        imgBtnQueue9 = (ImageButton) findViewById(R.id.imgButtonQueue9);

        imgBtnFunction0 = (ImageButton) findViewById(R.id.imgButtonFunction0);
        imgBtnFunction1 = (ImageButton) findViewById(R.id.imgButtonFunction1);
        imgBtnFunction2 = (ImageButton) findViewById(R.id.imgButtonFunction2);
        imgBtnFunction3 = (ImageButton) findViewById(R.id.imgButtonFunction3);
        imgBtnFunction4 = (ImageButton) findViewById(R.id.imgButtonFunction4);

        imgBtnGo = (ImageButton) findViewById(R.id.imgBtnGo);
        /*imgBtnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                label:
                for (QueueElement e : NormalQueue) {
                    switch (e.getCommand()) {
                        case "F":
                            for (QueueElement f : FunctionQueue)
                            {
                                if (f.getCommand().equals("N")) { break; }
                                bluetoothActivity.sendData(f.getCommand());
                            }
                            break;
                        case "N":
                            break label;
                        default:
                            bluetoothActivity.sendData(e.getCommand());
                            break;
                    }
                }
            }
        });*/

        /*btnConnectBluetooth = (Button) findViewById(R.id.btnConnectBluetooth);
        btnConnectBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ResolveInfo> list = getPackageManager().queryIntentActivities(
                        new Intent(getApplicationContext(),BluetoothActivity.class), PackageManager.MATCH_DEFAULT_ONLY);
                if (list.size() > 0) {
                    Intent i = new Intent(getApplicationContext(), BluetoothActivity.class);
                    startActivity(i);
                    bluetoothActivity.btAdapter = BluetoothAdapter.getDefaultAdapter();
                    bluetoothActivity.checkBTState();
                }
            }
        });*/
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
                    setBtnImageResource(imgBtnQueue0, command);
                    break;
                }
                case 1:
                {
                    setBtnImageResource(imgBtnQueue1, command);
                    break;
                }
                case 2:
                {
                    setBtnImageResource(imgBtnQueue2, command);
                    break;
                }
                case 3:
                {
                    setBtnImageResource(imgBtnQueue3, command);
                    break;
                }
                case 4:
                {
                    setBtnImageResource(imgBtnQueue4, command);
                    break;
                }
                case 5:
                {
                    setBtnImageResource(imgBtnQueue5, command);
                    break;
                }
                case 6:
                {
                    setBtnImageResource(imgBtnQueue6, command);
                    break;
                }
                case 7:
                {
                    setBtnImageResource(imgBtnQueue7, command);
                    break;
                }
                case 8:
                {
                    setBtnImageResource(imgBtnQueue8, command);
                    break;
                }
                case 9:
                {
                    setBtnImageResource(imgBtnQueue9, command);
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
                    setBtnImageResource(imgBtnFunction0, command);
                    break;
                }
                case 1:
                {
                    setBtnImageResource(imgBtnFunction1, command);
                    break;
                }
                case 2:
                {
                    setBtnImageResource(imgBtnFunction2, command);
                    break;
                }
                case 3:
                {
                    setBtnImageResource(imgBtnFunction3, command);
                    break;
                }
                case 4:
                {
                    setBtnImageResource(imgBtnFunction4, command);
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

        imgBtnQueue0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupNormalQueue(0);
            }
        });
        imgBtnQueue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupNormalQueue(1);
            }
        });
        imgBtnQueue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupNormalQueue(2);
            }
        });
        imgBtnQueue3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupNormalQueue(3);
            }
        });
        imgBtnQueue4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupNormalQueue(4);
            }
        });
        imgBtnQueue5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupNormalQueue(5);
            }
        });
        imgBtnQueue6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupNormalQueue(6);
            }
        });
        imgBtnQueue7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupNormalQueue(7);
            }
        });
        imgBtnQueue8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupNormalQueue(8);
            }
        });
        imgBtnQueue9.setOnClickListener(new View.OnClickListener() {
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

        imgBtnFunction0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupFunctionQueue(0);
            }
        });
        imgBtnFunction1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupFunctionQueue(1);
            }
        });
        imgBtnFunction2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupFunctionQueue(2);
            }
        });
        imgBtnFunction3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupFunctionQueue(3);
            }
        });
        imgBtnFunction4.setOnClickListener(new View.OnClickListener() {
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
                FunctionQueue.get(index).setCommand("N");
                reviseFunctionQueue();
                alerta.dismiss();
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
    }
}
