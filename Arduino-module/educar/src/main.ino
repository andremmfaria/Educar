/*
 * main.ino
 * Main project file.
 * Created by Andre Faria
 * Date 2015-09-16
 */

#include <SoftwareSerial.h>
#include "../lib/movement.h"
#include "../lib/tracking.h"
#include "../lib/pins.h"

char recv;
int lftsns;
int mdlsns;
int rgtsns;
int vlpna = 180;
int vlpnb = 180;
int mode = 0;
int dilay = 800;

movement move(MTR_CNTR_PIN_1,MTR_CNTR_PIN_2,MTR_CNTR_PIN_3,
        MTR_CNTR_PIN_4,MTR_ENAB_PIN_A,MTR_ENAB_PIN_B,vlpna,vlpnb);

tracking trck(TRK_SENS_PIN_L,TRK_SENS_PIN_M,TRK_SENS_PIN_R);

SoftwareSerial btserial(BLT_RDIO_PIN_R,BLT_RDIO_PIN_T);

void setup()
{
    btserial.begin(9600);
}

void loop()
{
    if (mode == 0)
    {
        recv = btserial.read();
        btserial.write(recv);
        if(recv != -1)
        {
            switch(recv)
            {
                case ('W'):
                    {
                        move.fwd(MTR_CNTR_PIN_1,MTR_CNTR_PIN_2,MTR_CNTR_PIN_3,
                                MTR_CNTR_PIN_4,MTR_ENAB_PIN_A,MTR_ENAB_PIN_B,vlpna,vlpnb);
                        break;
                    }
                case ('A'):
                    {
                        move.lft(MTR_CNTR_PIN_1,MTR_CNTR_PIN_2,MTR_CNTR_PIN_3,
                                MTR_CNTR_PIN_4,MTR_ENAB_PIN_A,MTR_ENAB_PIN_B,vlpna,vlpnb);
                        break;
                    }
                case ('D'):
                    {
                        move.rgt(MTR_CNTR_PIN_1,MTR_CNTR_PIN_2,MTR_CNTR_PIN_3,
                                MTR_CNTR_PIN_4,MTR_ENAB_PIN_A,MTR_ENAB_PIN_B,vlpna,vlpnb);
                        break;
                    }
                case ('S'):
                    {
                        move.bck(MTR_CNTR_PIN_1,MTR_CNTR_PIN_2,MTR_CNTR_PIN_3,
                                MTR_CNTR_PIN_4,MTR_ENAB_PIN_A,MTR_ENAB_PIN_B,vlpna,vlpnb);
                        break;
                    }
                case ('Q'):
                    {
                        move.stp(MTR_CNTR_PIN_1,MTR_CNTR_PIN_2,MTR_CNTR_PIN_3,MTR_CNTR_PIN_4);
                        break;
                    }
                case('M'):
                    {
                        mode = 1;
                        break;
                    }
            }
            delay(dilay);
        }
        else
        { 
            move.stp(MTR_CNTR_PIN_1,MTR_CNTR_PIN_2,MTR_CNTR_PIN_3,MTR_CNTR_PIN_4);
            delay(dilay);
        }

    }
    else if (mode == 1)
    {
        recv = btserial.read();
        btserial.write(recv);
        if(recv == 'M') { mode = 0; }

        lftsns = trck.getsensleft(TRK_SENS_PIN_L);
        mdlsns = trck.getsensmidl(TRK_SENS_PIN_M);
        rgtsns = trck.getsensrigt(TRK_SENS_PIN_R);

        if(mdlsns == LOW)
        {
            if(lftsns == LOW && rgtsns == HIGH)
            {
                move.lft(MTR_CNTR_PIN_1,MTR_CNTR_PIN_2,MTR_CNTR_PIN_3,
                        MTR_CNTR_PIN_4,MTR_ENAB_PIN_A,MTR_ENAB_PIN_B,vlpna,vlpnb);
            }
            else if(lftsns == HIGH && rgtsns == LOW)
            {
                move.rgt(MTR_CNTR_PIN_1,MTR_CNTR_PIN_2,MTR_CNTR_PIN_3,
                        MTR_CNTR_PIN_4,MTR_ENAB_PIN_A,MTR_ENAB_PIN_B,vlpna,vlpnb);
            }
            else
            {
                move.fwd(MTR_CNTR_PIN_1,MTR_CNTR_PIN_2,MTR_CNTR_PIN_3,
                        MTR_CNTR_PIN_4,MTR_ENAB_PIN_A,MTR_ENAB_PIN_B,vlpna,vlpnb);
            }
        }
        else
        {
            move.stp(MTR_CNTR_PIN_1,MTR_CNTR_PIN_2,MTR_CNTR_PIN_3,MTR_CNTR_PIN_4); 
        }
    }
}
