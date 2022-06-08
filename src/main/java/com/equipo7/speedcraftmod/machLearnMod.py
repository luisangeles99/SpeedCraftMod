import numpy as np
import pandas as pd
import pickle
from pathlib import Path
import os.path
import tensorflow as tf



path = './model.h5'
mp= tf.keras.models.load_model(path )


data = [[r'/Users/luisalbertoangeles/Desktop/GITHUB/SpeedCraftMod/src/main/java/com/equipo7/images/screenshots/ss.png', "GOOD"]]
 
# Create the pandas DataFrame
test_df = pd.DataFrame(data, columns=['Filepath', 'Label'])

test_generator = tf.keras.preprocessing.image.ImageDataGenerator(
    rescale=1./255
)

test_images = test_generator.flow_from_dataframe(
    dataframe=test_df,
    x_col='Filepath',
    y_col='Label',
    target_size=(224, 224),
    color_mode='rgb',
    batch_size=32,
    shuffle=False
)

predictions = (mp.predict(test_images) >= 0.5).astype(np.int)
print(predictions[0][0])