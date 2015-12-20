//There are more information aboat this code in Page 12 of "Instruction manual-English.pdf"
int MotorRight1=4;
int MotorRight2=5;
int MotorLeft1=7;
int MotorLeft2=8;
const int SensorLeft = 1;     	//
const int SensorMiddle= 2;    	//
const int SensorRight = 3;     	//
int SL;    //
int SM;    //
int SR;    //

void setup()
{  
  Serial.begin(9600);
  pinMode(MotorRight1, 	OUTPUT);  	// 
  pinMode(MotorRight2, 	OUTPUT);  	// 
  pinMode(MotorLeft1,  	OUTPUT);  	// 
  pinMode(MotorLeft2,  	OUTPUT);  	// 
  pinMode(SensorLeft, 	INPUT); 	//
  pinMode(SensorMiddle, INPUT);		//
  pinMode(SensorRight, 	INPUT); 	//
}

void loop() 
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
        }}}

