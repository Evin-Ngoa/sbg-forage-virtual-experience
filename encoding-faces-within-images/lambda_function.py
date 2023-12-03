import os
from PIL import Image
import numpy as np

def lambda_handler(event, context):
    # Path to the local image file (adjust this to your file path)
    file_path = 'images/african-woman-face.jpg'

    # Open the file and keep it open for image operations
    with open(file_path, 'rb') as file:
        # Read the image
        image = Image.open(file)
        image.load()  # Explicitly load the image data

    # Encode the image
    encoded_vector = encode_image(image)

    # Output the vector
    return encoded_vector.tolist()

def encode_image(image):
    # Resize the image to a fixed size
    image = image.resize((64, 64))  # Example size

    # Convert image to numpy array
    image_array = np.array(image)

    # Flatten the array
    vector = image_array.flatten()

    return vector

if __name__ == "__main__":
    # Simulate event and context
    event = {}
    context = {}

    # Call the lambda_handler function
    result = lambda_handler(event, context)
    print("Encoded Vector:", result)