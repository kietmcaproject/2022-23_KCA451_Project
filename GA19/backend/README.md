# Elective Subjects Recommendations API

Recommendation Systems are an ongoing research that is applied in various domains like music, movie, . ELECTIVE SUBJECT RECOMMENDATION SYSTEM is considered a challenged domain that has not been explored thoroughly. It benefits students who need suggestions and also enhances elective subjects selection processes during the selection. This project introduces a recommendation system for university elective courses, which recommends the courses based on the similarity between the course templates of students. This project utilizes some popular algorithms: Angular, Euclidean, Manhattan, Hamming, Dot and compares their performance on a dataset of academic records of university students. The experimental results show that applying these algorithm in this domain is superior to collaborative based with 86 percent of accuracy.

### Steps to install

> 1. Install Python

> 2. `git clone <repo_link>`

> 3. `cd <repo_name>`

> 4. `python -m venv .env`

> 5. `pip install -r requirements.txt`

> 6. `python app.py`

<br>

### Input
<br>

> Value sent by User
```json
{
    "subjects": [
        "Principles of Management & Communication"
    ],
    "k": 2,
    "electives": [1, 2, 3]
}
```

<br>

### Output
<br>

> Value sent by API
```json
[
    {
        "elective_1": [
            {
                "name": "Cryptography & Network Security",
                "score": 0.5
            },
            {
                "name": "Data Warehousing & Data Mining",
                "score": 0.5
            }
        ]
    },
    {
        "elective_2": [
            {
                "name": "Web Technology",
                "score": 0.43
            },
            {
                "name": "Big Data",
                "score": 0.57
            }
        ]
    },
    {
        "elective_3": [
            {
                "name": "Privacy & Security in Online Social Media",
                "score": 0.5
            },
            {
                "name": "Soft Computing",
                "score": 0.5
            }
        ]
    }
]
```

### Thank You

