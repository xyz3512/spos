//Understanding the connectivity of Raspberry-Pi / Adriano with IR sensor Write an application to detect 
//obstacle and notify user using LEDs.





import RPi. GPIO as GPIO
import time
# Pin setup
GPIO.setmode (GPIO.BOARD)
GPIO.setup (3, GPIO.IN) # IRsensor
GPIO.setup(5,GPIO.U)
# LED
while True:
val = GPIO.input(3)
if val == 0:
print("Object Detected")
GPIO.output(5, GPIO.HIGH)
# LED ON
time.sleep(0.2)
GPIO.output(5, GPIO.LOW)
# LED OFF
time.sleep(0.2)
else:
print("No Object")
GPIO.output(5, GPIO.LOW)
# Keep LED OFF
time.sleep(0.5)
