//There are more information aboat this code in Page 9 of "Instruction manual-English.pdf"
int pinI1=4;//define IN1
int pinI2=5;//define IN2
int speedpina=6;//define EA(PWM)
int pinI3=7;//define IN3
int pinI4=8;//define IN4
int speedpinb=9;//define EB(PWM)
void setup()
{
  pinMode(pinI1,OUTPUT);
  pinMode(pinI2,OUTPUT);
  pinMode(speedpina,OUTPUT);
  pinMode(pinI3,OUTPUT);
  pinMode(pinI4,OUTPUT);
  pinMode(speedpinb,OUTPUT);
}
void loop()
{
 //go straight
     analogWrite(speedpina,100);//input analog value to set speed
     analogWrite(speedpinb,100);
     digitalWrite(pinI4,LOW);//right motor move in anticlockwise
     digitalWrite(pinI3,HIGH);
     digitalWrite(pinI1,LOW);//left motor move in clockwise
     digitalWrite(pinI2,HIGH);
     delay(2000);
 //go back
     analogWrite(speedpina,100);//input analog value to set speed
     analogWrite(speedpinb,100);
     digitalWrite(pinI4,HIGH);//right motor move in clockwise
     digitalWrite(pinI3,LOW);
     digitalWrite(pinI1,HIGH);//left motor move in anticlockwise
     digitalWrite(pinI2,LOW);
     delay(2000);
 //turn right
     analogWrite(speedpina,60);//input analog value to set speed
     analogWrite(speedpinb,60);
     digitalWrite(pinI4,LOW);//right motor in anticlockwise
     digitalWrite(pinI3,HIGH);
     digitalWrite(pinI1,HIGH);//left motor in anticlockwise
     digitalWrite(pinI2,LOW);
     delay(2000);
 //turn left
     analogWrite(speedpina,60);//input analog value to set speed
     analogWrite(speedpinb,60);
     digitalWrite(pinI4,HIGH);//right motor in clockwise
     digitalWrite(pinI3,LOW);
     digitalWrite(pinI1,LOW);//left motor in anticlockwise
     digitalWrite(pinI2,HIGH);
     delay(2000);
 //stop
     digitalWrite(pinI4,HIGH);//right motor stop
     digitalWrite(pinI3,HIGH);
     digitalWrite(pinI1,HIGH);//left motor stop
     digitalWrite(pinI2,HIGH);
     delay(2000);
 }

