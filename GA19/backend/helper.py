import pandas as pd
import os

def load_elective(choice):
    subs = pd.read_csv(os.path.join(os.curdir, 'dataset', 'elective_subjects.csv'))
    return list(subs['elective_{}'.format(choice)])