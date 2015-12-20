#include "Arduino.h"
#include "../lib/tracking.h"

tracking::tracking(int sensleft, int sensmiddle, int sensright)
{
    Serial.begin(9600);
    pinMode(sensleft,INPUT); 	
    pinMode(sensmiddle,INPUT);
    pinMode(sensright,INPUT);
    _sensleft = sensleft;
    _sensmiddle = sensmiddle;
    _sensright = sensright;
}

int tracking::getsensleft(int _sensleft)
{
    return digitalRead(_sensleft);
}

int tracking::getsensmidl(int _sensmiddle)
{
    return digitalRead(_sensmiddle);
}

int tracking::getsensrigt(int _sensright)
{
    return digitalRead(_sensright);
}

