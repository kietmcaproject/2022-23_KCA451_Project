from annoy import AnnoyIndex
import pandas as pd
import numpy as np
import pickle
import os


def make_np(df):
  df = df.drop(df.columns[0], axis=1)
  data = np.array(df).astype(float)
  return data


def load_to_tree(data, f, path):
  t = AnnoyIndex(f, 'angular')
  for i in range(len(data)):
    t.add_item(i, data[i])
  t.build(10)
  t.save(path)
  print("SAVED TREE")


def save_dicts(df, paths):
  subject2idx = { v:i for i, v in enumerate(list(df[df.columns[0]]))}
  idx2subject = { i:v for i, v in enumerate(list(df[df.columns[0]]))}

  print("SAVING SUBJECT TO INDEX...")
  with open(paths[0], "wb") as f:
    pickle.dump(subject2idx, f)
  
  print("SAVING INDEX TO SUBJECT...")
  with open(paths[1], "wb") as f:
    pickle.dump(idx2subject, f)

  
def extract_everything(df_path, tree_path, s2i_path, i2s_path):
  df = pd.read_csv(df_path)
  data = make_np(df)
  load_to_tree(data, len(data[0]), tree_path)
  save_dicts(df, [s2i_path, i2s_path])


if __name__ == '__main__':
  extract_everything(
    os.path.join(os.curdir, 'dataset', 'KNB_PERCENTAGE.csv'),
    os.path.join(os.curdir, 'model', 'KNB_MODEL', 'PERCENTAGE', 'tree.ann'),
    os.path.join(os.curdir, 'model', 'KNB_MODEL', 'PERCENTAGE', 'subject2idx.pkl'),
    os.path.join(os.curdir, 'model', 'KNB_MODEL', 'PERCENTAGE', 'idx2subject.pkl'),
  )