import pandas as pd


df = pd.read_csv("./test_csv_2.txt")
print(df)

print(df.columns)

print(df.iloc[1,2])