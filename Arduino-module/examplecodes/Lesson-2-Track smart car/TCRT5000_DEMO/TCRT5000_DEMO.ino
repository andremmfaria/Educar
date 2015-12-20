int ledPin=7;//
int val;//
void setup()
{
  pinMode(ledPin,OUTPUT);//
  Serial.begin(9600);//
}
void loop()
{
 val=digitalRead(ledPin);//
 Serial.println(val);//
}

