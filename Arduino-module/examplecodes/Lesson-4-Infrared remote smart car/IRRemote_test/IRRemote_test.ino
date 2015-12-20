//There are more information aboat this code in Page 20 of "Instruction manual-English.pdf"
#include <IRremote.h>                    // 

const int irReceiverPin = 2;             // 

IRrecv irrecv(irReceiverPin);            // 
decode_results results;                  // 

void setup()
{
  Serial.begin(9600);                     // 
  irrecv.enableIRIn();                   // 
}


void showIRProtocol(decode_results *results) 
{
  Serial.print("Protocol: ");
  
  //
  switch(results->decode_type) {
   case NEC:
     Serial.print("NEC");
     break;
   case SONY:
     Serial.print("SONY");
     break;
   case RC5:
     Serial.print("RC5");
     break;
   case RC6:
     Serial.print("RC6");
     break;
   default:
     Serial.print("Unknown encoding");  
  }  

  //
  Serial.print(", irCode: ");            
  Serial.print(results->value, HEX);    // 
  Serial.print(",  bits: ");           
  Serial.println(results->bits);        //    
}

void loop() 
{
  if (irrecv.decode(&results)) {         // 
    showIRProtocol(&results);            // 
    irrecv.resume();                     //        
  }  
}

