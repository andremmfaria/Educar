#include "Arduino.h"
#include "SoftwareSerial.h"
#include "../lib/bluetooth.h"

bluetooth::bluetooth(int rxpin, int txpin)
	:_serial(rxpin, txpin)
{
    pinMode(rxpin, INPUT);
    pinMode(txpin, OUTPUT);
	_serial.begin(9600);
}

char bluetooth::read()
{
    return _serial.read();
}

void bluetooth::write(char value)
{
    _serial.write(value);
}
