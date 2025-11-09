
//


Import Adafruit_DHT
Import RPi.GPIO as GPIO
Import time
# GPIO setup
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)
LED_PIN = 18
GPIO.setup(LED_PIN, GPIO.OUT)
# DHT11 setup
SENSOR = Adafruit_DHT.DHT11
PIN = 4 # GPIO4
Try:
 While True:
 Temperature = Adafruit_DHT.read(SENSOR, PIN)[1]
 If temperature is not None: Print(f”Temp={temperature:.1f}°C”)
 If temperature > 30:
 GPIO.output(LED_PIN, GPIO.HIGH)
 Else:
 GPIO.output(LED_PIN, GPIO.LOW)
 Else:
 Print(“Sensor error, retrying...”)
 Time.sleep(2)
Except KeyboardInterrupt:
 Print(“Program stopped by user.”)
Finally:
 GPIO.cleanup()
