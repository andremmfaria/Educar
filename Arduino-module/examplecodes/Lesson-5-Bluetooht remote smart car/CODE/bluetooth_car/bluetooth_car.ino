//There are more information aboat this code in Page 25 of "Instruction manual-English.pdf"
//*******************************
int MotorRight1=5;
int MotorRight2=6;
int MotorLeft1=10;
int MotorLeft2=11;
  
  
void setup()
{  
  Serial.begin(9600);
  pinMode(MotorRight1, OUTPUT);  // 
  pinMode(MotorRight2, OUTPUT);  // 
  pinMode(MotorLeft1,  OUTPUT);  //  
  pinMode(MotorLeft2,  OUTPUT);  //
}

void go()// 
{
        digitalWrite(MotorRight1,LOW);
        digitalWrite(MotorRight2,HIGH);
        digitalWrite(MotorLeft1,LOW);
        digitalWrite(MotorLeft2,HIGH);
       
}

void left() //
{
      digitalWrite(MotorRight1,HIGH);
      digitalWrite(MotorRight2,LOW);
      digitalWrite(MotorLeft1,LOW);
      digitalWrite(MotorLeft2,HIGH);
  
}
void right() //
{
      digitalWrite(MotorRight1,LOW);
      digitalWrite(MotorRight2,HIGH);
      digitalWrite(MotorLeft1,HIGH);
      digitalWrite(MotorLeft2,LOW);
     
} 
void stop() //
{
     digitalWrite(MotorRight1,LOW);
     digitalWrite(MotorRight2,LOW);
     digitalWrite(MotorLeft1,LOW);
     digitalWrite(MotorLeft2,LOW);
    
}
void back() //
{
        digitalWrite(MotorRight1,HIGH);
        digitalWrite(MotorRight2,LOW);
        digitalWrite(MotorLeft1,HIGH);
        digitalWrite(MotorLeft2,LOW);;
      
}
        
void loop()
{
  char val = Serial.read();
  Serial.write(val);
  if (-1 != val) {
    if ('W' == val)
    go();
    else if ('A' ==val)
    left();
    else if ('D' == val)
    right();
    else if ('S' == val)
    back();
    else if ('Q' == val)
      stop();
    delay(500);
    }
  else
  {
    //stop();
    delay(500);
  }
}  


