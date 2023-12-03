import boto3
import io
from PIL import Image
import numpy as np
import cv2

# Initialize the S3 client
s3_client = boto3.client('s3')

def lambda_handler(event, context):
    # Extract bucket name and file key from the event
    bucket_name = event['Records'][0]['s3']['bucket']['name']
    file_key = event['Records'][0]['s3']['object']['key']

    # Get the file from S3
    file_obj = s3_client.get_object(Bucket=bucket_name, Key=file_key)
    file_content = file_obj['Body'].read()

    # Convert to an OpenCV format
    image = np.array(Image.open(io.BytesIO(file_content)))
    image = cv2.cvtColor(image, cv2.COLOR_RGB2BGR)

    # Encode the face in the image
    encoded_vector = encode_face(image)

    # Output the vector
    return encoded_vector.tolist()

def encode_face(image_path):
    # Load the pre-trained HAAR classifier for face detection
    face_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_frontalface_default.xml')

    # Load the image
    image = cv2.imread(image_path)
    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

    # Detect faces in the image
    faces = face_cascade.detectMultiScale(gray, 1.3, 5)

    # For simplicity, consider only the first detected face
    if len(faces) > 0:
        x, y, w, h = faces[0]
        face_region = gray[y:y+h, x:x+w]

        # Resize the face region to a fixed size and flatten
        face_region = cv2.resize(face_region, (64, 64))
        return face_region.flatten()
    else:
        return np.array([])
