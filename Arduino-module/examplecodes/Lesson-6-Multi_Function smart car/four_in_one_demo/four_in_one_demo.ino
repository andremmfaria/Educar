//There are more information aboat this code in Page 26 of "Instruction manual-English.pdf"

//******************************
#include <IRremote.h>  
#include <Servo.h>
//******************************************
int MotorRight1=5;
int MotorRight2=6;
int MotorLeft1=10;
int MotorLeft2=11;
int counter=0;
const int irReceiverPin = 2; //

char val; 
//************************************
long IRfront= 0x00FFA25D;        //
long IRback=0x00FF629D;         //
long IRturnright=0x00FFC23D;    //
long IRturnleft= 0x00FF02FD;     //
long IRstop=0x00FFE21D;         //
long IRcny70=0x00FFA857;        //
long IRAutorun=0x00FF906F;      //
long IRturnsmallleft= 0x00FF22DD; 
//******************************************************
const int SensorLeft = 7;      //
const int SensorMiddle= 4 ;    //
const int SensorRight = 3;     //
int SL;    //
int SM;    //
int SR;    //
IRrecv irrecv(irReceiverPin);  // 
decode_results results;       // 
//****************************************************
int inputPin =13 ; // 
int outputPin =12; // 
int Fspeedd = 0; // 
int Rspeedd = 0; // 
int Lspeedd = 0; // 
int directionn = 0; // 
Servo myservo; // 
int delay_time = 250; // 
int Fgo = 8; // 
int Rgo = 6; // 
int Lgo = 4; // 
int Bgo = 2; // 
//********************************************************************(SETUP)
void setup()
{  
  Serial.begin(9600);
  pinMode(MotorRight1, OUTPUT);  // 
  pinMode(MotorRight2, OUTPUT);  // 
  pinMode(MotorLeft1,  OUTPUT);  //
  pinMode(MotorLeft2,  OUTPUT);  // 
  irrecv.enableIRIn();     // 
   pinMode(SensorLeft, INPUT); //
  pinMode(SensorMiddle, INPUT);//
  pinMode(SensorRight, INPUT); //
  digitalWrite(2,HIGH);
  pinMode(inputPin, INPUT); // 
  pinMode(outputPin, OUTPUT); // 
  myservo.attach(9); // 


 }
//******************************************************************(Void)
void advance(int a) // 
{
        digitalWrite(MotorRight1,LOW);
        digitalWrite(MotorRight2,HIGH);
        digitalWrite(MotorLeft1,LOW);
        digitalWrite(MotorLeft2,HIGH);
        delay(a * 100); 
}
void right(int b) //
{
       digitalWrite(MotorLeft1,LOW);
       digitalWrite(MotorLeft2,HIGH);
       digitalWrite(MotorRight1,LOW);
       digitalWrite(MotorRight2,LOW);
       delay(b * 100);
}
void left(int c) //
{
      digitalWrite(MotorRight1,LOW);
      digitalWrite(MotorRight2,HIGH);
      digitalWrite(MotorLeft1,LOW);
      digitalWrite(MotorLeft2,LOW);
      delay(c * 100);
}
void turnR(int d) //
{
      digitalWrite(MotorRight1,HIGH);
      digitalWrite(MotorRight2,LOW);
      digitalWrite(MotorLeft1,LOW);
      digitalWrite(MotorLeft2,HIGH);
      delay(d * 100);
}
void turnL(int e) //
{
      digitalWrite(MotorRight1,LOW);
      digitalWrite(MotorRight2,HIGH);
      digitalWrite(MotorLeft1,HIGH);
      digitalWrite(MotorLeft2,LOW);
      delay(e * 100);
} 
void stopp(int f) //
{
     digitalWrite(MotorRight1,LOW);
     digitalWrite(MotorRight2,LOW);
     digitalWrite(MotorLeft1,LOW);
     digitalWrite(MotorLeft2,LOW);
     delay(f * 100);
}
void back(int g) //
{
        digitalWrite(MotorRight1,HIGH);
        digitalWrite(MotorRight2,LOW);
        digitalWrite(MotorLeft1,HIGH);
        digitalWrite(MotorLeft2,LOW);;
        delay(g * 100); 
}
void detection() //
{ 
    int delay_time = 250; // 
    ask_pin_F(); // 

    if(Fspeedd < 10) // 
   {
      stopp(1); //  
      back(2); // 
   }
    if(Fspeedd < 25) // 
   {
      stopp(1); // 
      ask_pin_L(); // 
      delay(delay_time); // 
      ask_pin_R(); //
      delay(delay_time); // 

      if(Lspeedd > Rspeedd) //
     {
        directionn = Lgo; //
     }

      if(Lspeedd <= Rspeedd) //
      {
        directionn = Rgo; //
      } 

      if (Lspeedd < 15 && Rspeedd < 15) //
     {
        directionn = Bgo; //
      } 
    }
    else //
   {
      directionn = Fgo; //
   }
}   
//*********************************************************************************
void ask_pin_F() // 
{
myservo.write(90);
digitalWrite(outputPin, LOW); // 
delayMicroseconds(2);
digitalWrite(outputPin, HIGH); // 
delayMicroseconds(10);
digitalWrite(outputPin, LOW); // 
float Fdistance = pulseIn(inputPin, HIGH); // 
Fdistance= Fdistance/5.8/10; // 
Serial.print("F distance:"); //
Serial.println(Fdistance); //
Fspeedd = Fdistance; // 
} 
//********************************************************************************
void ask_pin_L() // 
{
myservo.write(177);
delay(delay_time);
digitalWrite(outputPin, LOW); // 
delayMicroseconds(2);
digitalWrite(outputPin, HIGH); // 
delayMicroseconds(10);
digitalWrite(outputPin, LOW); // 
float Ldistance = pulseIn(inputPin, HIGH); // 
Ldistance= Ldistance/5.8/10; // 
Serial.print("L distance:"); //
Serial.println(Ldistance); //
Lspeedd = Ldistance; // 
} 
//******************************************************************************
void ask_pin_R() // 
{
myservo.write(5);
delay(delay_time);
digitalWrite(outputPin, LOW); // 
delayMicroseconds(2);
digitalWrite(outputPin, HIGH); // 
delayMicroseconds(10);
digitalWrite(outputPin, LOW); // 
float Rdistance = pulseIn(inputPin, HIGH); // 
Rdistance= Rdistance/5.8/10; // 
Serial.print("R distance:"); //
Serial.println(Rdistance); //
Rspeedd = Rdistance; // 
} 
//******************************************************************************(LOOP)
void loop() 
{
      SL = digitalRead(SensorLeft);
      SM = digitalRead(SensorMiddle);
      SR = digitalRead(SensorRight);
      performCommand();
//*************************************************************************    
  if (irrecv.decode(&results)) 
    {         // 
/***********************************************************************/
      if (results.value == IRfront)//
       {
        advance(10);//
       }
/***********************************************************************/ 
      if (results.value ==  IRback)//
       {
        back(10);//
       }
/***********************************************************************/
      if (results.value == IRturnright)//
      {
        right(6); // 
      }
/***********************************************************************/
     if (results.value == IRturnleft)//
     {
       left(6); // 
     }
/***********************************************************************/    
    if (results.value == IRstop)//
   {
     digitalWrite(MotorRight1,LOW);
     digitalWrite(MotorRight2,LOW);
     digitalWrite(MotorLeft1,LOW);
     digitalWrite(MotorLeft2,LOW);
    }
//********************************************************************
    if (results.value == IRcny70)
   {                     
     while(IRcny70)
     {  
       SL = digitalRead(SensorLeft);
       SM = digitalRead(SensorMiddle);
       SR = digitalRead(SensorRight);
                   
       if (SM == HIGH)//
       { 
          if (SL == LOW & SR == HIGH) // 
          {  
             digitalWrite(MotorRight1,LOW);
             digitalWrite(MotorRight2,HIGH);
             analogWrite(MotorLeft1,0);
             analogWrite(MotorLeft2,80);
          } 
          else if (SR == LOW & SL == HIGH) //
          {  
             analogWrite(MotorRight1,0);//
             analogWrite(MotorRight2,80);
             digitalWrite(MotorLeft1,LOW);
             digitalWrite(MotorLeft2,HIGH);
          }
         else  // 
          { 
             digitalWrite(MotorRight1,LOW);
             digitalWrite(MotorRight2,HIGH);
             digitalWrite(MotorLeft1,LOW);
             digitalWrite(MotorLeft2,HIGH);
             analogWrite(MotorLeft1,200);
             analogWrite(MotorLeft2,200);
             analogWrite(MotorRight1,200);
             analogWrite(MotorRight2,200);
         }      
       } 
       else // 
      {  
         if (SL == LOW & SR == HIGH)// 
        {  
            digitalWrite(MotorRight1,LOW);
            digitalWrite(MotorRight2,HIGH);
            digitalWrite(MotorLeft1,LOW);
            digitalWrite(MotorLeft2,LOW);
        }
         else if (SR == LOW & SL == HIGH) // 
        {  
           digitalWrite(MotorRight1,LOW);
           digitalWrite(MotorRight2,LOW);
           digitalWrite(MotorLeft1,LOW);
           digitalWrite(MotorLeft2,HIGH);
        }
         else // 
        {    
        digitalWrite(MotorRight1,HIGH);
        digitalWrite(MotorRight2,LOW);
        digitalWrite(MotorLeft1,HIGH);
        digitalWrite(MotorLeft2,LOW);;
        }
      }
       if (irrecv.decode(&results))
       {
             irrecv.resume(); 
                  Serial.println(results.value,HEX);
             if(results.value ==IRstop)
             { 
               digitalWrite(MotorRight1,HIGH);
               digitalWrite(MotorRight2,HIGH);
               digitalWrite(MotorLeft1,HIGH);
               digitalWrite(MotorLeft2,HIGH);
               break;
             }
       }
     }
      results.value=0;
   }
//**********************************************************
 if (results.value ==IRAutorun )
      {
           while(IRAutorun)
        {
            myservo.write(90); //
            detection(); /
             if(directionn == 8) //
            { 
              if (irrecv.decode(&results))
           {
             irrecv.resume(); 
             Serial.println(results.value,HEX);
             if(results.value ==IRstop)
             { 
               digitalWrite(MotorRight1,LOW); 
               digitalWrite(MotorRight2,LOW);
               digitalWrite(MotorLeft1,LOW);
               digitalWrite(MotorLeft2,LOW);
               break;
             }
           }
                results.value=0;
                advance(1); //
                Serial.print(" Advance "); //
                Serial.print(" "); 
            }
           if(directionn == 2) //
          {
            if (irrecv.decode(&results))
           {
             irrecv.resume(); 
             Serial.println(results.value,HEX);
             if(results.value ==IRstop)
             { 
               digitalWrite(MotorRight1,LOW); 
               digitalWrite(MotorRight2,LOW);
               digitalWrite(MotorLeft1,LOW);
               digitalWrite(MotorLeft2,LOW);
               break;
             }
           }
              results.value=0;
              back(8); // 
              turnL(3); //
              Serial.print(" Reverse "); //
          }
            if(directionn == 6) //
          {
           if (irrecv.decode(&results))
           {
              irrecv.resume(); 
              Serial.println(results.value,HEX);
             if(results.value ==IRstop)
             { 
               digitalWrite(MotorRight1,LOW); 
               digitalWrite(MotorRight2,LOW);
               digitalWrite(MotorLeft1,LOW);
               digitalWrite(MotorLeft2,LOW);
               break;
             }
           }
             results.value=0;
               back(1); 
               turnR(6); // 
               Serial.print(" Right "); //
          }
            if(directionn == 4) //
          { 
             if (irrecv.decode(&results))
           {
             irrecv.resume(); 
             Serial.println(results.value,HEX);
             if(results.value ==IRstop)
             { 
               digitalWrite(MotorRight1,LOW); 
               digitalWrite(MotorRight2,LOW);
               digitalWrite(MotorLeft1,LOW);
               digitalWrite(MotorLeft2,LOW);
               break;
             }
           }
                results.value=0;
                back(1); 
                turnL(6); // 
                Serial.print(" Left "); //
           } 
            
             if (irrecv.decode(&results))
           {
             irrecv.resume(); 
             Serial.println(results.value,HEX);
             if(results.value ==IRstop)
             { 
               digitalWrite(MotorRight1,LOW); 
               digitalWrite(MotorRight2,LOW);
               digitalWrite(MotorLeft1,LOW);
               digitalWrite(MotorLeft2,LOW);
               break;
             }
           }
         }
               results.value=0;
       }
/***********************************************************************/    
     else
    {
           digitalWrite(MotorRight1,LOW);
           digitalWrite(MotorRight2,LOW);
           digitalWrite(MotorLeft1,LOW);
           digitalWrite(MotorLeft2,LOW);
     }
      

        irrecv.resume();    //   
   }  
}
   
void performCommand() {
  if (Serial.available()) {
    val = Serial.read();
  }
    if (val == 'f') { // Forward
      advance(10);
    } else if (val == 'z') { // Stop Forward     
      stopp(10) ;
    } else if (val == 'b') { // Backward
      back(10); 
    } else if (val == 'y') { // Stop Backward
       back(10); 
    }  else if (val == 'l') { // Right
      turnR(10);
    } else if (val == 'r') { // Left
      turnL(10);
    } else if (val == 'v') { // Stop Turn
      stopp(10) ;
    } else if (val == 's') { // Stop
      stopp(10) ;
    } 
  
}




