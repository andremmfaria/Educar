package Activities;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import Objects.QueueElement;
import br.andremmfaria.projetofinal.educar.R;

public class MainActivity extends AppCompatActivity {

    protected ArrayList<QueueElement> NormalQueue = new ArrayList<>();
    protected ArrayList<QueueElement> FunctionQueue = new ArrayList<>();
    private AlertDialog alerta;

    protected BluetoothActivity bluetoothActivity;

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

        Intent i  = new Intent(getApplicationContext(),BluetoothActivity.class);
        startActivity(i);
        bluetoothActivity.btAdapter = BluetoothAdapter.getDefaultAdapter();
        bluetoothActivity.checkBTState();

        initializeVariables();

        normalQueue();

        functionQueue();
    }

    @Override
    public void onResume() {
        super.onResume();

        bluetoothActivity.resume();
    }

    @Override
    public void onPause() {
        super.onPause();

        bluetoothActivity.pause();
    }

    private String setDirectionNormalQueue()
    {
        final String[] queue = new String[1];
        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.prompt, null);
        view.findViewById(R.id.btnFoward).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queue[0] = "W";
                alerta.dismiss();
            }
        });
        view.findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queue[0] = "S";
                alerta.dismiss();
            }
        });
        view.findViewById(R.id.btnStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queue[0] = "Q";
                alerta.dismiss();
            }
        });
        view.findViewById(R.id.btnLeft).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queue[0] = "A";
                alerta.dismiss();
            }
        });
        view.findViewById(R.id.btnRight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queue[0] = "D";
                alerta.dismiss();
            }
        });
        view.findViewById(R.id.btnDelCommand).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queue[0] = "N";
                alerta.dismiss();
            }
        });
        view.findViewById(R.id.btnFunction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queue[0] = "F";
                alerta.dismiss();
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
        return queue[0];
    }

    private String setDirectionFunctionQueue()
    {
        final String[] queue = new String[1];
        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.prompt_function, null);
        view.findViewById(R.id.btnFoward).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queue[0] = "W";
                alerta.dismiss();
            }
        });
        view.findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queue[0] = "S";
                alerta.dismiss();
            }
        });
        view.findViewById(R.id.btnStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queue[0] = "Q";
                alerta.dismiss();
            }
        });
        view.findViewById(R.id.btnLeft).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queue[0] = "A";
                alerta.dismiss();
            }
        });
        view.findViewById(R.id.btnRight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queue[0] = "D";
                alerta.dismiss();
            }
        });
        view.findViewById(R.id.btnDelCommand).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queue[0] = "N";
                alerta.dismiss();
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
        return queue[0];
    }

    private void initializeVariables()
    {
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
        imgBtnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(QueueElement e : NormalQueue)
                {
                    if(e.getCommand().equals("F"))
                    {
                        for(QueueElement f : FunctionQueue)
                        {
                            bluetoothActivity.sendData(f.getCommand());
                        }
                    }
                    bluetoothActivity.sendData(e.getCommand());
                }
            }
        });

        btnConnectBluetooth = (Button) findViewById(R.id.btnConnectBluetooth);
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
        });
    }

    private void reviseQueue(ArrayList<QueueElement> Queue)
    {
        for(QueueElement e : Queue)
        {
            if(e.getCommand() == null)
            {
                Queue.remove(e);
                Queue.add(new QueueElement("N"));
            }
        }

        if(Queue.size() == 10)
        {
            int i;
            for(i=0;i<10;i++)
            {
                switch (i)
                {
                    case 0:
                    {
                        switch (Queue.get(i).getCommand())
                        {
                            case "W":
                            {imgBtnQueue0.setImageResource(R.drawable.uparrow);             break;}
                            case "S":
                            {imgBtnQueue0.setImageResource(R.drawable.downarrow);           break;}
                            case "A":
                            {imgBtnQueue0.setImageResource(R.drawable.leftarrow);           break;}
                            case "D":
                            {imgBtnQueue0.setImageResource(R.drawable.rightarrow);          break;}
                            case "Q":
                            {imgBtnQueue0.setImageResource(R.drawable.pause);                break;}
                            case "F":
                            {imgBtnQueue0.setImageResource(R.drawable.stop);               break;}
                            case "N":
                            {imgBtnQueue0.setImageResource(R.drawable.uncheckedcheckbox);   break;}
                        }
                        break;
                    }
                    case 1:
                    {
                        switch (Queue.get(i).getCommand())
                        {
                            case "W":
                            {imgBtnQueue1.setImageResource(R.drawable.uparrow);             break;}
                            case "S":
                            {imgBtnQueue1.setImageResource(R.drawable.downarrow);           break;}
                            case "A":
                            {imgBtnQueue1.setImageResource(R.drawable.leftarrow);           break;}
                            case "D":
                            {imgBtnQueue1.setImageResource(R.drawable.rightarrow);          break;}
                            case "Q":
                            {imgBtnQueue1.setImageResource(R.drawable.pause);                break;}
                            case "F":
                            {imgBtnQueue1.setImageResource(R.drawable.stop);               break;}
                            case "N":
                            {imgBtnQueue1.setImageResource(R.drawable.uncheckedcheckbox);   break;}
                        }
                        break;
                    }
                    case 2:
                    {
                        switch (Queue.get(i).getCommand())
                        {
                            case "W":
                            {imgBtnQueue2.setImageResource(R.drawable.uparrow);             break;}
                            case "S":
                            {imgBtnQueue2.setImageResource(R.drawable.downarrow);           break;}
                            case "A":
                            {imgBtnQueue2.setImageResource(R.drawable.leftarrow);           break;}
                            case "D":
                            {imgBtnQueue2.setImageResource(R.drawable.rightarrow);          break;}
                            case "Q":
                            {imgBtnQueue2.setImageResource(R.drawable.pause);                break;}
                            case "F":
                            {imgBtnQueue2.setImageResource(R.drawable.stop);               break;}
                            case "N":
                            {imgBtnQueue2.setImageResource(R.drawable.uncheckedcheckbox);   break;}
                        }
                        break;
                    }
                    case 3:
                    {
                        switch (Queue.get(i).getCommand())
                        {
                            case "W":
                            {imgBtnQueue3.setImageResource(R.drawable.uparrow);             break;}
                            case "S":
                            {imgBtnQueue3.setImageResource(R.drawable.downarrow);           break;}
                            case "A":
                            {imgBtnQueue3.setImageResource(R.drawable.leftarrow);           break;}
                            case "D":
                            {imgBtnQueue3.setImageResource(R.drawable.rightarrow);          break;}
                            case "Q":
                            {imgBtnQueue3.setImageResource(R.drawable.pause);                break;}
                            case "F":
                            {imgBtnQueue3.setImageResource(R.drawable.stop);               break;}
                            case "N":
                            {imgBtnQueue3.setImageResource(R.drawable.uncheckedcheckbox);   break;}
                        }
                        break;
                    }
                    case 4:
                    {
                        switch (Queue.get(i).getCommand())
                        {
                            case "W":
                            {imgBtnQueue4.setImageResource(R.drawable.uparrow);             break;}
                            case "S":
                            {imgBtnQueue4.setImageResource(R.drawable.downarrow);           break;}
                            case "A":
                            {imgBtnQueue4.setImageResource(R.drawable.leftarrow);           break;}
                            case "D":
                            {imgBtnQueue4.setImageResource(R.drawable.rightarrow);          break;}
                            case "Q":
                            {imgBtnQueue4.setImageResource(R.drawable.pause);                break;}
                            case "F":
                            {imgBtnQueue4.setImageResource(R.drawable.stop);               break;}
                            case "N":
                            {imgBtnQueue4.setImageResource(R.drawable.uncheckedcheckbox);   break;}
                        }
                        break;
                    }
                    case 5:
                    {
                        switch (Queue.get(i).getCommand())
                        {
                            case "W":
                            {imgBtnQueue5.setImageResource(R.drawable.uparrow);             break;}
                            case "S":
                            {imgBtnQueue5.setImageResource(R.drawable.downarrow);           break;}
                            case "A":
                            {imgBtnQueue5.setImageResource(R.drawable.leftarrow);           break;}
                            case "D":
                            {imgBtnQueue5.setImageResource(R.drawable.rightarrow);          break;}
                            case "Q":
                            {imgBtnQueue5.setImageResource(R.drawable.pause);                break;}
                            case "F":
                            {imgBtnQueue5.setImageResource(R.drawable.stop);               break;}
                            case "N":
                            {imgBtnQueue5.setImageResource(R.drawable.uncheckedcheckbox);   break;}
                        }
                        break;
                    }
                    case 6:
                    {
                        switch (Queue.get(i).getCommand())
                        {
                            case "W":
                            {imgBtnQueue6.setImageResource(R.drawable.uparrow);             break;}
                            case "S":
                            {imgBtnQueue6.setImageResource(R.drawable.downarrow);           break;}
                            case "A":
                            {imgBtnQueue6.setImageResource(R.drawable.leftarrow);           break;}
                            case "D":
                            {imgBtnQueue6.setImageResource(R.drawable.rightarrow);          break;}
                            case "Q":
                            {imgBtnQueue6.setImageResource(R.drawable.pause);                break;}
                            case "F":
                            {imgBtnQueue6.setImageResource(R.drawable.stop);               break;}
                            case "N":
                            {imgBtnQueue6.setImageResource(R.drawable.uncheckedcheckbox);   break;}
                        }
                        break;
                    }
                    case 7:
                    {
                        switch (Queue.get(i).getCommand())
                        {
                            case "W":
                            {imgBtnQueue7.setImageResource(R.drawable.uparrow);             break;}
                            case "S":
                            {imgBtnQueue7.setImageResource(R.drawable.downarrow);           break;}
                            case "A":
                            {imgBtnQueue7.setImageResource(R.drawable.leftarrow);           break;}
                            case "D":
                            {imgBtnQueue7.setImageResource(R.drawable.rightarrow);          break;}
                            case "Q":
                            {imgBtnQueue7.setImageResource(R.drawable.pause);                break;}
                            case "F":
                            {imgBtnQueue7.setImageResource(R.drawable.stop);               break;}
                            case "N":
                            {imgBtnQueue7.setImageResource(R.drawable.uncheckedcheckbox);   break;}
                        }
                        break;
                    }
                    case 8:
                    {
                        switch (Queue.get(i).getCommand())
                        {
                            case "W":
                            {imgBtnQueue8.setImageResource(R.drawable.uparrow);             break;}
                            case "S":
                            {imgBtnQueue8.setImageResource(R.drawable.downarrow);           break;}
                            case "A":
                            {imgBtnQueue8.setImageResource(R.drawable.leftarrow);           break;}
                            case "D":
                            {imgBtnQueue8.setImageResource(R.drawable.rightarrow);          break;}
                            case "Q":
                            {imgBtnQueue8.setImageResource(R.drawable.pause);                break;}
                            case "F":
                            {imgBtnQueue8.setImageResource(R.drawable.stop);               break;}
                            case "N":
                            {imgBtnQueue8.setImageResource(R.drawable.uncheckedcheckbox);   break;}
                        }
                        break;
                    }
                    case 9:
                    {
                        switch (Queue.get(i).getCommand())
                        {
                            case "W":
                            {imgBtnQueue9.setImageResource(R.drawable.uparrow);             break;}
                            case "S":
                            {imgBtnQueue9.setImageResource(R.drawable.downarrow);           break;}
                            case "A":
                            {imgBtnQueue9.setImageResource(R.drawable.leftarrow);           break;}
                            case "D":
                            {imgBtnQueue9.setImageResource(R.drawable.rightarrow);          break;}
                            case "Q":
                            {imgBtnQueue9.setImageResource(R.drawable.pause);                break;}
                            case "F":
                            {imgBtnQueue9.setImageResource(R.drawable.stop);               break;}
                            case "N":
                            {imgBtnQueue9.setImageResource(R.drawable.uncheckedcheckbox);   break;}
                        }
                        break;
                    }
                }
            }
        }
        else if(Queue.size() == 5)
        {
            int i;
            for(i=0;i<10;i++)
            {
                switch (i)
                {
                    case 0:
                    {
                        switch (Queue.get(i).getCommand())
                        {
                            case "W":
                            {imgBtnFunction0.setImageResource(R.drawable.uparrow);             break;}
                            case "S":
                            {imgBtnFunction0.setImageResource(R.drawable.downarrow);           break;}
                            case "A":
                            {imgBtnFunction0.setImageResource(R.drawable.leftarrow);           break;}
                            case "D":
                            {imgBtnFunction0.setImageResource(R.drawable.rightarrow);          break;}
                            case "Q":
                            {imgBtnFunction0.setImageResource(R.drawable.pause);                break;}
                            case "N":
                            {imgBtnFunction0.setImageResource(R.drawable.uncheckedcheckbox);   break;}
                        }
                        break;
                    }
                    case 1:
                    {
                        switch (Queue.get(i).getCommand())
                        {
                            case "W":
                            {imgBtnFunction1.setImageResource(R.drawable.uparrow);             break;}
                            case "S":
                            {imgBtnFunction1.setImageResource(R.drawable.downarrow);           break;}
                            case "A":
                            {imgBtnFunction1.setImageResource(R.drawable.leftarrow);           break;}
                            case "D":
                            {imgBtnFunction1.setImageResource(R.drawable.rightarrow);          break;}
                            case "Q":
                            {imgBtnFunction1.setImageResource(R.drawable.pause);                break;}
                            case "N":
                            {imgBtnFunction1.setImageResource(R.drawable.uncheckedcheckbox);   break;}
                        }
                        break;
                    }
                    case 2:
                    {
                        switch (Queue.get(i).getCommand())
                        {
                            case "W":
                            {imgBtnFunction2.setImageResource(R.drawable.uparrow);             break;}
                            case "S":
                            {imgBtnFunction2.setImageResource(R.drawable.downarrow);           break;}
                            case "A":
                            {imgBtnFunction2.setImageResource(R.drawable.leftarrow);           break;}
                            case "D":
                            {imgBtnFunction2.setImageResource(R.drawable.rightarrow);          break;}
                            case "Q":
                            {imgBtnFunction2.setImageResource(R.drawable.pause);                break;}
                            case "N":
                            {imgBtnFunction2.setImageResource(R.drawable.uncheckedcheckbox);   break;}
                        }
                        break;
                    }
                    case 3:
                    {
                        switch (Queue.get(i).getCommand())
                        {
                            case "W":
                            {imgBtnFunction3.setImageResource(R.drawable.uparrow);             break;}
                            case "S":
                            {imgBtnFunction3.setImageResource(R.drawable.downarrow);           break;}
                            case "A":
                            {imgBtnFunction3.setImageResource(R.drawable.leftarrow);           break;}
                            case "D":
                            {imgBtnFunction3.setImageResource(R.drawable.rightarrow);          break;}
                            case "Q":
                            {imgBtnFunction3.setImageResource(R.drawable.pause);                break;}
                            case "N":
                            {imgBtnFunction3.setImageResource(R.drawable.uncheckedcheckbox);   break;}
                        }
                        break;
                    }
                    case 4:
                    {
                        switch (Queue.get(i).getCommand())
                        {
                            case "W":
                            {imgBtnFunction4.setImageResource(R.drawable.uparrow);             break;}
                            case "S":
                            {imgBtnFunction4.setImageResource(R.drawable.downarrow);           break;}
                            case "A":
                            {imgBtnFunction4.setImageResource(R.drawable.leftarrow);           break;}
                            case "D":
                            {imgBtnFunction4.setImageResource(R.drawable.rightarrow);          break;}
                            case "Q":
                            {imgBtnFunction4.setImageResource(R.drawable.pause);                break;}
                            case "N":
                            {imgBtnFunction4.setImageResource(R.drawable.uncheckedcheckbox);   break;}
                        }
                        break;
                    }
                }
            }
        }
    }

    private void normalQueue()
    {
        while(NormalQueue.size() <= 10)
        {
            QueueElement e = new QueueElement("N");
            NormalQueue.add(e);
        }

        imgBtnQueue0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NormalQueue.get(0).setCommand(setDirectionNormalQueue());
                reviseQueue(NormalQueue);
            }
        });

        imgBtnQueue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NormalQueue.get(1).setCommand(setDirectionNormalQueue());
                reviseQueue(NormalQueue);
            }
        });

        imgBtnQueue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NormalQueue.get(2).setCommand(setDirectionNormalQueue());
                reviseQueue(NormalQueue);
            }
        });

        imgBtnQueue3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NormalQueue.get(3).setCommand(setDirectionNormalQueue());
                reviseQueue(NormalQueue);
            }
        });

        imgBtnQueue4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NormalQueue.get(4).setCommand(setDirectionNormalQueue());
                reviseQueue(NormalQueue);
            }
        });

        imgBtnQueue5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NormalQueue.get(5).setCommand(setDirectionNormalQueue());
                reviseQueue(NormalQueue);
            }
        });

        imgBtnQueue6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NormalQueue.get(6).setCommand(setDirectionNormalQueue());
                reviseQueue(NormalQueue);
            }
        });

        imgBtnQueue7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NormalQueue.get(7).setCommand(setDirectionNormalQueue());
                reviseQueue(NormalQueue);
            }
        });

        imgBtnQueue8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NormalQueue.get(8).setCommand(setDirectionNormalQueue());
                reviseQueue(NormalQueue);
            }
        });

        imgBtnQueue9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NormalQueue.get(9).setCommand(setDirectionNormalQueue());
                reviseQueue(NormalQueue);
            }
        });

    }

    private void functionQueue()
    {
        while(FunctionQueue.size() <= 5)
        {
            QueueElement e = new QueueElement("N");
            FunctionQueue.add(e);
        }

        imgBtnFunction0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunctionQueue.get(0).setCommand(setDirectionFunctionQueue());
                reviseQueue(FunctionQueue);
            }
        });
        imgBtnFunction1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunctionQueue.get(1).setCommand(setDirectionFunctionQueue());
                reviseQueue(FunctionQueue);
            }
        });
        imgBtnFunction2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunctionQueue.get(2).setCommand(setDirectionFunctionQueue());
                reviseQueue(FunctionQueue);
            }
        });
        imgBtnFunction3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunctionQueue.get(3).setCommand(setDirectionFunctionQueue());
                reviseQueue(FunctionQueue);
            }
        });
        imgBtnFunction4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunctionQueue.get(4).setCommand(setDirectionFunctionQueue());
                reviseQueue(FunctionQueue);
            }
        });
    }
}
