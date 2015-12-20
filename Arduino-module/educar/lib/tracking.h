/*
 * tracking.h
 * Get tracking data from tracking sensors.
 * Created by Andre Faria
 * Date: 2015-09-16
*/

#ifndef tracking_h
#define tracking_h

#include "Arduino.h"

class tracking
{
    public:
        tracking(int sensleft, int sensright, int sensmiddle);
        int getsensrigt(int sensright);
        int getsensleft(int sensleft);
        int getsensmidl(int sensmiddle);
    private:
        int _sensleft;
        int _sensright;
        int _sensmiddle;
};
#endif
