//There are more information aboat this code in Page 24 of "Instruction manual-English.pdf"
char val;
int ledpin=13;
void setup()
{
  Serial.begin(9600);
  pinMode(ledpin,OUTPUT);
}
void loop()
{
  val=Serial.read();
  if(val=='r')
  {
    digitalWrite(ledpin,HIGH);
    delay(500);
    digitalWrite(ledpin,LOW);
    delay(500);
   Serial.println("keyes");
  }
 }

