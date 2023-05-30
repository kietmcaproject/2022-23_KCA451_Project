import cv2
import numpy as np
import matplotlib.pyplot as plt
import tkinter as tk
from tkinter import *
from tkinter import filedialog
from tkinter.filedialog import askopenfile
from PIL import Image, ImageTk
#from imageai.Detection import ObjectDetection  

my_w = tk.Tk()
my_w.geometry("700x500")  # Size of the window 
#l1 = tk.Label(my_w,text='Upload Files & display',width=30,font=my_font1)  
#l1.grid(row=1,column=1,columnspan=4)
b1 = tk.Button(my_w, text='Upload Files', bg='skyblue',fg='red', 
   width=20,command = lambda:upload_file())
b1.grid(row=5,column=1,columnspan=4)
b1.place(x=300,y=300)
b2 = tk.Button(my_w, text='Live Image Detection', bg='skyblue',fg='red', 
   width=20,)
b2.grid(row=5,column=1,columnspan=4)
b2.place(x=280,y=370)


def upload_file():
    f_types = [('Jpg Files', '*.jpg'),
    ('PNG Files','*.png')]   # type of files to select 
    filename = tk.filedialog.askopenfilename(multiple=True,filetypes=f_types)
    col=1 # start from column 1
    row=3 # start from row 3 
    for f in filename:
        img=Image.open(f) # read the image file
        img=img.resize((200,200)) # new width & height
          
        img=ImageTk.PhotoImage(img)
        e1 =tk.Label(my_w)
        e1.grid(row=row,column=col)
        exit_button = tk.Button(my_w, text="Convert", command=my_w.destroy,width=15, bg='skyblue')
        exit_button.place(x=300,y=100)
        e1.image = img # keep a reference! by attaching it to a widget attribute
        e1['image']=img # Show Image  
        e1.place(x=30, y=30)
        if(col==3): # start new line after third column
            row=row+1# start wtih next row
            col=1    # start with first column
        else:       # within the same row 
            col=col+1 # increase to next column  
                     
             
my_w.mainloop()  # Keep the window open

plt.style.use('seaborn')

img = cv2.imread("ferrari.jpg")
img = cv2.cvtColor(img,cv2.COLOR_BGR2RGB)
plt.imshow(img)
plt.axis("off")
plt.title("Original Image")

img_gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
plt.imshow(img_gray,cmap="gray")
plt.axis("off")
plt.title("GrayScale Image")


img_invert = cv2.bitwise_not(img_gray)
plt.imshow(img_invert,cmap="gray")
plt.axis("off")
plt.title("Inverted Image")


img_smoothing = cv2.GaussianBlur(img_invert, (21, 21),sigmaX=0, sigmaY=0)
###plt.figure(figsize=(8,8))
plt.imshow(img_smoothing,cmap="gray")
plt.axis("off")
plt.title("Smoothen Image")

final = cv2.divide(img_gray, 255 - img_smoothing, scale=255)
#plt.figure(figsize=(8,8))

plt.axis("off")
plt.title("Final Sketch Image")
plt.imshow(final,cmap="gray")
#im=plt.imread("frame2.png")
#plt.imshow(im)


plt.figure(figsize=(20,20))
plt.subplot(1,5,1)
plt.imshow(img)
plt.axis("off")
plt.title("Original Image")
plt.subplot(1,5,2)
plt.imshow(img_gray,cmap="gray")
plt.axis("off")
plt.title("GrayScale Image")
plt.subplot(1,5,3)
plt.imshow(img_invert,cmap="gray")
plt.axis("off")
plt.title("Inverted Image")
plt.subplot(1,5,4)
plt.imshow(img_smoothing,cmap="gray")
plt.axis("off")
plt.title("Smoothen Image")
plt.subplot(1,5,5)
plt.imshow(final,cmap="gray")
plt.axis("off")
plt.title("Final Sketch Image")
plt.show()


import cv2
import pixellib
from pixellib.instance import instance_segmentation
segment_image = instance_segmentation()
segment_image.load_model("mask_rcnn_coco.h5") 
#segment_image.segmentImage("dog1.jpg", show_bboxes = True, output_image_name = "output4.jpg")
camera = cv2.VideoCapture(0)

while camera.isOpened():
    res,frame=camera.read()
    ### Apply Segmentation
    result=segment_image.segmentFrame(frame,show_bboxes=True)
    image=result[1]
    cv2.imshow('Image Segmentation',image)

    if cv2.waitKey(10) & 0xFF==ord('q'):
        break

camera.release()
cv2.destroyAllWindows() 

