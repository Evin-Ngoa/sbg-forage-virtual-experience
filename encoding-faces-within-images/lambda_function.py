import boto3
import io
from PIL import Image
import numpy as np

# Initialize the S3 client
s3_client = boto3.client('s3')

def lambda_handler(event, context):
    # Extract bucket name and file key from the event
    # This assumes the Lambda function is triggered by an S3 event
    bucket_name = event['Records'][0]['s3']['bucket']['name']
    file_key = event['Records'][0]['s3']['object']['key']

    # Get the file from S3
    file_obj = s3_client.get_object(Bucket=bucket_name, Key=file_key)
    file_content = file_obj['Body'].read()

    # Read the image with Pillow
    image = Image.open(io.BytesIO(file_content))
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