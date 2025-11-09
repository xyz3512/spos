



From picamera import PiCamera
From time import sleep
Camera=PiCamera()
Camera.start_preview()
Sleep(5)
Camera.capture(‘/home/pi/camera122.jpg’)
Camera.stop_preview()
Print(“Image Captured”)

                          


      

  //                         OR                           






import picamera
import time
num_images = 5
camera = picamera. Plcamera()
try:
camera.resolution = (1920, 1080)
for i in range(num_images):
image_name = f'images_{1} jpg
camera capture(image_name)
print (f' Captured (images_name}')
time sleep(1)
finally:
camera close()
