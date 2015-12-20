/*
 * movement.h
 * Control of the bridge that controls the motors.
 * Created by Andre Faria
 * Date: 2015-09-16
*/

#ifndef movement_h
#define movement_h

#include "Arduino.h"

class movement
{
    public:
        movement(int pinI1, int pinI2, int pinI3, int pinI4, int spdpna, int spdpnb, int vlpna, int vlpnb);
        void fwd(int pinI1, int pinI2, int pinI3, int pinI4, int spdpna, int spdpnb, int vlpna, int vlpnb);
        void bck(int pinI1, int pinI2, int pinI3, int pinI4, int spdpna, int spdpnb, int vlpna, int vlpnb);
        void rgt(int pinI1, int pinI2, int pinI3, int pinI4, int spdpna, int spdpnb, int vlpna, int vlpnb);
        void lft(int pinI1, int pinI2, int pinI3, int pinI4, int spdpna, int spdpnb, int vlpna, int vlpnb);
        void stp(int pinI1, int pinI2, int pinI3, int pinI4);
    private:
        int _pinI1;
        int _pinI2;
        int _pinI3;
        int _pinI4;
        int _spdpna;
        int _spdpnb;
        int _vlpna;
        int _vlpnb;
};

#endif
