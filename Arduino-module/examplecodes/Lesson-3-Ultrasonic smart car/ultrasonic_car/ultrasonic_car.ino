//There are more information aboat this code in Page 15 of "Instruction manual-English.pdf"

#include <Servo.h> 
int pinLB=6;     // 
int pinLF=9;     // 

int pinRB=10;    // 
int pinRF=11;    // 

int inputPin = A0;  // 
int outputPin =A1;  // 

int Fspeedd = 0;      // 
int Rspeedd = 0;      // 
int Lspeedd = 0;      // 
int directionn = 0;   //  
Servo myservo;        // 
int delay_time = 250; // 

int Fgo = 8;         // 
int Rgo = 6;         // 
int Lgo = 4;         // 
int Bgo = 2;         // 

void setup()
 {
  Serial.begin(9600);     // 
  pinMode(pinLB,OUTPUT); // 
  pinMode(pinLF,OUTPUT); //
  pinMode(pinRB,OUTPUT); // 
  pinMode(pinRF,OUTPUT); //
  
  pinMode(inputPin, INPUT);    // 
  pinMode(outputPin, OUTPUT);  //    

  myservo.attach(5);    // 
 }
void advance(int a)     // 
    {
     digitalWrite(pinRB,LOW);  // 
     digitalWrite(pinRF,HIGH);
     digitalWrite(pinLB,LOW);  //
     digitalWrite(pinLF,HIGH);
     delay(a * 100);     
    }

void right(int b)        //
    {
     digitalWrite(pinRB,LOW);   //
     digitalWrite(pinRF,HIGH);
     digitalWrite(pinLB,HIGH);
     digitalWrite(pinLF,HIGH);
     delay(b * 100);
    }
void left(int c)         //
    {
     digitalWrite(pinRB,HIGH);
     digitalWrite(pinRF,HIGH);
     digitalWrite(pinLB,LOW);   //
     digitalWrite(pinLF,HIGH);
     delay(c * 100);
    }
void turnR(int d)        //
    {
     digitalWrite(pinRB,LOW);  //
     digitalWrite(pinRF,HIGH);
     digitalWrite(pinLB,HIGH);
     digitalWrite(pinLF,LOW);  //
     delay(d * 100);
    }
void turnL(int e)        //
    {
     digitalWrite(pinRB,HIGH);
     digitalWrite(pinRF,LOW);   //
     digitalWrite(pinLB,LOW);   //
     digitalWrite(pinLF,HIGH);
     delay(e * 100);
    }    
void stopp(int f)         //
    {
     digitalWrite(pinRB,HIGH);
     digitalWrite(pinRF,HIGH);
     digitalWrite(pinLB,HIGH);
     digitalWrite(pinLF,HIGH);
     delay(f * 100);
    }
void back(int g)          //
    {

     digitalWrite(pinRB,HIGH);  //
     digitalWrite(pinRF,LOW);
     digitalWrite(pinLB,HIGH);  //
     digitalWrite(pinLF,LOW);
     delay(g * 100);     
    }
    
void detection()        //
    {      
      int delay_time = 250;   // 
      ask_pin_F();            // 
      
     if(Fspeedd < 10)         // 
      {
      stopp(1);               //  
      back(2);                // 
      }
           
      if(Fspeedd < 25)         // 
      {
        stopp(1);               //  
        ask_pin_L();            // 
        delay(delay_time);      // 
        ask_pin_R();            //   
        delay(delay_time);      //   
        
        if(Lspeedd > Rspeedd)   //
        {
         directionn = Rgo;      //
        }
        
        if(Lspeedd <= Rspeedd)   //
        {
         directionn = Lgo;      //
        } 
        
        if (Lspeedd < 10 && Rspeedd < 10)   //
        {
         directionn = Bgo;      //        
        }          
      }
      else                      //   
      {
        directionn = Fgo;        //    
      }
     
    }    
void ask_pin_F()   //  
    {
      myservo.write(90);
      digitalWrite(outputPin, LOW);   // 
      delayMicroseconds(2);
      digitalWrite(outputPin, HIGH);  // 
      delayMicroseconds(10);
      digitalWrite(outputPin, LOW);    // 
      float Fdistance = pulseIn(inputPin, HIGH);  // 
      Fdistance= Fdistance/5.8/10;       // 
      Serial.print("F distance:");      //
      Serial.println(Fdistance);         //
      Fspeedd = Fdistance;              // 
    }  
 void ask_pin_L()   //
    {
      myservo.write(5);
      delay(delay_time);
      digitalWrite(outputPin, LOW);   // 
      delayMicroseconds(2);
      digitalWrite(outputPin, HIGH);  // 
      delayMicroseconds(10);
      digitalWrite(outputPin, LOW);    // 
      float Ldistance = pulseIn(inputPin, HIGH);  // 
      Ldistance= Ldistance/5.8/10;       // 
      Serial.print("L distance:");       //
      Serial.println(Ldistance);         //
      Lspeedd = Ldistance;              // 
    }  
void ask_pin_R()   //  
    {
      myservo.write(177);
      delay(delay_time);
      digitalWrite(outputPin, LOW);   //
      delayMicroseconds(2);
      digitalWrite(outputPin, HIGH);  // 
      delayMicroseconds(10);
      digitalWrite(outputPin, LOW);    // 
      float Rdistance = pulseIn(inputPin, HIGH);  // 
      Rdistance= Rdistance/5.8/10;       // 
      Serial.print("R distance:");       //
      Serial.println(Rdistance);         //
      Rspeedd = Rdistance;              // 
    }  
    
void loop()
 {
    myservo.write(90);  //
    detection();        //
      
   if(directionn == 2)  //             
   {
     back(8);                    //  
     turnL(2);                   //
     Serial.print(" Reverse ");   //
   }
   if(directionn == 6)           //    
   {
     back(1); 
     turnR(6);                   // 
     Serial.print(" Right ");    //
   }
   if(directionn == 4)          //    
   {  
     back(1);      
     turnL(6);                  // 
     Serial.print(" Left ");     //   
   }  
   if(directionn == 8)          //      
   { 
    advance(1);                 //   
    Serial.print(" Advance ");   //
    Serial.print("   ");    
   }
 }


