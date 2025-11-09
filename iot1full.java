PRACTICAL NO 1
Title: Understanding the connectivity of Raspberry-Pi / Adriano with IR sensor.
Write an application to detect obstacle and notify user using LEDs.
Objectives:
 To understand the concept of IR sensor
 To interface IR sensor with Raspberry Pi model
 To program the Raspberry Pi model to detect the nearest object using IR
sensor and give indication through led.
Software:
 Raspbian OS (IDLE)
 Thonny IDE
Hardware Modules:
 Raspberry Pi Board
 IR sensor, Led, 330 ohm register
 Monitor
Theory:
What is Raspberry Pi?
The Raspberry Pi is a low-cost, credit-card-sized computer developed by the Raspberry Pi
Foundation to promote teaching of basic computer science and electronics. It has become
very popular for IoT and embedded system projects due to its flexibility and GPIO (General
Purpose Input/Output) support.
Key Features:
Feature Description
Processor ARM Cortex-based CPU (e.g., Quad-core 1.5 GHz in Pi 4)
RAM Varies from 1GB to 8GB
Storage microSD card slot
Connectivity HDMI, USB ports, Ethernet, Wi-Fi, Bluetooth
GPIO 40 pins (digital only), used to connect sensors, motors, LEDs, etc.
Power Supply 5V/2.5A micro USB or USB-C
✅Advantages
 Compact, portable, and inexpensive
 Supports multiple operating systems (mainly Raspberry Pi OS)
 Strong Python and Linux support
 GPIO allows interfacing with real-world devices
❌Disadvantages
 Slower than traditional PCs
 Limited analog inputs (only digital pins)
 Must handle power and GPIO carefully to avoid damages.
 Pin Diagram for Raspberry-Pi :
What is an IR Sensor?
 An IR Sensor is an electronic device that emits and detects infrared radiation. It's
commonly used for obstacle detection, distance sensing, and line following in robotics.
It has:
 IR Transmitter (LED) – emits infrared light
 IR Receiver (Photodiode) – detects the reflected IR light from an object
Typical IR Sensor Module Pins:
Pin Function
VCC Connects to 3.3V or 5V power supply
GND Connects to ground
OUT Digital output (LOW = Obstacle, HIGH = No obstacle)
How It Works
1. The IR LED emits infrared light.
2. If an object is nearby, the light reflects back.
3. The photodiode receives the reflected light and changes output voltage.
4. The comparator circuit converts it to digital HIGH or LOW.
IR Sensor Range
 Typically from 2 cm to 30 cm
 Some sensors have a potentiometer to adjust sensitivity/range
✅Advantages
 Low cost and easy to use
 Good for short-range detection
 Simple digital interface
❌Disadvantages
 Affected by ambient lighting (sunlight interference)
 Not reliable for long-range detection
 Limited accuracy compared to ultrasonic or LiDAR sensors
 Fig: IR Sensor
⚙️ Setup Steps
1. Install Raspbian OS
 Download Raspberry Pi Imager from https://www.raspberrypi.com/software
 Insert SD card into your PC, open Raspberry Pi Imager:
 Choose OS: Raspberry Pi OS (32-bit)
 Choose Storage: SD card
 Click “Write”
 Insert SD card into Raspberry Pi and boot.
◻ Algorithm
1. Initialize GPIO pins for IR sensor and LED.
2. Start infinite loop:
 Read digital input from IR sensor.
 If obstacle detected (sensor returns 0):
(i ) Turn ON LED.
(ii) Print message.
 Else:
(i) Turn OFF LED.
3. On exit, clean up GPIOs.
🎯 Pin Connection description:
🎯 Observation Table
Obstacle Present IR Sensor Output LED State Message
Yes LOW (0) ON Obstacle Detected
No HIGH (1) OFF No Obstacle
Conclusion:
 The IR sensor successfully detected obstacles and provided output to the Raspberry Pi.
 LED was used as an effective notifier.
 This experiment helps in understanding real-time sensing and actuation using embedded
systems and IoT.
Component Component
Pins
Raspberry Pi Pin Purpose
IR Sensor
IR Sensor VCC Pin 1 (3.3V) Powers the IR sensor
IR Sensor GND Pin 6 (GND) Common ground
IR Sensor OUT Pin 11 (GPIO17) Reads signal from sensor
LED
LED + Resistor Pin 13 (GPIO27) Controls LED for notification
LED GND Pin 6 (GND) Common ground
Program:
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
