/*
 * bluetooth.h
 * Implements basic serial functions for bluetooth.
 * Created by Andre Faria
 * Date: 2015-09-17
*/

#ifndef bluetooth_h
#define bluetooth_h

#include "Arduino.h"
#include "SoftwareSerial.h"

class bluetooth
{
    public:
        bluetooth(int rxpin, int txpin);
        void begin(int speed);
        void write(char value);
		char read();
    private:
        SoftwareSerial _serial;
};
#endif