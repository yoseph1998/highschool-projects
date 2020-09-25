#include <Servo.h>

//Integrate Roll Mechanics

int value = 0;

Servo M3, M2, M1;
Servo tilt;
short rollPin = 3;
short pitchPin = 4;
short yawPin = 5;
short thrustPin = 6;

short stabalizer;
double roll;
double pitch;
double thrust;

int motor1;
int motor2;
int motor3;


void setup() {
  M1.attach(7);
  M2.attach(8);
  M3.attach(9);
  Serial.begin(9600);
}

void skewNumbers()
{
    roll = ((roll - 848) - 385) / 380;
    roll = roll * roll * roll;
    roll *= 200;
    pitch =  ((pitch - 854) - 356) / 350;
    pitch = pitch * pitch * pitch;
    pitch *= 200; 
    thrust = (thrust - 889) / 571 * 850;
    
    if(thrust < 0)
      thrust *= -1;
}
void processMovement(int roll, int pitch, int thrust, int TOTAL)
{
  float motor1x;
  float motor2x;
  float motor3x;
  float motor1y;
  float motor2y;
  float motor3y;

    if(pitch > 0)
    {
      motor1x = pitch * 0.00;
      motor2x = pitch * 0.00;
      motor3x = pitch * 1.00;
    } else {
      pitch *= -1;
      motor1x = pitch * 1.00;
      motor2x = pitch * 1.00;
      motor3x = pitch * 0.00;
    }
    
    if(roll > 0)
    {
      motor1y = roll * 1.00;
      motor2y = roll * 0.00;
      motor3y = roll * 0.80;
    } else {
      roll*= -1;
      motor1y = roll * 0.00;
      motor2y = roll * 1.00;
      motor3y = roll * 0.80;
    }
    
  motor1 = ((motor1y + motor1x)/2) + thrust;    
  motor2 = ((motor2y + motor2x)/2) + thrust;
  motor3 = ((motor3y + motor3x)/2) + thrust;
  
  Serial.print("  ");
  Serial.print(motor1);
  Serial.print("  ");
  Serial.print(motor2);
  Serial.print("  ");
  Serial.println(motor3);
  Serial.println("_");
}

void loop() {
  
  if(Serial.available()) 
    value = Serial.parseInt();
    
  if(value == -100)
  {
  roll = pulseIn(rollPin, HIGH);
  pitch = pulseIn(pitchPin, HIGH);
  thrust = pulseIn(thrustPin, HIGH);
  
  skewNumbers();
  processMovement(roll, pitch, thrust, 100);
  
  M1.writeMicroseconds(motor1 + 850);
  M2.writeMicroseconds(motor2 + 850);
  M3.writeMicroseconds(motor3 + 850);
  }
  else
  {
  M1.writeMicroseconds(value);
  M2.writeMicroseconds(value);
  M3.writeMicroseconds(value);
  }
}
