import mediapipe as mp
import time
import HandTrackingModule as htm
import cv2

pTime = 0
cTime = 0
cap = cv2.VideoCapture(0)
cap.set(3, 1920)
cap.set(4, 1080)
detector = htm.HandDetector()
while True:
    success, img = cap.read()
    img = detector.findHands(img, draw=True)
    lmList = detector.findPosition(img, draw=False)
    if len(lmList) != 0:
        print(lmList[0])

    cTime = time.time()
    fps = 1 / (cTime - pTime)
    pTime = cTime

    # cv2.putText(img, str(int(fps)), (10, 70), cv2.FONT_HERSHEY_PLAIN, 3,
    #           (255, 0, 255), 3)

    cv2.imshow("Image", img)
    cv2.waitKey(1)