# **Gesture-Control-Advancements**
>*Master's major project.*

>*Project members: Srishty Singh(https://www.github.com/srishty-singh2k), Raj Pratap Singh(https://www.github.com/workingpayload)*

  We perform `Hand Gesture Recognition`, making use of Google's `Mediapipe` framework in python, without any physical aid being affixed to hands.

   MediaPipe offers customizable ML solutions. Mediapipe Hands is a hand and finger tracking solution. It works by deducing 21 3D landmarks of a hand. For which the pipeline consists of two modules: a palm detection model after that a hand landmark model. The palm detector achieves an average precision of 95.7%. The hand detector performs keypoint localization of all 21 hand-knuckle coordinates in the detected hand regions provided by palm detector employing regression.

  We capture live video feed from camera using opencv library then pass it to Mediapipe that returns the landmarks' details. Afterwards we operate on those landmarks to implement several trivial use cases. By computing relative positioning, distance among landmarks, linear alignment we implement virtual mouse, finger counter, volume controller, rock-paper-scissors game. The required GUI is brought about using opencv.
  
  
  
## LANDMARKS TRACKING
> Sample.py

![Tracking](https://user-images.githubusercontent.com/48465143/203244043-af4e9f5a-74bb-4c3f-94f9-edcf9e1887f4.png)



## VIRTUAL MOUSE POINTER
> MenuScreen.py

![Pointer](https://user-images.githubusercontent.com/48465143/203244224-7e125a72-d695-44d7-aaad-1385ca14b161.png)



## FINGER COUNTER
> FingerCounter.py

![Counting](https://user-images.githubusercontent.com/48465143/203244312-e1eacf0e-7145-41fa-8d74-39a4d8f961cc.png)



## VOLUME CONTROLLER
> VolumeControl.py

![Volume](https://user-images.githubusercontent.com/48465143/203244310-70048abb-a9a0-431e-b68c-94406ad2c82a.png)



## ROCK-PAPER-SCISSORS GAME 
> RockPaperScissorsGame.py

![RPC](https://user-images.githubusercontent.com/48465143/203244296-7a36de7e-1f38-461e-a1ee-cd6d40d2cc69.png)



## SNAKE GAME
> SnakeGame.py

![Snake](https://user-images.githubusercontent.com/48465143/203244305-5262d75c-1fc2-4849-9cd5-285c658e59e8.png)



