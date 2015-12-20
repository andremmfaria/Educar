#include "Arduino.h"
#include "../lib/movement.h"

movement::movement(int pinI1, int pinI2, int pinI3, int pinI4, int spdpna,int spdpnb,int vlpna, int vlpnb)
{
    pinMode(spdpna,OUTPUT);
    pinMode(spdpnb,OUTPUT);
    pinMode(pinI1,OUTPUT);
    pinMode(pinI2,OUTPUT);
    pinMode(pinI3,OUTPUT);
    pinMode(pinI4,OUTPUT);
    _pinI1 = pinI1;
    _pinI2 = pinI2;
    _pinI3 = pinI3;
    _pinI4 = pinI4;
    _spdpna = spdpna;
    _spdpnb = spdpnb;
    _vlpna = vlpna;
    _vlpnb = vlpnb;
}

void movement::fwd (int _pinI1, int _pinI2, int _pinI3, int _pinI4, int _spdpna,int _spdpnb,int _vlpna, int _vlpnb)
{
     analogWrite(_spdpna,_vlpna);
     analogWrite(_spdpnb,_vlpnb);
     digitalWrite(_pinI1,LOW);
     digitalWrite(_pinI2,HIGH);
     digitalWrite(_pinI3,HIGH);
     digitalWrite(_pinI4,LOW);
}

void movement::bck (int _pinI1, int _pinI2, int _pinI3, int _pinI4, int _spdpna,int _spdpnb,int _vlpna, int _vlpnb)
{
     analogWrite(_spdpna,_vlpna);
     analogWrite(_spdpnb,_vlpnb);
     digitalWrite(_pinI1,HIGH);
     digitalWrite(_pinI2,LOW);
     digitalWrite(_pinI3,LOW);
     digitalWrite(_pinI4,HIGH);
}

void movement::rgt (int _pinI1, int _pinI2, int _pinI3, int _pinI4, int _spdpna,int _spdpnb,int _vlpna, int _vlpnb)
{
     analogWrite(_spdpna,_vlpna);
     analogWrite(_spdpnb,_vlpnb);
     digitalWrite(_pinI1,HIGH);
     digitalWrite(_pinI2,LOW);
     digitalWrite(_pinI3,HIGH);
     digitalWrite(_pinI4,LOW);
}

void movement::lft (int _pinI1, int _pinI2, int _pinI3, int _pinI4, int _spdpna,int _spdpnb,int _vlpna, int _vlpnb)
{
     analogWrite(_spdpna,_vlpna);
     analogWrite(_spdpnb,_vlpnb);
     digitalWrite(_pinI1,LOW);
     digitalWrite(_pinI2,HIGH);
     digitalWrite(_pinI3,LOW);
     digitalWrite(_pinI4,HIGH);
}

void movement::stp (int _pinI1, int _pinI2, int _pinI3, int _pinI4)
{
    digitalWrite(_pinI4,HIGH);
    digitalWrite(_pinI3,HIGH);
    digitalWrite(_pinI1,HIGH);
    digitalWrite(_pinI2,HIGH);
}
