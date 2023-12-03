# STANDARD BANK FORAGE VIRTUAL EXPERIENCE
- [Forage](https://www.theforage.com/simulations?companies=standard-bank/) - Standard Bank Virtual Experience Programs

## AWS Lambda Image Encoding Function

## Overview
This project contains a Python script designed to be deployed as an AWS Lambda function. The function is triggered by an event in an AWS S3 bucket (specifically, when a new image is uploaded). Upon activation, the function reads the image from the S3 bucket, encodes it into a numerical vector, and returns this vector. This process is useful in scenarios like preprocessing images for machine learning models.

## Getting Started

### Prerequisites
To use this Lambda function, you need:

1. **AWS Account:** You should have an AWS account with access to S3 and Lambda services.
2. **S3 Bucket:** An S3 bucket for storing the images. The Lambda function will be triggered when a new image is uploaded to this bucket.
3. **IAM Role:** An IAM role with permissions to access the S3 bucket and execute Lambda functions.
4. **Python:** Python installed on your local system for testing. The script is compatible with Python 3.6 and above.
5. **AWS CLI:** AWS Command Line Interface installed and configured for deploying the Lambda function.

### Installation & Running Guide

1. **Clone the Repository:**
```sh
git clone https://github.com/Evin-Ngoa/sbg-forage-virtual-experience.git
cd encoding-faces-within-images
```
2. **Install dependencies:**
- Using pip:
```sh
pip install -r requirements.txt
```
3. **Run the Application:**
- Using python:
```sh
python lambda_function.py

```
### AWS Setup
1. **Create an IAM Role:**
Create an IAM role with permissions to access S3 and run Lambda functions. Attach the AWSLambdaExecute policy for basic Lambda execution and S3 access.

2. **Create the Lambda Function:**

- Go to the AWS Lambda console and create a new function.
- Choose "Author from scratch."
- Assign the previously created IAM role to this function.
3. **Deploy the Function:**

- Package your script along with its dependencies.
- Upload the package to the Lambda function.
4. **Set Up S3 Trigger:**

- Go to your S3 bucket in the AWS console.
- Set up an event to trigger the Lambda function when a new image is uploaded.
5. **Test the Function:**

- Upload an image to your S3 bucket.
- The Lambda function should automatically execute and process the image.

## AWS Lambda Function Explanation
AWS Lambda is a serverless computing service that lets you run code without provisioning or managing servers. This Lambda function is designed to be triggered by an event in AWS S3 (e.g., when a new image file is uploaded to a specified bucket). The function then processes the image by encoding it into a vector format, suitable for machine learning applications. This serverless architecture is highly scalable and cost-effective for processing images, as you only pay for the compute time you consume with no charge when your code is not running.