import firebase_admin
from firebase_admin import credentials
from firebase_admin import db

cred = credentials.Certificate("serviceAccountKey.json")
firebase_admin.initialize_app(cred,{
    'databaseURL': "https://project-8cfb1-default-rtdb.firebaseio.com/"
})

ref = db.reference('Students')

data = {
    "321654":
        {
            "name": "Swapnil",
            "major": "Engineering",
            "starting_year": 2016,
            "total_attendance": 6,
            "standing": "Good",
            "year":4,
            "last_attendance_time": "2023-04-01 00:54:34"
        },
    "852741":
        {
            "name": "Emily Blunt",
            "major": " Robotics",
            "starting_year": 2018,
            "total_attendance":7,
            "standing": "Good",
            "year":3,
            "last_attendance_time": "2023-04-06 00:54:34"
        },
    "963852":
        {
            "name": "Elon Musk",
            "major": "Artificial Intelligence",
            "starting_year": 2019,
            "total_attendance": 8,
            "standing": "Good",
            "year":2,
            "last_attendance_time": "2023-04-01 00:54:34"
        },
    "7127639":
        {
            "name": "Muskan Choudhary",
            "major": " Machine Learning",
            "starting_year": 2021,
            "total_attendance": 10,
            "standing": "Good",
            "year":3,
            "last_attendance_time": "2023-04-01 00:54:34"
        },
    "562728":
        {
            "name": "Avni Tyagi",
            "major": " Machine Learning",
            "starting_year": 2021,
            "total_attendance": 5,
            "standing": "Good",
            "year": 3,
            "last_attendance_time": "2023-04-01 00:54:34"
        }
}

for key,value in data.items():
    ref.child(key).set(value)