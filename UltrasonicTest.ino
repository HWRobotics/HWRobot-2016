#include "Ultrasonic.h"
Ultrasonic ultrasonic(12,13);

void setup() {
int outputPin = 10;
pinMode(outputPin,OUTPUT);
}

void loop()
{
  if(ultrasonic.Ranging(CM)<10)
    digitalWrite(10,HIGH);
   else
    digitalWrite(10,LOW);
  
  delay(10);
  
}
