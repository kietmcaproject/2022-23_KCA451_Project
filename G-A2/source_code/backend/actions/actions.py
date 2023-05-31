# This files contains your custom actions which can be used to run
# custom Python code.
#
# See this guide on how to implement these action:
# https://rasa.com/docs/rasa/custom-actions


# This is a simple example for a custom action which utters "Hello World!"

from typing import Any, Text, Dict, List

from rasa_sdk import Action, Tracker
from rasa_sdk.executor import CollectingDispatcher
#
#
# class ActionHelloWorld(Action):
#
#     def name(self) -> Text:
#         return "action_hello_world"
#
#     def run(self, dispatcher: CollectingDispatcher,
#             tracker: Tracker,
#             domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
#
#         dispatcher.utter_message(text="Hello World    !")
#
#         return []

fees = {
    'mca' : 138499,
    'b.tech': 151239,
    'mba' : 122890,
    'hostel': 96000,
    'library': 0
}

hod = {
    'mca': "Dr. Arun Kumar Tripathi",
    'b.tech': "Dr. Ajay Kumar Srivastava",
    'mba': "Dr. Binkey Srivastava",
    'b.pharma': "Dr. K.Nagarajan"
}

class ActionUtterFees(Action):

    def name(self) -> Text:
        return "action_utter_fees"

    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        
        course = tracker.get_slot('course')
        if course not in fees.keys():
            dispatcher.utter_message(text=f"The fees of {course} is not added yet.")
        else:
            fee = fees[course]
            if fee == 0:
                dispatcher.utter_message(text=f"There are no additional fees for {course}")
            else:
                dispatcher.utter_message(text=f"The fees of {course} is {fee}")
        return []


class ActionUtterFees(Action):

    def name(self) -> Text:
        return "action_utter_hod"

    def run(self, dispatcher: CollectingDispatcher,     
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        
        dept = tracker.get_slot('department')
        if dept not in hod.keys():
            dispatcher.utter_message(text=f"I don't have information about this department HOD yet.")
        else:    
            dispatcher.utter_message(text=f"The HOD of {dept} is {hod[dept]}")
        return []
