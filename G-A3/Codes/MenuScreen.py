import cv2
from HandTrackingModule import HandDetector
from pynput.keyboard import Controller

cap = cv2.VideoCapture(0)
cap.set(3, 1920)
cap.set(4, 1080)

detector = HandDetector(detectionCon=0.8)
keys = ["Track Hands", "Count Fingers", "Volume Control", "Rock Paper Scissors"]
go = False
option = 0

keyboard = Controller()


def drawAll(img, buttonList):
    for button in buttonList:
        x, y = button.pos
        w, h = button.size
        # cvzone.cornerRect(img, (button.pos[0], button.pos[1], button.size[0], button.size[1]),20, rt=0)
        cv2.rectangle(img, button.pos, (x + w, y + h), (232, 144, 24), cv2.FILLED)
        cv2.putText(img, button.text, (x + 5, y + h - 15),
                    cv2.FONT_HERSHEY_PLAIN, 3, (255, 255, 255), 3)
    return img


class Button():
    def __init__(self, pos, text, size=[520, 70]):
        self.pos = pos
        self.size = size
        self.text = text


buttonList = []

for i, key in enumerate(keys):
    buttonList.append(Button([350, 120 * i + 100], key))

while True:
    success, img = cap.read()
    img = detector.findHands(img)
    lmList, bboxInfo = detector.findPositionVol(img)
    img = drawAll(img, buttonList)


    if lmList:
        '''for button in buttonList:
            x, y = button.pos
            w, h = button.size

            if x < lmList[8][0] < x + w and y < lmList[8][1] < y + h:
                cv2.rectangle(img, (x - 5, y - 5), (x + w + 5, y + h + 5), (175, 0, 175), cv2.FILLED)
                cv2.putText(img, button.text, (x + 20, y + 65),
                            cv2.FONT_HERSHEY_PLAIN, 4, (255, 255, 255), 4)
                l, _, _ = detector.findDistance(8, 12, img, draw=False)
                print(l)

                # Check fingers up
                fingers = detector.fingersUp()
                # print(fingers)

                # If middle is up
                if fingers[2]:
                    import Sample
                #else:

        '''
        fingers = detector.fingersUp()
        if fingers[2]:
            print("2")
            for button in buttonList:
                x, y = button.pos
                w, h = button.size
                if x < lmList[8][1] < x + w and y < lmList[8][2] < y + h:
                    print("True")
                    option = keys.index(button.text) + 1
                    print(option)

        if fingers[4]:
            # cv2.putText(img, "Moving", (50, 700), cv2.FONT_HERSHEY_PLAIN, 8, (232, 144, 24), 8)
            if option == 1:
                import Sample
            elif option == 2:
                import FingerCounter
            elif option == 3:
                import VolumeControl
            elif option == 4:
                import RockPaperScissorsGame

    cv2.putText(img, str(option), (50, 700), cv2.FONT_HERSHEY_PLAIN, 8, (232, 144, 24), 8)

    cv2.imshow("Image", img)
    cv2.waitKey(1)
