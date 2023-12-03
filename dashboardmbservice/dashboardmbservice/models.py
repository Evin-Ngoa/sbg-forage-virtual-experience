from django.db import models

class User(models.Model):
    id = models.CharField(max_length=255, primary_key=True)
    username = models.CharField(max_length=255)
    first_name = models.CharField(max_length=255)
    last_name = models.CharField(max_length=255)
    email = models.EmailField()
    address = models.TextField()
    membership = models.CharField(max_length=255)
    date_joined = models.DateTimeField()

class AppUsage(models.Model):
    id = models.AutoField(primary_key=True)
    timestamp = models.DateTimeField()
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    usage_type = models.CharField(max_length=255)
    session_start = models.DateTimeField()
    session_end = models.DateTimeField()
    clicks = models.IntegerField()
    pages_visited = models.IntegerField()
    device = models.CharField(max_length=255)

class Transactions(models.Model):
    id = models.AutoField(primary_key=True)
    timestamp = models.DateTimeField()
    sender = models.ForeignKey(User, related_name='sent_transactions', on_delete=models.CASCADE)
    recipient = models.ForeignKey(User, related_name='received_transactions', on_delete=models.CASCADE)
    transaction_type = models.CharField(max_length=255)
    amount = models.DecimalField(max_digits=10, decimal_places=2)
    status = models.CharField(max_length=255)
